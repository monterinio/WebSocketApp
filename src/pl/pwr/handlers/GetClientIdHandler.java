package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.model.AppData;
import pl.pwr.util.Strings;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class GetClientIdHandler implements ClientHandler {
	
	@Inject
	WebsocketService websocketService;
	
	@Inject
	AppData appData;
	
	@Override
	public boolean handlesCommand(String command) {
		return "get-id".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		int processID = parameters.getInt("id");
		websocketService.addSession(session, processID);
		int instanceNumber = websocketService.getClientInstanceNumber(processID);
		websocketService.addClientInstance(session, instanceNumber);
		websocketService.addClientState(session, Strings.LOGGED_IN);
		appData.createAppData(processID, instanceNumber);
		
		JsonProvider provider = JsonProvider.provider();
		JsonObject idMessage = provider.createObjectBuilder()
				.add("action", "idDeclared")
				.add("clientInstance", instanceNumber)
				.build();
		
		websocketService.sendToSession(session, idMessage);
		// send to master-client aswell
		if (websocketService.getMasterClientSession() != null) {
			websocketService.declareClient(session);
		}
	}
	
}
