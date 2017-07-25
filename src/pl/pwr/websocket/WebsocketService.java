package pl.pwr.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.model.AppData;
import pl.pwr.util.CollectionManager;

@ApplicationScoped
public class WebsocketService {
	private final Map<Session, Integer> sessions = new HashMap<>(); // session + processID
	private final Map<Session, Integer> clientsState = new HashMap<>(); // session + state
	private final Map<Session, Integer> clientsInstance = new HashMap<>(); // session + instanceNo 
	private Session masterClientSession = null;

	private final NavigableMap<Integer, Boolean> client1Instances = new TreeMap<>();
	private final NavigableMap<Integer, Boolean> client2Instances = new TreeMap<>();
	private final NavigableMap<Integer, Boolean> client3Instances = new TreeMap<>();
	private final NavigableMap<Integer, Boolean> client4Instances = new TreeMap<>();
	private final NavigableMap<Integer, Boolean> client5Instances = new TreeMap<>();
	private final NavigableMap<Integer, Boolean> client6Instances = new TreeMap<>();
	
	@Inject
	AppData appData;
	
	public WebsocketService() {
		client1Instances.put(1, false);
		client2Instances.put(1, false);
		client3Instances.put(1, false);
		client4Instances.put(1, false);
		client5Instances.put(1, false);
		client6Instances.put(1, false);
	}
	
	public Session getMasterClientSession() {
		return masterClientSession;
	}

	public void setMasterClientSession(Session masterClientSession) {
		this.masterClientSession = masterClientSession;
	}

	public Map<Session, Integer> getSessions() {
		return sessions;
	}

	public Map<Session, Integer> getClientsState() {
		return clientsState;
	}
	
	public Map<Session, Integer> getClientsInstance() {
		return clientsInstance;
	}

	// add to sessions
	public void addSession(Session session, int clientID) {
		sessions.put(session, clientID);
	}

	// add to clients state
	public void addClientState(Session session, int clientState) {
		clientsState.put(session, clientState);
	}
	
	// add to clients instances
	public void addClientInstance(Session session, int instanceNumber) {
		clientsInstance.put(session, instanceNumber);
	}
	
	private int getClientInstanceNumber(NavigableMap<Integer, Boolean> clientInstances) {
		//jeœli jakiœ klucz siê zwolni³ to go zajmij i zwróæ
		for(Map.Entry<Integer, Boolean> entry : clientInstances.entrySet()) {
			if(entry.getValue() == false) {
				clientInstances.put(entry.getKey(), true);
				return entry.getKey();
			}
		}
		//jak ¿aden siê nie zwolni³ to do³ó¿ kolejny klucz
		Integer newKey = clientInstances.lastKey() + 1;
		clientInstances.put(newKey, true);
		return newKey;
	}
	
	//add client instance
	public int getClientInstanceNumber(int processID) {
		if(processID == 1) {
			return getClientInstanceNumber(client1Instances);
		}
		else if(processID == 2) {
			return getClientInstanceNumber(client2Instances);
		}
		else if(processID == 3) {
			return getClientInstanceNumber(client3Instances);
		}
		else if(processID == 4) {
			return getClientInstanceNumber(client4Instances);
		}
		else if(processID == 5) {
			return getClientInstanceNumber(client5Instances);
		}
		else if(processID == 6) {
			return getClientInstanceNumber(client6Instances);
		} else {
			return 0;
		}
	}
	
	// release client instance number
	public void releaseClientInstanceNumber(int processID, int clientInstance) {
		if(processID == 1) {
			client1Instances.put(clientInstance, false);
		}
		else if(processID == 2) {
			client2Instances.put(clientInstance, false);
		}
		else if(processID == 3) {
			client3Instances.put(clientInstance, false);
		}
		else if(processID == 4) {
			client4Instances.put(clientInstance, false);
		}
		else if(processID == 5) {
			client5Instances.put(clientInstance, false);
		}
		else if(processID == 6) {
			client6Instances.put(clientInstance, false);
		} 
	}
	
	// get clientsState size
	public int clientsStateSize() {
		return clientsState.size();
	}

	// remove client from data structures
	public void removeClient(Session session) {
		
		int processID = sessions.get(session);
		int clientInstance = clientsInstance.get(session);
		
		sessions.remove(session);
		clientsState.remove(session);
		clientsInstance.remove(session);
		
		appData.removeAppData(processID, clientInstance);
		releaseClientInstanceNumber(processID, clientInstance);
		
		if(masterClientSession != null) {
			removeClientFromMasterClient(processID, clientInstance);
		}
	}

	public void sendToSession(Session session, JsonObject message) {
		try {
			session.getBasicRemote().sendText(message.toString());
		} catch (IOException ex) {
			sessions.remove(session);
			// Logger.getLogger(ClientSessionHandler.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}
	
	public void declareClient(Session s) {
		JsonProvider provider = JsonProvider.provider();
		JsonObjectBuilder builder = provider.createObjectBuilder().add("action", "clientDeclared");
		builder.add("processID", (int) sessions.get(s));
		builder.add("clientInstance", (int) clientsInstance.get(s));
		JsonObject idMessage = builder.build();
		sendToSession(masterClientSession, idMessage);
	}
	
	public void declareClients() {
		JsonProvider provider = JsonProvider.provider();
		for (Session s : sessions.keySet())
		{
			JsonObjectBuilder builder = provider.createObjectBuilder().add("action", "clientDeclared");
			builder.add("clientClass", (int) sessions.get(s));
			builder.add("clientInstance", (int) clientsInstance.get(s));
			JsonObject idMessage = builder.build();
			sendToSession(masterClientSession, idMessage);
		}
	}
	
	public void sendClientsStateToMasterClient() {
		JsonProvider provider = JsonProvider.provider();
		JsonObjectBuilder builder = provider.createObjectBuilder().add("action", "changedState");
		for (int i = 1; i <= clientsState.size(); i++) {
			builder.add("client" + i, (int) clientsState.get(i));
		}
		JsonObject idMessage = builder.build();
		sendToSession(masterClientSession, idMessage);
	}
	
	public void sendClientStateToMasterClient(int processID, int clientInstance, int clientStatus) {
		JsonProvider provider = JsonProvider.provider();
		JsonObject idMessage = provider.createObjectBuilder()
				.add("action", "changedState")
				.add("processID", processID)
				.add("clientInstance", clientInstance)
				.add("state", clientStatus)
				.build();
		sendToSession(masterClientSession, idMessage);
	}
	
	private void removeClientFromMasterClient(int processID, int clientInstance) {
		JsonProvider provider = JsonProvider.provider();
		JsonObject idMessage = provider.createObjectBuilder()
				.add("action", "removeClient")
				.add("processID", processID)
				.add("clientInstance", clientInstance)
				.build();
		sendToSession(masterClientSession, idMessage);
	}
	
	public boolean checkUserStatus(int conditionProcessID, int conditionClientInstance, String value) {
		int comparedToStatus = Integer.valueOf(value);
		Session clientSession = CollectionManager.getSessionFromID(sessions, clientsInstance, conditionProcessID, conditionClientInstance);
		int clientStatus = clientsState.get(clientSession);
		if(comparedToStatus == clientStatus) return true;
		return false;
	}

}
