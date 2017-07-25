const processID=6;
var clientInstance;
var socket;

window.onload = function() {
	init();
}

function onMessage(event) {
	var data = JSON.parse(event.data);
	
	if(data.action === "idDeclared") {
		printNotificationTemporarily("ID NADANE POMYŚLNIE");
		clientInstance = data.clientInstance;
		console.log(clientInstance);
	}
	
	if(data.action === "permitOperating") {
		permitOperating();
	}
	
	if(data.action === "prohibitOperating") {
		prohibitOperating();
	}
	
	if(data.action === "toggle-element") {
		var controlName = data.controlName;
		var controlValue = data.controlValue;
		document.getElementById(controlName).checked = controlValue;
	}
	
	if(data.action === "master-logout") {
		masterClientLogoutActions();
	}
}



