var timeOutValue;

function printNotificationTemporarily(notification) {
	printNotification(notification);
	 timeOutValue = setTimeout(function() {
		 document.getElementById("notification-paragraph").innerText = "";
		 document.getElementById("start-button").disabled = false;
	 },3000);
}

function printNotification(notification) {
	if(timeOutValue !== undefined) {
		 clearTimeout(timeOutValue);
	 }
	document.getElementById("notification-paragraph").innerHTML = notification;
}

function permitOperating() {
	var inputs = document.getElementsByTagName("input");
	for(s of inputs) {
		s.disabled = false;
	}
	printNotificationTemporarily("NADANO ZEZWOLENIE");
}

function clientReady() {
	const buttonName = document.getElementById("start-button").innerText;
    if(buttonName === "Uruchom proces") {
    	sendMessage("client-ready", processID, socket);
        printNotification("OCZEKIWANIE NA ZEZWOLENIE PRACY");
        document.getElementById("start-button").innerText = "Zatrzymaj proces";
    } else {
    	sendMessage("client-stop", processID, socket);
    	prohibitOperating();
    	document.getElementById("start-button").innerText = "Uruchom proces";
    }
}

function sendMessage(actionType, processID, socket) {
	var ClientAction = {
            action: actionType,
            id: processID,
        };
    socket.send(JSON.stringify(ClientAction));
}

function init() {
	socket = new WebSocket("ws://localhost:8080/Test2/actions");
	socket.onmessage = onMessage;  
	socket.onopen = function() {
	sendMessage("get-id", processID, socket);
	}
}



