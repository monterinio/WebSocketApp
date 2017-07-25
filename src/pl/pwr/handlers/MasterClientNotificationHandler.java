package pl.pwr.handlers;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.util.CollectionManager;
import pl.pwr.websocket.WebsocketService;

public abstract class MasterClientNotificationHandler implements ClientHandler {
	
	@Inject
	protected WebsocketService websocketService;
	
	public void sendMessageToClient(int processID, int clientInstance, int clientState, String task) {
		Session clientSession = CollectionManager.getSessionFromID(websocketService.getSessions(), 
				websocketService.getClientsInstance(), processID, clientInstance);
		websocketService.addClientState(clientSession, clientState);

		if (clientSession != null) {
			JsonProvider provider = JsonProvider.provider();
			JsonObject idMessage = provider.createObjectBuilder().add("action", task).build();
			websocketService.sendToSession(clientSession, idMessage);
		}
	}
	
	public void sendMessageToMasterClient(int processID, int clientInstance, int clientState) {
		Session masterClientSession = websocketService.getMasterClientSession();
		
		if(masterClientSession != null) {
			JsonProvider provider = JsonProvider.provider();
			JsonObject idMessage = provider.createObjectBuilder()
					.add("action", "changedState")
					.add("state", clientState)
					.add("processID", processID)
					.add("clientInstance", clientInstance)
					.build();
			websocketService.sendToSession(masterClientSession, idMessage);
		}
	}
}