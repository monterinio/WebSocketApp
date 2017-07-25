package pl.pwr.instructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;

public final class Instructions {
	public Instructions() { };
	
	public static List<JsonObject> processAlist;
	public static int [] processAClients = {2, 1, 1, 0, 0, 0};
	public static List<JsonObject> processBlist;
	public static int [] processBClients = {0, 1, 2, 1, 1, 2};
	public static List<JsonObject> userDefinedList;
	// Ka¿da kolejna liczba okreœla minimaln¹ liczbê instancji
	// procesów, pocz¹wszy od procesu ID:1 do procesu ID:6
	// Np. public static int [] userDefinedClients = {1, 2, 1, 0, 0, 0};
	// oznacza, ¿e do uruchomienia scenariusza wymagane jest zalogowanie
	// ID:1 Instancja:1, ID:2 Instancja:1, ID:2 Instancja:2, ID:3 Instancja:1
	public static int [] userDefinedClients = {2, 0, 0, 0, 0, 0};
	public static Map<String, List<JsonObject>> processesMap;
	static {
		processAlist = new ArrayList<>();
		processBlist = new ArrayList<>();
		userDefinedList = new ArrayList<>();
		processesMap = new HashMap<>();
		
		/* 8.FINISH => 1.B. */
		// INPUT
		// ID:1 INST:1 => STATE: OFF
		// ID:1 INST:2 => STATE: OFF
		// ID:3 INST:1 => INP1:= 150 AND INP2:= 350
		// OUTPUT
		// ID:3 INST:1 => INP1:= 350
		processAlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 1)
								.add("conditionClientInstance", 1)
								.add("element", "status")
								.add("relation", "none")
								.add("value", "1"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 1)
								.add("conditionClientInstance", 2)
								.add("element", "status")
								.add("relation", "none")
								.add("value", "1"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "150"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage2")
								.add("relation", "equal")
								.add("value", "350")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 1)
								.add("name", "inputVoltage1")
								.add("value", "350")
								.add("output", "outputVoltage1")))
								.build());
		/* 7. STEP */
		// INPUT
		// ID:3 INST:1 => INP1:= 1000 AND INP2:>200
		// ID:2 INST:1 => INP1:= 800 AND INP2:<0.5
		// ID:1 INST:1 => STATE: ON
		// ID:1 INST:2 => STATE: ON
		// OUTPUT
		// ID:1 INST:1 => STATE: OFF
		// ID:1 INST:2 => STATE: OFF
		// ID:2 INST:1 => INP1:=300 AND INP2:= 0.65
		// ID:3 INST:1 => INP1:= 150 AND INP2:= 350
		processAlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "1000"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage2")
								.add("relation", "greater")
								.add("value", "200"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "800"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "lesser")
								.add("value", "0.5"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 1)
								.add("conditionClientInstance", 1)
								.add("element", "status")
								.add("relation", "none")
								.add("value", "3"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 1)
								.add("conditionClientInstance", 2)
								.add("element", "status")
								.add("relation", "none")
								.add("value", "3")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "stop-user")
								.add("processID", 1)
								.add("clientInstance", 1))
						.add(Json.createObjectBuilder()
								.add("action", "stop-user")
								.add("processID", 1)
								.add("clientInstance", 2))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputVelocity")
								.add("value", "300")
								.add("output", "outputVelocity"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputPressure")
								.add("value", "0.65")
								.add("output", "outputPressure"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 1)
								.add("name", "inputVoltage1")
								.add("value", "150")
								.add("output", "outputVoltage1"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 1)
								.add("name", "inputVoltage2")
								.add("value", "350")
								.add("output", "outputVoltage2")))
								.build());
		/* 6. STEP */
		// INPUT
		// ID:1 INST:1 => INP1: ON AND INP5: ON
		// ID:1 INST:2 => INP1: ON AND INP3: ON
		// ID:3 INST:1 => INP1:>800
		// ID:2 INST:1 => STATE: ON
		// OUTPUT
		// ID:3 INST:1 => INP2:= 250
		// ID:1 INST:1 => INP4: TRUE
		// ID:2 INST:1 => INP1:= 800
		processAlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputValve1")
										.add("relation", "none")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputChoicebox5")
										.add("relation", "none")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 2)
										.add("element", "inputValve1")
										.add("relation", "none")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 2)
										.add("element", "inputMotor3")
										.add("relation", "none")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 3)
										.add("conditionClientInstance", 1)
										.add("element", "inputVoltage1")
										.add("relation", "greater")
										.add("value", "800"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 3)
										.add("clientInstance", 1)
										.add("name", "inputVoltage2")
										.add("value", "250")
										.add("output", "outputVoltage2"))
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 1)
										.add("clientInstance", 1)
										.add("name", "inputMotor4")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 2)
										.add("clientInstance", 1)
										.add("name", "inputVelocity")
										.add("value", "800")
										.add("output", "outputVelocity")))
										.build());
		/* 5. STEP */
		// INPUT
		// ID:2 INST:1 => INP1:= 500
		// ID:1 INST:1 => STATE: ON
		// OUTPUT
		// ID:3 INST:1 => STATE:ON AND INP1:= 1000
		// ID:1 INST:1 => INP5: OFF
		// ID:2 INST:1 => INP2:= 0.4
		processAlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "inputVelocity")
										.add("relation", "equal")
										.add("value", "500"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID", 3)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 3)
										.add("clientInstance", 1)
										.add("name", "inputVoltage1")
										.add("value", "1000")
										.add("output", "outputVoltage1"))
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 1)
										.add("clientInstance", 1)
										.add("name", "inputChoicebox5")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 2)
										.add("clientInstance", 1)
										.add("name", "inputPressure")
										.add("value", "0.4")
										.add("output", "outputPressure")))
										.build());
		/* 4. STEP */
		// INPUT
		// ID:1 INST:1 => INP5: ON	
		// ID:1 INST:2 => STATE: ON
		// ID:2 INST:1 => INP1:<400
		// OUTPUT
		// ID:2 INST:1 => STATE:ON AND INP1:= 500
		// ID:1 INST:2 => INP3: ON
		processAlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputCheckbox5")
										.add("relation", "none")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 2)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "inputVelocity")
										.add("relation", "lesser")
										.add("value", "400")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID", 2)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 2)
										.add("clientInstance", 1)
										.add("name", "inputVelocity")
										.add("value", "500")
										.add("output", "outputVelocity"))
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 1)
										.add("clientInstance", 2)
										.add("name", "inputMotor3")
										.add("value", "true")))
										.build());
		/* 3. STEP */
		// INPUT
		// ID:1 INST:1 => INP1: ON
		// ID:1 INST:2 => STATE: OFF
		// OUTPUT
		// ID:1 INST:2 => STATE: ON AND INP1:ON
		processAlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputValve1")
										.add("relation", "none")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 2)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID", 1)
										.add("clientInstance", 2))
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 1)
										.add("clientInstance", 2)
										.add("name", "inputValve1")
										.add("value", "true")))
										.build());
		/* 2. STEP */
		// INPUT
		// ID:1 INST:1 => INP1-INP6: OFF AND STATE: ON
		// OUTPUT
		// ID:1 INST:1 => INP1:ON AND INP5:ON
		processAlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputValve1")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputValve2")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputMotor3")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputMotor4")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputCheckbox5")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputCheckbox6")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 1)
										.add("clientInstance", 1)
										.add("name", "inputValve1")
										.add("value", "true"))
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 1)
										.add("clientInstance", 1)
										.add("name", "inputCheckbox5")
										.add("value", "true")))
										.build());
		/* 1.B. ALTERNATIVE START */
		//INPUT
		// ID:1 INST:1 => STATE: OFF
		// ID:2 INST:1 => INP1:= 300 AND INP2:= 0.65
		// ID:3 INST:1 => INP1:= 350
		// OUTPUT
		// ID:1 INST:1 => STATE: ON
		processAlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 1)
								.add("conditionClientInstance", 1)
								.add("element", "status")
								.add("relation", "none")
								.add("value", "1"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "300"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.65"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "350")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "allow-user")
								.add("processID", 1)
								.add("clientInstance", 1)))
								.build());
		/* 1A. START */
		// INPUT
		// ID:1 INST:1 => INP1-INP6: OFF AND STATE: OFF
		// ID:3 INST:1 => STATE: OFF
		// OUTPUT
		// ID:1 INST:1 => STATE: ON
		processAlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputValve1")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputValve2")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputMotor3")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputMotor4")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputCheckbox5")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "inputCheckbox6")
										.add("relation", "none")
										.add("value", "false"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 1)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 3)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID", 1)
										.add("clientInstance", 1)))
										.build());
		
		processesMap.put("ProcesA", processAlist);
	
