function sendControlState(event, outputName) {
	var ClientAction = new clientAction(event, outputName);
	socket.send(JSON.stringify(ClientAction));
}

function clientAction(event, outputName) {
	this.action = "set-element-value";
	this.processID = processID; // bierze ID z globalnego scopu czyli: const ID = 3;
	this.clientInstance = clientInstance;
	this.name = event.target.id; //inputPressure || inputVelocity
	this.value = event.target.value;
	this.output = outputName;
}

function prohibitOperating() {
	var inputs = document.getElementsByTagName("input");
	for(s of inputs) {
		s.disabled = true;
		s.value = 0;
	}
	
	var outputs = document.getElementsByTagName("output");
	for(s of outputs) {
		s.value = 0;
	}
	printNotification("PRACA ZATRZYMANA");
	document.getElementById("start-button").innerText = "Uruchom proces";
}