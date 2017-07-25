package pl.pwr.websocket;

import java.io.StringReader;
import java.util.stream.StreamSupport;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import pl.pwr.handlers.ClientHandler;
import pl.pwr.handlers.StopUserHandler;

@ApplicationScoped
@ServerEndpoint("/actions")
public class ClientsWebSocketServer {

	@Inject
	Instance<ClientHandler> handlers;
	
	@Inject
	StopUserHandler stopUser;

	@Inject
	WebsocketService websocketService;

	@OnOpen
	public void open(Session session) {
	}

	@OnClose
	public void close(Session session) {
		websocketService.removeClient(session);
	}

	@OnError
	public void onError(Throwable error) {
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();
			String command = jsonMessage.getString("action");

			ClientHandler h = StreamSupport.stream(handlers.spliterator(), false)
					.filter(handler -> handler.handlesCommand(command)).findFirst()
					.orElseThrow(() -> new RuntimeException("Unknown command"));
			h.handleCommand(jsonMessage, session);
		}
	}
}
