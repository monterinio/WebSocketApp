function sendControlState(event) {
	var ClientAction = new clientAction(event);
	socket.send(JSON.stringify(ClientAction));
}

function clientAction(event) {
	this.action = "toggle-element";
	this.processID = processID; // bierze ID z globalnego scopu czyli: const ID = 1;
	this.clientInstance = clientInstance;
	this.name = event.target.id;
	this.value = event.target.checked;
}

function prohibitOperating() {
	var inputs = document.getElementsByTagName("input");
	for(s of inputs) {
		s.disabled = true;
		s.checked = false;
	}
	printNotification("PRACA ZATRZYMANA");
	document.getElementById("start-button").innerText = "Uruchom proces";
}