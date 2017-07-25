function measure() {
    var ClientAction = new clientAction();
	socket.send(JSON.stringify(ClientAction));
}

function clientAction() {
	this.action = "generate-flow-data";
	this.clientInstance = clientInstance;
}

function prohibitOperating() {
	var inputs = document.getElementsByTagName("input");
	for(s of inputs) {
		s.value = 0;
	}
	
	var outputs = document.getElementsByTagName("output");
	for(s of outputs) {
		s.value = 0;
	}
	printNotification("PRACA ZATRZYMANA");
	document.getElementById("start-button").innerText = "Uruchom proces";
	document.getElementById("measurement-button").disabled = true;
}
