package pl.pwr.handlers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import pl.pwr.instructions.Instructions;
import pl.pwr.instructions.InstructionsHandler;
import pl.pwr.model.AppData;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class StartProcessHandler implements ClientHandler {
	
	@Inject
	AppData appData;
	
	@Inject
	Instance<ClientHandler> handlers;
	
	@Inject
	WebsocketService websocketService;
	
	private Thread thread;
	@Override
	public boolean handlesCommand(String command) {
		return "process".equals(command);
	}

	@Override
	public void handleCommand(JsonObject parameters, Session session) {
		String status = parameters.getString("status");
		if(status.equals("start")) {
			String processType = parameters.getString("option");
			String registeredClients = parameters.getString("clients");
			if(sufficientNumberOfClients(registeredClients, processType)) {
				thread = new Thread(new InstructionsHandler(appData, handlers, processType));
				thread.start();
			} else {
				JsonProvider provider = JsonProvider.provider();
				JsonObject message = provider.createObjectBuilder()
						.add("action", "insufficient-clients")
						.build();
				websocketService.sendToSession(websocketService.getMasterClientSession(), message);
			}
		}
		if(status.equals("stop")) {
			thread.interrupt();
		}
	}

	private boolean sufficientNumberOfClients(String registeredClients, String processType) {
		int registeredClientsArray [] = getLoggedUsersList(registeredClients);
		boolean isSatisfactory = true;
		switch(processType) {
		case "ProcesA":
			for(int i = 0; i < registeredClientsArray.length; i++) {
				if(registeredClientsArray[i] >= Instructions.processAClients[i]) isSatisfactory = isSatisfactory && true;
				else isSatisfactory = isSatisfactory && false;
			}
			break;
			
		case "ProcesB":
			for(int i = 0; i < registeredClientsArray.length; i++) {
				if(registeredClientsArray[i] >= Instructions.processBClients[i]) isSatisfactory = isSatisfactory && true;
				else isSatisfactory = isSatisfactory && false;
			}
			break;	
			
		case "ProcesUzytkownika":
			for(int i = 0; i < registeredClientsArray.length; i++) {
				if(registeredClientsArray[i] >= Instructions.userDefinedClients[i]) isSatisfactory = isSatisfactory && true;
				else isSatisfactory = isSatisfactory && false;
			}
			break;
		}
		return isSatisfactory;
	}
	
	private int [] getLoggedUsersList(String registeredClients) {
		int registeredClientsArray [] = new int[6];
		String[] registeredClientsParsed = registeredClients.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
		int i = 0;
		for(String str : registeredClientsParsed) {
			registeredClientsArray[i++] = Integer.parseInt(str); 
		}
		return registeredClientsArray;
	}
}
