package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.websocket.Session;

import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class GetMasterIdHandler implements ClientHandler {
	
	@Inject
	WebsocketService websocketService;
	
	@Override
	public boolean handlesCommand(String command) {
		return "get-master-id".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		websocketService.setMasterClientSession(session);
		websocketService.declareClients();
	}
}
