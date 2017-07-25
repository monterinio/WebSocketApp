const processID = 5;
var clientInstance;
var socket;

window.onload = function() {
	init();
}

function onMessage(event) {
	var data = JSON.parse(event.data);

	if (data.action === "idDeclared") {
		document.getElementById("measurement-button").disabled = true;
		printNotificationTemporarily("ID NADANE POMYŚLNIE");
		clientInstance = data.clientInstance;
	}

	if (data.action === "permitOperating") {
		document.getElementById("measurement-button").disabled = false;
		printNotificationTemporarily("NADANO ZEZWOLENIE");
	}

	if (data.action === "prohibitOperating") {
		prohibitOperating();
	}

	if (data.action === "generate-flow-data") {
		document.getElementById("inputFlow").value = data.data;
		document.getElementById("outputFlow").value = data.data;
	}
	
	if(data.action === "master-logout") {
		masterClientLogoutActions();
	}
}