/* STEP. 9A */
// INPUT:
// ID:2 INST:1 => INP1:= 150 AND INP2:= 0.35
// OUTPUT:
// ID:2 INST:1 => INP1:= 150 AND INP2:= 0.00
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "150"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.35")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputVelocity")
								.add("value", "150")
								.add("output", "outputVelocity"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputPressure")
								.add("value", "0.00")
								.add("output", "outputPressure")))
								.build());
		
/* STEP. 8A */
// INPUT:
// ID:3 INST:2 => INP1:= 770
// OUTPUT:
// ID:2 INST:1 => INP1:= 150 AND INP2:= 0.35
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 2)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "770")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputVelocity")
								.add("value", "150")
								.add("output", "outputVelocity"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputPressure")
								.add("value", "0.35")
								.add("output", "outputPressure")))
								.build());

/* STEP. 7A */
// INPUT:
// ID:2 INST:1 => INP1:=900 AND INP2:> 0.9
// OUTPUT:
// ID:3 INST:2 => STATE: ON AND INP1:=770	
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "900"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "greater")
								.add("value", "0.9")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage1")
								.add("value", "770")
								.add("output", "outputVoltage1"))
						.add(Json.createObjectBuilder()
								.add("action", "allow-user")
								.add("processID",3)
								.add("clientInstance", 2)))
								.build());

