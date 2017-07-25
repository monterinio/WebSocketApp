const processID = 4;
var clientInstance;
const middleImageID = "middle-image";
const bottomImageID = "bottom-image";
const upperImageID = "upper-image";
var socket;

window.onload = function() {
	init();
}

function onMessage(event) {
	var data = JSON.parse(event.data);

	if (data.action === "idDeclared") {
		document.getElementById("release-button").disabled = true;
		printNotificationTemporarily("ID NADANE POMYŚLNIE");
		clientInstance = data.clientInstance;
	}

	if (data.action === "permitOperating") {
		document.getElementById("release-button").disabled = false;
		printNotificationTemporarily("NADANO ZEZWOLENIE");
	}

	if (data.action === "prohibitOperating") {
		prohibitOperating();
	}

	if (data.action === "generate-number") {
		var turns = [ {
			name : "first-turn",
			value : data.firstTurn
		}, {
			name : "second-turn",
			value : data.secondTurn
		}, {
			name : "third-turn",
			value : data.thirdTurn
		}, {
			name : "turnNumber",
			value : data.turnNumber
		} ];

		updateOutputs(turns);
	}
	
	if(data.action === "master-logout") {
		masterClientLogoutActions();
	}
	
	if(data.action === "execute-process-3") {
		executeProcess3();
	}
}


