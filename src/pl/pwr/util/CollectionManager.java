package pl.pwr.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.websocket.Session;

public class CollectionManager {
	private CollectionManager() {}
	
	public static Session getSessionFromID(Map<Session,Integer> sessions, 
			Map<Session,Integer> clientsInstance, int processID, int clientInstance) {
		
		Session clientSession = null;
		Set<Session> filteredSessions = new HashSet<>();
		
		for(Entry<Session, Integer> entry : sessions.entrySet()) {
			if(entry.getValue().equals(processID)) {
				filteredSessions.add(entry.getKey());
			}
		}
		
		for(Session s : filteredSessions) {
			if(clientsInstance.get(s).equals(clientInstance)) {
				clientSession = s;
			}
		}
		
		return clientSession;
	}
	
}