/* STEP. 6A */
// INPUT:
// ID:2 INST:1 => INP1:= 280 AND INP2:= 0.95
// OUTPUT:
// ID:2 INST:1 => INP1:= 900
		
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "280"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.95")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputVelocity")
								.add("value", "900")
								.add("output", "outputVelocity")))
								.build());
		
/* STEP. 5A */
// INPUT:
// ID:2 INST:1 => INP1:= 600 AND INP2:= 0.35
// OUTPUT:
// ID:2 INST:1 => INP1:= 280 AND INP2:= 0.95
		
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "600"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.35")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputVelocity")
								.add("value", "280")
								.add("output", "outputVelocity"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputPressure")
								.add("value", "0.95")
								.add("output", "outputPressure")))
								.build());

/* STEP. 4A */
// INPUT:
// ID:3 INST:1 => INP1:= 500 AND INP2:= 80
// OUTPUT:
// ID:2 INST:1 => INP1:= 600 AND INP2:= 0.35
// ID:3 INST:1 => STATE: OFF
		
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "500"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage2")
								.add("relation", "equal")
								.add("value", "80")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputVelocity")
								.add("value", "600")
								.add("output", "outputVelocity"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 2)
								.add("clientInstance", 1)
								.add("name", "inputPressure")
								.add("value", "0.35")
								.add("output", "outputPressure"))
						.add(Json.createObjectBuilder()
								.add("action", "stop-user")
								.add("processID",3)
								.add("clientInstance", 1)))
								.build());
		


		/* STEP. 3A */
		// INPUT:
		// ID:3 INST:1 => STATE: ON
		// ID:2 INST:1 => INP1:= 200
		// OUTPUT:
		// ID:3 INST:1 => INP1:= 500 AND INP2:= 80
		processBlist.add(Json.createObjectBuilder()
								.add("conditions", Json.createArrayBuilder()
										.add(Json.createObjectBuilder()
												.add("conditionProcessID", 3)
												.add("conditionClientInstance", 1)
												.add("element", "status")
												.add("relation", "none")
												.add("value", "3"))
										.add(Json.createObjectBuilder()
												.add("conditionProcessID", 2)
												.add("conditionClientInstance", 1)
												.add("element", "inputVelocity")
												.add("relation", "equal")
												.add("value", "200")))
								.add("instructions", Json.createArrayBuilder()
										.add(Json.createObjectBuilder()
												.add("action", "set-element-value")
												.add("processID", 3)
												.add("clientInstance", 1)
												.add("name", "inputVoltage1")
												.add("value", "500")
												.add("output", "outputVoltage1"))
										.add(Json.createObjectBuilder()
												.add("action", "set-element-value")
												.add("processID", 3)
												.add("clientInstance", 1)
												.add("name", "inputVoltage2")
												.add("value", "80")
												.add("output", "outputVoltage2")))
												.build());
		


