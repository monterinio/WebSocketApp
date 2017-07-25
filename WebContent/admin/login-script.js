function removeClient(processID, processInstance) {
	var client = document.getElementById("process_" + processID + "_instance_" + processInstance);
	client.parentNode.removeChild(client);
}

function printClient(processID, processInstance) {
    var content = document.getElementById("content");
	switch(processID) {
		case 1:
		var controlsDiv = createUserHeading(content, processID, processInstance);
		client1RowFactory("Zawór 1", "inputValve1", client1ToggleElementFactory, controlsDiv, processID, processInstance);
		client1RowFactory("Zawór 2", "inputValve2", client1ToggleElementFactory, controlsDiv, processID, processInstance);
		client1RowFactory("Silnik", [ "inputMotor3", "inputMotor4" ], client1RadioBoxFactory, controlsDiv, processID, processInstance);
		client1RowFactory("Warunki", ["inputCheckbox5", "inputCheckbox6" ], client1CheckBoxFactory, controlsDiv, processID, processInstance);
		break;
		
		case 2:
		var controlsDiv = createUserHeading(content, processID, processInstance);
		client23SlidersFactory("Prędkość: ", "Velocity", 1500, 50, controlsDiv, processID, processInstance);
		client23SlidersFactory("Nacisk: ", "Pressure", 1, 0.01, controlsDiv, processID, processInstance);
		break;
		
		case 3:
		var controlsDiv = createUserHeading(content, processID, processInstance);
		client23SlidersFactory("Napięcie 1: ", "Voltage1", 1000, 5, controlsDiv, processID, processInstance);
		client23SlidersFactory("Napięcie 2: ", "Voltage2", 1500, 5, controlsDiv, processID, processInstance);
		break;
		
		case 4:
		var controlsDiv = createUserHeading(content, processID, processInstance);
		client4RandomNumberFactory(controlsDiv, processID, processInstance);
		break;
		
		case 5:
		var controlsDiv = createUserHeading(content, processID, processInstance);
		client5RandomNumberFlowGeneratorFactory(controlsDiv, processID, processInstance);
		break;
		
		case 6:
		var controlsDiv = createUserHeading(content, processID, processInstance);
		client6RowFactory("STYCZNIK 1A", "inputContactor1a", controlsDiv, processID, processInstance);
		client6RowFactory("STYCZNIK 1B", "inputContactor1b", controlsDiv, processID, processInstance);
		client6RowFactory("STYCZNIK 1C", "inputContactor1c", controlsDiv, processID, processInstance);
		client6RowFactory("STYCZNIK 2A", "inputContactor2a", controlsDiv, processID, processInstance);
		client6RowFactory("STYCZNIK 3A", "inputContactor3a", controlsDiv, processID, processInstance);
		client6RowFactory("STYCZNIK 3B", "inputContactor3b", controlsDiv, processID, processInstance);
		break;
	}
}

//Common for every user
function createUserHeading(content, processID, processInstance) {
		
		var clientDiv = document.createElement("div");
		clientDiv.setAttribute("class", "client");
		clientDiv.setAttribute("id", "process_" + processID + "_instance_" + processInstance);
		content.appendChild(clientDiv);
		
		var informationDiv = document.createElement("div");
		informationDiv.setAttribute("class", "information");
		clientDiv.appendChild(informationDiv);
		
		var clientName = document.createElement("span");
		clientName.setAttribute("class", "clientName");
		clientName.innerHTML = "Proces: " + processID + "; Instancja: " + processInstance;
		informationDiv.appendChild(clientName);
		
		var clientStatus = document.createElement("div");
		clientStatus.setAttribute("class", "clientStatus");
		informationDiv.appendChild(clientStatus);
		
		var allowUserLink = document.createElement("a");
		allowUserLink.innerHTML = "Zezwól";
		allowUserLink.href = "#";
		allowUserLink.setAttribute("id", "allow-process_" + processID + "_instance_" + processInstance);
		allowUserLink.onclick = function () {
			allowUser(processID, processInstance);
		}
		clientStatus.appendChild(allowUserLink);
		
		var clientLogOut = document.createElement("div");
		clientLogOut.setAttribute("class", "clientLogOut");
		informationDiv.appendChild(clientLogOut);
		
		var stopUserLink = document.createElement("a");
		stopUserLink.innerHTML = "Zatrzymaj";
		stopUserLink.href = "#";
		stopUserLink.setAttribute("id", "stop-process_" + processID + "_instance_" + processInstance);
		stopUserLink.onclick = function () {
			stopUser(processID, processInstance);
		}
		clientLogOut.appendChild(stopUserLink);
		
		var controlsDiv = document.createElement("div");
		controlsDiv.setAttribute("class", "controls");
		clientDiv.appendChild(controlsDiv);
		
		return controlsDiv;
}

	