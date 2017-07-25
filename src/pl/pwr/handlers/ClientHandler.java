package pl.pwr.handlers;

import javax.json.JsonObject;
import javax.websocket.Session;

public interface ClientHandler {
	boolean handlesCommand(String command);
	void handleCommand(JsonObject parameters, Session session);
}