/* STEP. 2A */
// INPUT:
// ID:2 INST:1 => INP1:= 150
// ID:2 INST:1 => INP2:< 0.1
// OUTPUT:
// ID:3 INST:1 => STATE: ON
// ID:2 INST:1 => INP1:= 200
processBlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "inputVelocity")
										.add("relation", "equal")
										.add("value", "150"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "inputPressure")
										.add("relation", "lesser")
										.add("value", "0.1")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID",3)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 2)
										.add("clientInstance", 1)
										.add("name", "inputVelocity")
										.add("value", "200")
										.add("output", "outputVelocity")))
										.build());

/* STEP. 1: INITIALIZATION */
// INPUT:
// ID:2 INST:1 => STATE: ON
// ID:3 INST:1 => STATE: OFF
// ID:3 INST:2 => STATE: ON
// ID:4 INST:1 => STATE: OFF
// ID:5 INST:1 => STATE: ON
// ID:6 INST:1 => STATE: OFF
// ID:6 INST:2 => STATE: OFF
// OUTPUT:
// ID:2 INST:1 => INP1:= 150
// ID:3 INST:2 => INP2:= 400
// ID:4 INST:1 => STATE: ON AND RUN();
// ID:6 INST:1 => STATE: ON AND INP1: ON
processBlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 3)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 3)
										.add("conditionClientInstance", 2)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 4)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 5)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "3"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 6)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 6)
										.add("conditionClientInstance", 2)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 2)
										.add("clientInstance", 1)
										.add("name", "inputVelocity")
										.add("value", "150")
										.add("output", "outputVelocity"))
								.add(Json.createObjectBuilder()
										.add("action", "set-element-value")
										.add("processID", 3)
										.add("clientInstance", 2)
										.add("name", "inputVoltage2")
										.add("value", "400")
										.add("output", "outputVoltage2"))
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID",4)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "generate-number")
										.add("processID",4)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID",6)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "toggle-element")
										.add("processID", 6)
										.add("clientInstance", 1)
										.add("name", "inputContactor1a")
										.add("value", "true")))
										.build());

