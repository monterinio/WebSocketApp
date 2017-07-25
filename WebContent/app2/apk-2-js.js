const processID=2;
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
	}
	
	if(data.action === "permitOperating") {
		permitOperating();
	}
	
	if(data.action === "prohibitOperating") {
		prohibitOperating();
	}
	
	if(data.action === "set-element-value") {
		var controlName = data.controlName;
		var controlValue = data.controlValue;
		var outputName = data.outputName;
		document.getElementById(controlName).value = controlValue;
		document.getElementById(outputName).value = controlValue;
	}
	
	if(data.action === "master-logout") {
		masterClientLogoutActions();
	}
}


