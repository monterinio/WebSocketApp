package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import pl.pwr.util.Strings;

@ApplicationScoped
public class AllowUserHandler extends MasterClientNotificationHandler {

	@Override
	public boolean handlesCommand(String command) {
		return "allow-user".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		int processID = parameters.getInt("processID");
		int clientInstance = parameters.getInt("clientInstance");
		sendMessageToClient(processID, clientInstance, Strings.MASTER_CLIENT_APPROVAL, "permitOperating");
		sendMessageToMasterClient(processID, clientInstance, Strings.MASTER_CLIENT_APPROVAL);
	}

}