/* 1. START */
// INPUT:
// ID:2 INST:1 => STATE: OFF
// ID:3 INST:1 => STATE: OFF
// ID:3 INST:2 => STATE: OFF
// ID:4 INST:1 => STATE: OFF
// ID:5 INST:1 => STATE: OFF
// ID:6 INST:1 => STATE: OFF
// ID:6 INST:2 => STATE: OFF
// OUTPUT:
// ID:2 INST:1 => STATE: ON
// ID:3 INST:2 -> STATE: ON
// ID:5 INST:1 => STATE: ON
		processBlist.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 2)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 3)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 3)
										.add("conditionClientInstance", 2)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 4)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 5)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 6)
										.add("conditionClientInstance", 1)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1"))
								.add(Json.createObjectBuilder()
										.add("conditionProcessID", 6)
										.add("conditionClientInstance", 2)
										.add("element", "status")
										.add("relation", "none")
										.add("value", "1")))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID", 2)
										.add("clientInstance", 1))
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID", 3)
										.add("clientInstance", 2))
								.add(Json.createObjectBuilder()
										.add("action", "allow-user")
										.add("processID",5)
										.add("clientInstance", 1)))
										.build());
		
		//RULES
		// INPUT:
		// ID:2 INST:1 => INP1:= 200
		// OUTPUT:
		// ID:3 INST:2 => INP1:= 1000 AND INP2:=300
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "200")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage1")
								.add("value", "1000")
								.add("output", "outputVoltage1"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage2")
								.add("value", "300")
								.add("output", "outputVoltage2")))
								.build());
		// INPUT:
		// ID:3 INST:1 => INP1:= 500 AND INP2:= 80
		// OUTPUT:
		// ID:3 INST:2 => INP1:= 500 AND INP2:= 200
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "500"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage2")
								.add("relation", "equal")
								.add("value", "80")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage1")
								.add("value", "500")
								.add("output", "outputVoltage1"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage2")
								.add("value", "200")
								.add("output", "outputVoltage2")))
								.build());
		// INPUT:
		// ID:2 INST:1 => INP1:= 600 AND INP2:= 0.35
		// OUTPUT:
		// ID:3 INST:2 => INP1:= 300 AND INP2:=0
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "600"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.35")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage1")
								.add("value", "300")
								.add("output", "outputVoltage1"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage2")
								.add("value", "0")
								.add("output", "outputVoltage2")))
								.build());
		
		//INPUT:
		// ID:2 INST:1 => INP1:= 280 AND INP2:= 0.95
		// OUTPUT:
		// ID:3 INST:2 => STATE:OFF
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "280"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.95")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "stop-user")
								.add("processID", 3)
								.add("clientInstance", 2)))
								.build());	
		
		// INPUT:
		// ID:2 INST:1 => INP: 150 AND INP2:= 0.35
		// OUTPUT:
		// ID:3 INST:2 => INP1:=250 INP2: 150
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "150"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.35")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage1")
								.add("value", "250")
								.add("output", "outputVoltage1"))
						.add(Json.createObjectBuilder()
								.add("action", "set-element-value")
								.add("processID", 3)
								.add("clientInstance", 2)
								.add("name", "inputVoltage2")
								.add("value", "150")
								.add("output", "outputVoltage2")))
								.build());
		
//RULES FOR ID:4 INST:1
// INPUT:
// ID:3 INST:1 => INP1:= 500 AND INP2:= 80
// OUTPUT:
// ID:4 INST:1 => RUN();
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "500"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage2")
								.add("relation", "equal")
								.add("value", "80")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "generate-number")
								.add("processID",4)
								.add("clientInstance", 1)))
								.build());		


