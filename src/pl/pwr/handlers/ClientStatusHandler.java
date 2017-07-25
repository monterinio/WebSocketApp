package pl.pwr.handlers;

import javax.inject.Inject;
import javax.websocket.Session;
import pl.pwr.websocket.WebsocketService;

public abstract class ClientStatusHandler implements ClientHandler {
	
	@Inject
	protected WebsocketService websocketService;
	
	protected void changeClientStatus(Session session, int clientStatus) {
		websocketService.addClientState(session, clientStatus);
		
		if (websocketService.getMasterClientSession() != null) {
			int processID = websocketService.getSessions().get(session);
			int clientInstance = websocketService.getClientsInstance().get(session);
			websocketService.sendClientStateToMasterClient(processID, clientInstance, clientStatus);
		}
	}
}
