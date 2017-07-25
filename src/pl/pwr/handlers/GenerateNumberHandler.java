package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.model.AppData;
import pl.pwr.model.AppFourData;
import pl.pwr.util.CollectionManager;
import pl.pwr.util.RandomNumberGenerator;
import pl.pwr.util.Strings;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class GenerateNumberHandler implements ClientHandler {

	@Inject
	WebsocketService websocketService;

	@Inject
	AppData appData;
	
	private AppFourData appFourData;

	@Override
	public boolean handlesCommand(String command) {
		return "generate-number".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		int processID = parameters.getInt("processID");
		int clientInstance = parameters.getInt("clientInstance");
		appFourData = appData.getAppFourData(clientInstance);
		int turnNumber = appFourData.getTurn();
		saveDataOnServer(processID, clientInstance, turnNumber);
	}

	public void saveDataOnServer(int processID, int clientInstance, int turnNumber) {
		switch (turnNumber) {
		case Strings.APP_FOUR_TURN_1:
			appFourData.setFirstTurn(RandomNumberGenerator.generateNumber(60));
			break;

		case Strings.APP_FOUR_TURN_2:
			appFourData.setSecondTurn(RandomNumberGenerator.generateNumber(60));
			break;

		case Strings.APP_FOUR_TURN_3:
			appFourData.setThirdTurn(RandomNumberGenerator.generateNumber(60));
			break;
		}
		sendDataToMasterClient(clientInstance);
		sendDataToClient(clientInstance);
		appFourData.incrementTurn();
		appData.setAppFourData(clientInstance, appFourData);
	}

	public void sendDataToMasterClient(int clientInstance) {
		createAndSendUpdatingJson(websocketService.getMasterClientSession(), clientInstance, appFourData);
	}

	public void sendDataToClient(int clientInstance) {
		Session clientSession = CollectionManager.getSessionFromID(websocketService.getSessions(), 
				websocketService.getClientsInstance(), 4, clientInstance);
		createAndSendUpdatingJson(clientSession, clientInstance, appFourData);
	}

	private void createAndSendUpdatingJson(Session session, int clientInstance, AppFourData appFourData) {
		JsonProvider provider = JsonProvider.provider();
		JsonObject idMessage = provider.createObjectBuilder()
				.add("action", "generate-number")
				.add("clientInstance", clientInstance)
				.add("firstTurn", appFourData.getFirstTurn())
				.add("secondTurn", appFourData.getSecondTurn())
				.add("thirdTurn", appFourData.getThirdTurn())
				.add("turnNumber", appFourData.getTurn())
				.build();
		websocketService.sendToSession(session, idMessage);
	}

}