// RULES FOR ID:5 INST:1
// INPUT:
// ID:3 INST:2 => INP1:>250
// OUTPUT
// ID:5 INST:1 => MEASURE();
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 2)
								.add("element", "inputVoltage1")
								.add("relation", "greater")
								.add("value", "250")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "generate-flow-data") 
								.add("processID", 5)
								.add("clientInstance", 1)))
								.build());
		
		//
		// RULES FOR ID:6 INST:1
		//
		// INPUT:
		// ID:2 INST:1 => INP1:= 200
		// OUTPUT:
		// ID:6 INST:1 => INP1: OFF AND INP3: ON
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "200")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor1a")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor1c")
								.add("value", "true")))
								.build());

		// INPUT
		// ID:3 INST:1 => INP1:=500 AND INP2:= 80
		// OUTPUT
		// ID:6 INST:1 => INP2: ON AND INP6: ON
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "500"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 1)
								.add("element", "inputVoltage2")
								.add("relation", "equal")
								.add("value", "80")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor1b")
								.add("value", "true"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor3b")
								.add("value", "true")))
								.build());

		// INPUT
		// ID 2 INST:1 => INP1:600 INP2: 0.35
		// OUTPUT
		// ID:6 INST:1 => INP4: ON
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "600"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.35")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor2a")
								.add("value", "true")))
								.build());

		// INPUT
		// ID:2 INST:1 => INP1:= 280 AND INP2:= 0.95
		// OUTPT 
		// ID:6 INST:1 => INP4: OFF AND INP5: ON
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "280"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.95")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor2a")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor3a")
								.add("value", "true")))
								.build());

		// INPUT
		// ID:2 INST:1 => INP1:= 900 INP2:= 0.95
		// OUTPUT
		// ID:6 INST:1 => INP5: OFF
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "900"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.95")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor3a")
								.add("value", "false")))
								.build());

		// INPUT
		// ID:3 INST:2 => STATE: ON AND INP1:= 770
		// OUTPUT
		// ID:6 INST:1 => INP3: OFF AND INP6: OFF
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 2)
								.add("element", "status")
								.add("relation", "none")
								.add("value", "3"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 3)
								.add("conditionClientInstance", 2)
								.add("element", "inputVoltage1")
								.add("relation", "equal")
								.add("value", "770")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor1c")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor3b")
								.add("value", "false")))
								.build());

		// INPUT
		// ID:2 INST:1 => INP1:= 150 AND INP2:= 0.35
		// OUTPUT
		// ID:6 INST:1 => INP1: ON AND INP2: OFF
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputVelocity")
								.add("relation", "equal")
								.add("value", "150"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 2)
								.add("conditionClientInstance", 1)
								.add("element", "inputPressure")
								.add("relation", "equal")
								.add("value", "0.35")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor1a")
								.add("value", "true"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 1)
								.add("name", "inputContactor1b")
								.add("value", "false")))
								.build());
		
//
// RULES FOR ID:6 INST:2
//
// INPUT:
// ID:6 INST:1 => INP1:OFF AND INP2:OFF AND INP3: ON
// OUTPUT:
// ID:6 INST:2 => STATE: ON
		
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor1a")
								.add("relation", "equal")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor1b")
								.add("relation", "equal")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor1c")
								.add("relation", "equal")
								.add("value", "true")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "allow-user")
								.add("processID", 6)
								.add("clientInstance", 2)))
								.build());

// INPUT:
// ID:6 INST:1 => INP4: ON
// OUTPUT:
// ID:6 INST:2 => INP1: ON AND INP3: ON AND INP4: ON
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor2a")
								.add("relation", "equal")
								.add("value", "true")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 2)
								.add("name", "inputContactor1a")
								.add("value", "true"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 2)
								.add("name", "inputContactor1c")
								.add("value", "true"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 2)
								.add("name", "inputContactor2a")
								.add("value", "true")))
								.build());
// INPUT:
// ID:6 INST:1 => INP5: ON
// OUTPUT:
// ID:6 INST:2 => INP4: OFF INP5: ON
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor3a")
								.add("relation", "equal")
								.add("value", "true")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 2)
								.add("name", "inputContactor2a")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("action", "toggle-element")
								.add("processID", 6)
								.add("clientInstance", 2)
								.add("name", "inputContactor3a")
								.add("value", "true")))
								.build());
// INPUT:
// ID:6 INST:1 => INP1: OFF AND INP2: ON AND INP3-INP6: OFF
// OUTPUT:
// ID:6 INST:2 => STATE: OFF
		processBlist.add(Json.createObjectBuilder()
				.add("conditions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor1a")
								.add("relation", "equal")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor1b")
								.add("relation", "equal")
								.add("value", "true"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor1c")
								.add("relation", "equal")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor2a")
								.add("relation", "equal")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor3a")
								.add("relation", "equal")
								.add("value", "false"))
						.add(Json.createObjectBuilder()
								.add("conditionProcessID", 6)
								.add("conditionClientInstance", 1)
								.add("element", "inputContactor3b")
								.add("relation", "equal")
								.add("value", "false")))
				.add("instructions", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
								.add("action", "stop-user")
								.add("processID", 6)
								.add("clientInstance", 2)))
								.build());
		
processesMap.put("ProcesB", processBlist);

/*poni¿ej nale¿y umieœciæ instrukcje zdefiniowane przez u¿ytkownika*/

