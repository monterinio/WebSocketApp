package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import pl.pwr.util.Strings;

@ApplicationScoped
public class ClientStopHandler extends ClientStatusHandler {

	@Override
	public boolean handlesCommand(String command) {
		return "client-stop".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		changeClientStatus(session, Strings.LOGGED_IN);
	}
}
