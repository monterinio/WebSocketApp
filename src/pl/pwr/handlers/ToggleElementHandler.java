package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.model.AppData;
import pl.pwr.model.AppOneData;
import pl.pwr.model.AppSixData;
import pl.pwr.util.CollectionManager;
import pl.pwr.util.ControlNameManager;
import pl.pwr.util.Strings;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class ToggleElementHandler implements ClientHandler {
	
	@Inject
	WebsocketService websocketService;
	
	@Inject
	AppData appData;
	
	private AppOneData appOneData;
	private AppSixData appSixData;
	
	@Override
	public boolean handlesCommand(String command) {
		return "toggle-element".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		String name = parameters.getString("name");
		String value = parameters.getString("value");
		int processID = parameters.getInt("processID");
		int clientInstance = parameters.getInt("clientInstance");
		saveDataOnServer(processID, clientInstance, name, value);
	}
	
	public void saveDataOnServer(int processID, int clientInstance, String controlName, String controlValueString) {
		controlName = ControlNameManager.adjustClientControlName(controlName);
		boolean controlValue = Boolean.parseBoolean(controlValueString);
		System.out.println("BOOLEAN CONTROL_VALUE: " + controlValue);
		if (processID == Strings.APPLICATION_ONE) {
			appOneData = appData.getAppOneData(clientInstance);
			switch (controlName) {
			case Strings.APP_ONE_VALVE_1:
				appOneData.setValve1(controlValue);
				System.out.println("appOneData valve1: " + appOneData.isValve1());
				break;
			case Strings.APP_ONE_VALVE_2:
				appOneData.setValve2(controlValue);
				break;
			case Strings.APP_ONE_MOTOR_3:
				appOneData.setMotorA(controlValue);
				appOneData.setMotorB(!controlValue);
				break;
			case Strings.APP_ONE_MOTOR_4:
				appOneData.setMotorA(controlValue);
				appOneData.setMotorB(!controlValue);
				break;
			case Strings.APP_ONE_CHECKBOX_5:
				appOneData.setExhaust(controlValue);
				break;
			case Strings.APP_ONE_CHECKBOX_6:
				appOneData.setFilter(controlValue);
				break;
			}
			appData.setAppOneData(clientInstance, appOneData);
		} else if(processID == Strings.APPLICATION_SIX) {
			appSixData = appData.getAppSixData(clientInstance);
			switch(controlName) {
			case Strings.APP_SIX_CONTACTOR_1A:
				appSixData.setContactor1a(controlValue);
				break;
			case Strings.APP_SIX_CONTACTOR_1B:
				appSixData.setContactor1b(controlValue);
				break;
			case Strings.APP_SIX_CONTACTOR_1C:
				appSixData.setContactor1c(controlValue);
				break;
			case Strings.APP_SIX_CONTACTOR_2A:
				appSixData.setContactor2a(controlValue);
				break;
			case Strings.APP_SIX_CONTACTOR_3A:
				appSixData.setContactor3a(controlValue);
				break;
			case Strings.APP_SIX_CONTACTOR_3B:
				appSixData.setContactor3b(controlValue);
				break;
			}
			appData.setAppSixData(clientInstance, appSixData);
		}
		sendDataToMasterClient(processID, clientInstance, controlName, controlValue);
		sendDataToClient(processID, clientInstance, controlName, controlValue);
	}
	
	// data from app1
		public void sendDataToMasterClient(int processID, int clientInstance, String controlName, boolean controlValue) {
			controlName = ControlNameManager.adjustMasterClientControlName(controlName, clientInstance);
			createAndSendUpdatingJson(websocketService.getMasterClientSession(), processID, clientInstance, controlName, controlValue);
		}

		// data from master-client to app1
		public void sendDataToClient(int processID, int clientInstance, String controlName, boolean controlValue) {
			Session clientSession = CollectionManager.getSessionFromID(websocketService.getSessions(), 
					websocketService.getClientsInstance(), processID, clientInstance);
			//controlName = ControlNameManager.adjustClientControlName(controlName);
			//TODO: 1 wpisane na sztywno
			createAndSendUpdatingJson(controlName, controlValue, clientSession);
		}

		// for the use of app1 both client and master-client
		private void createAndSendUpdatingJson(String controlName, boolean controlValue, Session session) {
			JsonProvider provider = JsonProvider.provider();
			JsonObject idMessage = provider.createObjectBuilder()
					.add("action", "toggle-element")
					.add("controlName", controlName)
					.add("controlValue", controlValue)
					.build();
			websocketService.sendToSession(session, idMessage);
		}
		
		private void createAndSendUpdatingJson(Session session, int processID, int clientInstance, String controlName, boolean controlValue) {
			JsonProvider provider = JsonProvider.provider();
			JsonObject idMessage = provider.createObjectBuilder()
					.add("action", "toggle-element")
					.add("processID", processID)
					.add("clientInstance", clientInstance)
					.add("controlName", controlName)
					.add("controlValue", controlValue)
					.build();
			websocketService.sendToSession(session, idMessage);
		}
}