/*userDefinedList.add(Json.createObjectBuilder()
						.add("conditions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
									))
						.add("instructions", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
								))
										.build());
										*/
/* AKCJA 4*/
//INPUT
//ID:1 INST:1 => INP1: ON
//OUTPUT
//ID:1 INST:1 => STATE: OFF
//ID:1 INST:2 => STATE: OFF
userDefinedList.add(Json.createObjectBuilder()
		.add("conditions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("conditionProcessID", 1)
						.add("conditionClientInstance", 1)
						.add("element", "inputValve1")
						.add("relation", "equal")
						.add("value", "true")))
		.add("instructions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("action", "stop-user")
						.add("processID", 1)
						.add("clientInstance", 1))
				.add(Json.createObjectBuilder()
						.add("action", "stop-user")
						.add("processID", 1)
						.add("clientInstance", 2)))
						.build());
/* AKCJA 3 */
//INPUT
//ID:1 INST:2 => STATE: ON
//OUTPUT
//ID:1 INST:1 => INP1: ON AND INP2: OFF
userDefinedList.add(Json.createObjectBuilder()
		.add("conditions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("conditionProcessID", 1)
						.add("conditionClientInstance", 2)
						.add("element", "status")
						.add("relation", "none")
						.add("value", "3")))
		.add("instructions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("action", "toggle-element")
						.add("processID", 1)
						.add("clientInstance", 1)
						.add("name", "inputValve1")
						.add("value", "true"))
				.add(Json.createObjectBuilder()
						.add("action", "toggle-element")
						.add("processID", 1)
						.add("clientInstance", 1)
						.add("name", "inputValve2")
						.add("value", "false")))
						.build());
/* AKCJA 2 */
//INPUT
//ID:1 INST:1 => INP5: ON
//OUTPUT 
//ID:1 INST:2 => STATE: ON
//ID:1 INST:1 => INP4: ON
//ID:1 INST:2 => INP3: ON
userDefinedList.add(Json.createObjectBuilder()
		.add("conditions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("conditionProcessID", 1)
						.add("conditionClientInstance", 1)
						.add("element", "inputCheckbox5")
						.add("relation", "equal")
						.add("value", "true")))
		.add("instructions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("action", "allow-user")
						.add("processID", 1)
						.add("clientInstance", 2))
				.add(Json.createObjectBuilder()
						.add("action", "toggle-element")
						.add("processID", 1)
						.add("clientInstance", 2)
						.add("name", "inputMotor3")
						.add("value", "true"))
				.add(Json.createObjectBuilder()
						.add("action", "toggle-element")
						.add("processID", 1)
						.add("clientInstance", 1)
						.add("name", "inputMotor4")
						.add("value", "true")))
						.build());

/* AKCJA 1 */
//INPUT
//ID:1 INST:1 => STATE: OFF
//OUTPUT
//ID:1 INST:1 => STATE: ON AND INP2: ON AND INP5: ON
userDefinedList.add(Json.createObjectBuilder()
		.add("conditions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("conditionProcessID", 1)
						.add("conditionClientInstance", 1)
						.add("element", "status")
						.add("relation", "none")
						.add("value", "1")))
		.add("instructions", Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("action", "allow-user")
						.add("processID", 1)
						.add("clientInstance", 1))
				.add(Json.createObjectBuilder()
						.add("action", "toggle-element")
						.add("processID", 1)
						.add("clientInstance", 1)
						.add("name", "inputValve2")
						.add("value", "true"))
				.add(Json.createObjectBuilder()
						.add("action", "toggle-element")
						.add("processID", 1)
						.add("clientInstance", 1)
						.add("name", "inputCheckbox5")
						.add("value", "true")))
						.build());

				
/*powy¿ej nale¿y umieœciæ instrukcje zdefiniowane przez u¿ytkownika*/
processesMap.put("ProcesUzytkownika", userDefinedList);
}
	
	public static int processAinstructionsNumber = processAlist.size();

	public static JsonObject getElementProcessA(int i) {
		return processAlist.get(i);
	}
}
