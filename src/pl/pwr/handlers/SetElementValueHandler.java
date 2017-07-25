package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.model.AppData;
import pl.pwr.model.AppThreeData;
import pl.pwr.model.AppTwoData;
import pl.pwr.util.CollectionManager;
import pl.pwr.util.ControlNameManager;
import pl.pwr.util.Strings;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class SetElementValueHandler implements ClientHandler {

	@Inject
	WebsocketService websocketService;
	
	@Inject
	AppData appData;
	
	private AppTwoData appTwoData;
	private AppThreeData appThreeData;
	
	@Override
	public boolean handlesCommand(String command) {
		return "set-element-value".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		String name = parameters.getString("name");
		double value = Double.parseDouble(parameters.getString("value"));
		String output = parameters.getString("output");
		int processID = parameters.getInt("processID");
		int clientInstance = parameters.getInt("clientInstance");
		saveDataOnServer(processID, clientInstance, name, value, output);
	}
	
	public void saveDataOnServer(int processID, int clientInstance, String controlName, double controlValue, String output) {
		controlName = ControlNameManager.adjustClientControlName(controlName);
		output = ControlNameManager.adjustClientControlName(output);
		if (processID == Strings.APPLICATION_TWO) {
			appTwoData = appData.getAppTwoData(clientInstance);
			switch (controlName) {
			case Strings.APP_TWO_VELOCITY:
				appTwoData.setVelocityInput(controlValue);
				break;
			case Strings.APP_TWO_PRESSURE:
				appTwoData.setPressureInput(controlValue);
				break;
			}
			appData.setAppTwoData(clientInstance, appTwoData);
		}
		if (processID == Strings.APPLICATION_THREE) {
			appThreeData = appData.getAppThreeData(clientInstance);
			switch (controlName) {
			case Strings.APP_THREE_VOLTAGE_1:
				appThreeData.setVoltage1(controlValue);
				break;
			case Strings.APP_THREE_VOLTAGE_2:
				appThreeData.setVoltage2(controlValue);
				break;
			}
			appData.setAppThreeData(clientInstance, appThreeData);
		}
		sendDataToMasterClient(processID, clientInstance, controlName, controlValue, output);
		sendDataToClient(processID, clientInstance, controlName, controlValue, output);
	}
	
	// data from app2&3
		public void sendDataToMasterClient(int processID, int clientInstance, String controlName, double controlValue, String output) {
			controlName = ControlNameManager.adjustMasterClientControlName(controlName, clientInstance);
			output = ControlNameManager.adjustMasterClientControlName(output, clientInstance);
			System.out.println("sendDataToMasterClient: " + controlName + ", " + output);
			createAndSendUpdatingJson(processID, clientInstance, controlName, controlValue, websocketService.getMasterClientSession(), output);
		}
		
		// for the use of app2&3 masterclient
		private void createAndSendUpdatingJson(int processID, int clientInstance, String controlName, 
				double controlValue, Session session, String output) {
			JsonProvider provider = JsonProvider.provider();
			JsonObject idMessage = provider.createObjectBuilder()
					.add("action", "set-element-value")
					.add("processID", processID)
					.add("clientInstance", clientInstance)
					.add("controlName", controlName)
					.add("controlValue", controlValue)
					.add("outputName", output)
					.build();
			websocketService.sendToSession(session, idMessage);
		}

		// data from master-client to app2&app3
		public void sendDataToClient(int processID, int clientInstance, String controlName, double controlValue, String output) {
			Session clientSession = CollectionManager.getSessionFromID(websocketService.getSessions(), 
					websocketService.getClientsInstance(), processID, clientInstance);
			createAndSendUpdatingJson(controlName, controlValue, clientSession, output);
		}

		// for the use of app2&3 client
		private void createAndSendUpdatingJson(String controlName, double controlValue, Session session, String output) {
			JsonProvider provider = JsonProvider.provider();
			JsonObject idMessage = provider.createObjectBuilder()
					.add("action", "set-element-value")
					.add("controlName", controlName)
					.add("controlValue", controlValue)
					.add("outputName", output)
					.build();
			websocketService.sendToSession(session, idMessage);
		}

}
