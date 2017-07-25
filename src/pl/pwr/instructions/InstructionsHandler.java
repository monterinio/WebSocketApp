package pl.pwr.instructions;

import java.util.List;
import java.util.stream.StreamSupport;
import javax.enterprise.inject.Instance;
import javax.json.JsonArray;
import javax.json.JsonObject;
import pl.pwr.handlers.ClientHandler;
import pl.pwr.model.AppData;

public class InstructionsHandler implements Runnable {
	
	private AppData appData;
	private Instance<ClientHandler> handlers;
	private List<JsonObject> instructionList;
	
	public InstructionsHandler(AppData appData, Instance<ClientHandler> handlers, String instructions) {
		this.appData = appData;
		this.handlers = handlers;
		this.instructionList = Instructions.processesMap.get(instructions); 
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				for(int i = 0; i < instructionList.size(); i++) {
					JsonObject element = instructionList.get(i);
					JsonArray instructions = element.getJsonArray("instructions");
					if(checkIfConditionsAreTrue(element) == true) {
						for(int j = 0; j < instructions.size(); j++) {
							JsonObject instruction = instructions.getJsonObject(j);
							doActions(instruction);
						}
					}
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private boolean checkIfConditionsAreTrue(JsonObject instruction) {
		JsonArray conditions = instruction.getJsonArray("conditions");
		boolean checkConditions = true;
		for(int i = 0; i < conditions.size(); i++) {
			JsonObject condition = conditions.getJsonObject(i);
			int conditionProcessID = condition.getInt("conditionProcessID");
			int conditionClientInstance = condition.getInt("conditionClientInstance");
			String element = condition.getString("element");
			String relation = condition.getString("relation");
			String value = condition.getString("value");
			checkConditions = checkConditions && appData.checkProcessConditions(conditionProcessID, conditionClientInstance, element, value, relation);
		}
		return checkConditions;
	}

	private void doActions(JsonObject instruction) {
		String command = instruction.getString("action");

		ClientHandler h = StreamSupport.stream(handlers.spliterator(), false)
				.filter(handler -> handler.handlesCommand(command)).findFirst()
				.orElseThrow(() -> new RuntimeException("Unknown command"));
		h.handleCommand(instruction, null);
	}
}
