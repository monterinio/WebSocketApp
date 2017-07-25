var socket;
const LOGGED_OUT = 0;
const LOGGED_IN = 1;
const START_BUTTON_NOTIFICATION_SENT = 2;
const MASTER_CLIENT_START_APPROVED = 3;

window.onload = function() {
	init();
}

function onMessage(event) {
	var data = JSON.parse(event.data);
	
	if(data.action === "clientDeclared") {
		processID = data.processID;
		clientInstance = data.clientInstance;
		printClient(processID, clientInstance);
		toggleSegmentHyperLinks(processID, clientInstance, 'none')
		sortClients();
	}
	
	if(data.action === "removeClient") {
		processID = data.processID;
		clientInstance = data.clientInstance;
		removeClient(processID, clientInstance);
	}
	
	if(data.action === "changedState") {
		var state = data.state;
		if(state === LOGGED_IN) {
			changeLayoutOnLogIn(data.processID, data.clientInstance);
		}
		if(state === START_BUTTON_NOTIFICATION_SENT) {
			changeLayoutOnStartButton(data.processID, data.clientInstance);
		}
		if(state === MASTER_CLIENT_START_APPROVED) {
			changeLayoutOnAllowLink(data.processID, data.clientInstance);
		}
	}
	
	if(data.action === "toggle-element") {
		var processID = data.processID;
		var clientInstance = data.clientInstance;
		var controlName = data.controlName;
		var controlValue = data.controlValue;
		document.getElementById(controlName).checked = controlValue;
	}
	
	if(data.action === "set-element-value") {
		var controlName = data.controlName;
		var controlValue = data.controlValue;
		var outputName = data.outputName;
		document.getElementById(controlName).value = controlValue;
		document.getElementById(outputName).value = controlValue;
	}
	
	if(data.action === "generate-number") {
		var clientInstance = data.clientInstance;
		var turnNumber = data.turnNumber;
		var turns = [ 
			{ name : "first-turn_" + clientInstance,
			  value : data.firstTurn}, 
			{ name : "second-turn_" + clientInstance,
			  value : data.secondTurn}, 
			{ name : "third-turn_" + clientInstance,
			  value : data.thirdTurn} 
			  ];
		updateOutputs(turns, turnNumber, clientInstance);
	}

	if(data.action === "generate-flow-data") {
		var clientInstance = data.clientInstance;
		document.getElementById("inputFlow_" + clientInstance).value = data.data;
		document.getElementById("outputFlow_" + clientInstance).value = data.data;
	}
	
	if(data.action === "insufficient-clients") {
		alert("Zbyt mala liczba klientow!");
		document.getElementById("start-process-button").disabled = false;
	}
}
	


function init() {
	socket = new WebSocket("ws://localhost:8080/Test2/actions");
	socket.onmessage = onMessage;  
	socket.onopen = function() {
		var ClientAction = {
		        action: "get-master-id",
		        id: 0
		    };
		    socket.send(JSON.stringify(ClientAction));	
		}
	}



