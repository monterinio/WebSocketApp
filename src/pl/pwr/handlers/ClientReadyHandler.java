package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import pl.pwr.util.Strings;

@ApplicationScoped
public class ClientReadyHandler extends ClientStatusHandler {

	@Override
	public boolean handlesCommand(String command) {
		return "client-ready".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		changeClientStatus(session, Strings.START_BUTTON_NOTIFICATION);
	}
}
