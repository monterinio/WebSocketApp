package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.websocket.Session;
import pl.pwr.model.AppData;
import pl.pwr.util.Strings;

@ApplicationScoped
public class StopUserHandler extends MasterClientNotificationHandler {
	
	@Inject
	AppData appData;
	
	@Override
	public boolean handlesCommand(String command) {
		return "stop-user".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		int processID = parameters.getInt("processID");
		int clientInstance = parameters.getInt("clientInstance");
		appData.resetClientState(processID, clientInstance);
		sendMessageToClient(processID, clientInstance, Strings.LOGGED_IN, "prohibitOperating");
		sendMessageToMasterClient(processID, clientInstance, Strings.LOGGED_IN);
	}

}
