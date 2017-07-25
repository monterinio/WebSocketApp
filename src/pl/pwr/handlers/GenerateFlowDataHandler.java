package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import pl.pwr.model.AppData;
import pl.pwr.model.AppFiveData;
import pl.pwr.util.CollectionManager;
import pl.pwr.util.RandomNumberGenerator;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class GenerateFlowDataHandler implements ClientHandler {

	@Inject
	WebsocketService websocketService;

	@Inject
	AppData appData;
	
	private AppFiveData appFiveData;
	
	@Override
	public boolean handlesCommand(String command) {
		return "generate-flow-data".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		int clientInstance = parameters.getInt("clientInstance");
		saveDataOnServer(clientInstance);
	}

	public void saveDataOnServer(int clientInstance) {
		appFiveData = appData.getAppFiveData(clientInstance);
		appFiveData.setFlowData(RandomNumberGenerator.generateNumber(300));
		appData.setAppFiveData(clientInstance, appFiveData);
		sendDataToMasterClient(clientInstance);
		sendDataToClient(clientInstance);
	}

	public void sendDataToMasterClient(int clientInstance) {
		createAndSendUpdationJson(websocketService.getMasterClientSession(), clientInstance, appFiveData);
	}

	public void sendDataToClient(int clientInstance) {
		Session clientSession = CollectionManager.getSessionFromID(websocketService.getSessions(), 
				websocketService.getClientsInstance(), 5, clientInstance);
		createAndSendUpdatingJson(clientSession, appFiveData);
	}
	
	private void createAndSendUpdationJson(Session session, int clientInstance, AppFiveData appFiveData) {
		JsonProvider provider = JsonProvider.provider();
		JsonObject idMessage = provider.createObjectBuilder()
				.add("action", "generate-flow-data")
				.add("clientInstance", clientInstance)
				.add("data", appFiveData.getFlowData())
				.build();
		websocketService.sendToSession(session, idMessage);
	}
	
	private void createAndSendUpdatingJson(Session session, AppFiveData appFiveData) {
		JsonProvider provider = JsonProvider.provider();
		JsonObject idMessage = provider.createObjectBuilder()
				.add("action", "generate-flow-data")
				.add("data", appFiveData.getFlowData())
				.build();
		websocketService.sendToSession(session, idMessage);
	}
}
