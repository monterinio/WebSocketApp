function setBackgroundAndTextProperties(processID, processInstance, backgroundColor, textColor, hyperlinks) {
	document.getElementById('process_'+ processID + "_instance_" + processInstance).style.backgroundColor = backgroundColor;// '#BEBEBE';
	document.getElementById('process_'+ processID + "_instance_" + processInstance).style.color = textColor; // '#808080';
	toggleSegmentHyperLinks(processID, processInstance, hyperlinks); // 'none'
}

function toggleSegmentHyperLinks(processID, processInstance, toggle) {
	document.getElementById('allow-process_'+ processID + "_instance_" + processInstance).style.pointerEvents = toggle;
	document.getElementById('stop-process_'+ processID + "_instance_" + processInstance).style.pointerEvents = toggle;
}

function resetSegmentControls(processID, processInstance) {
	var inputs = document.getElementById('process_'+ processID + "_instance_" + processInstance).getElementsByTagName("input");
	for(s of inputs) {
		s.disabled = true;
		s.checked = false;
		s.value = 0;
	}
	
	var outputs = document.getElementById('process_'+ processID + "_instance_" + processInstance).getElementsByTagName("output");
	for(s of outputs) {
		s.value = 0;
	}
}

function toggleSegmentButtons(processID, processInstance, value) {
	var buttons = document.getElementById('process_'+ processID + "_instance_" + processInstance).getElementsByTagName("button");
	for(b of buttons) {
		b.disabled = value;
	}
}

function changeLayoutOnLogOut(processID, processInstance) {
	setBackgroundAndTextProperties(processID, processInstance, "#BEBEBE", "#808080", "none");
	resetSegmentControls(processID, processInstance);
}

function changeLayoutOnLogIn(processID, processInstance) {
	setBackgroundAndTextProperties(processID, processInstance, "#7FFFD4", "#808080", "none");
	resetSegmentControls(processID, processInstance);
	toggleSegmentButtons(processID, processInstance, true);
}

function changeLayoutOnStartButton(processID, processInstance) {
	setBackgroundAndTextProperties(processID, processInstance, "#CCFFB3", "#000", "auto");
}

function changeLayoutOnAllowLink(processID, processInstance) {
	setBackgroundAndTextProperties(processID, processInstance, "#33FF33", "#000", "auto");
	enableSegmentControls(processID, processInstance);
	toggleSegmentButtons(processID, processInstance, false);
}

function enableSegmentControls(processID, processInstance) {
	if(processID !== 5) {
		var inputs = document.getElementById('process_'+ processID + "_instance_" + processInstance).getElementsByTagName("input");
		for(s of inputs) {
			s.disabled = false;
		}
	}
}

function allowUser(processID, processInstance) {
	sendMessage("allow-user", processID, processInstance, socket);
}

function stopUser(processID, processInstance) {
	sendMessage("stop-user", processID, processInstance, socket);
}

function sendControlState(event, processID, clientInstance) {
	var ClientAction = new clientAction(event, processID, clientInstance);
	socket.send(JSON.stringify(ClientAction));
}

function clientAction(event, processID, clientInstance) {
	this.action = "toggle-element";
	this.processID = processID;
	this.clientInstance = clientInstance;
	this.name = event.target.id;
	this.value = event.target.checked.toString();
}

function sendSliderState(event, processID, clientInstance, outputName) {
	var ClientAction = new clientActionForSliders(event, processID, clientInstance, outputName);
	socket.send(JSON.stringify(ClientAction));
}

function clientActionForSliders(event, processID, clientInstance, outputName) {
	this.action = "set-element-value";
	this.processID = processID;
	this.clientInstance = clientInstance;
	this.name = event.target.id;
	this.value = event.target.value;
	this.output = outputName;
}

function sortClients() {
	var toSort = document.getElementById('content').children;
	toSort = Array.prototype.slice.call(toSort, 0);
	toSort.sort(function(a, b) { 
		var aord = +a.id.split('_')[3]; 
		var bord = +b.id.split('_')[3]; 
		return(aord > bord) ? 1 : -1; 
		});
	toSort.sort(function(a, b) { 
		var aord = +a.id.split('_')[1]; 
		var bord = +b.id.split('_')[1]; 
		return(aord > bord) ? 1 : -1; 
		});
		
	var parent = document.getElementById('content');
	parent.innerHTML="";
	for(var i=0,l=toSort.length;i<l;i++) { 
		parent.appendChild(toSort[i]); 
	}
}

// app4
function updateOutputs(turns, turnNumber, clientInstance) {
	setTimeout(function() {
		document.getElementById(turns[turnNumber].name).value = turns[turnNumber].value;
		setImages(turns[turnNumber].value, turnNumber, clientInstance);
	}, 500);
}

function setImages(outputValue, turnNumber, clientInstance) {
	var middleImageSource = document.getElementById("middle-image_" + clientInstance).src;
	var bottomImageSource = document.getElementById("bottom-image_" + clientInstance).src;
	var newImageSource;
	if(outputValue <= 20) {
		newImageSource = "../admin/source/low-amount.jpg";
	}
	else if(outputValue > 20 && outputValue <= 40) {
		newImageSource = "../admin/source/medium-amount.jpg";
	}
	else if(outputValue > 40) {
		newImageSource = "../admin/source/high-amount.jpg";
	}
	document.getElementById("upper-image_" + clientInstance).src = middleImageSource;
	document.getElementById("middle-image_" + clientInstance).src = bottomImageSource;
	document.getElementById("bottom-image_" + clientInstance).src = newImageSource;
}

//app5
function measure(clientInstance) {
    var ClientAction = new clientActionForFlow(clientInstance);
	socket.send(JSON.stringify(ClientAction));
}

function clientActionForFlow(clientInstance) {
	this.action = "generate-flow-data";
	this.clientInstance = clientInstance; 
}

//start process
function startProcess() {
	var comboBox = document.getElementById("combo-box");
	var selectedOption = comboBox.options[comboBox.selectedIndex].value;
	var registeredClients = getLoggedUsersList();
	var ClientAction = {
			action: "process",
			status: "start",
			option: selectedOption,
			clients: JSON.stringify(registeredClients)
	};
	document.getElementById("start-process-button").disabled = true;
	socket.send(JSON.stringify(ClientAction));
}

function getLoggedUsersList() {
	var registeredClients = [0, 0, 0, 0, 0, 0];
	var instances = document.getElementById('content').children;
	instances = Array.prototype.slice.call(instances, 0);
	for(var i = 0, len = instances.length; i < len; i++) {
		if(+instances[i].id.split('_')[1] == 1) {
			registeredClients[0]++;
		}
		if(+instances[i].id.split('_')[1] == 2) {
			registeredClients[1]++;
		}
		if(+instances[i].id.split('_')[1] == 3) {
			registeredClients[2]++;
		}
		if(+instances[i].id.split('_')[1] == 4) {
			registeredClients[3]++;
		}
		if(+instances[i].id.split('_')[1] == 5) {
			registeredClients[4]++;
		}
		if(+instances[i].id.split('_')[1] == 6) {
			registeredClients[5]++;
		}
	}
	return registeredClients;
}

function logOutUsers() {
	var instances = document.getElementById('content').children;
	instances = Array.prototype.slice.call(instances, 0);
	
	for(var i = 0, len = instances.length; i < len; i++) {
		var processID = +instances[i].id.split('_')[1];
		var clientInstance = +instances[i].id.split('_')[3];
		sendMessage("stop-user", processID, clientInstance, socket);
	}
}

function stopProcess() {
	var ClientAction = {
			action: "process",
			status: "stop",
			option: ""
	};
	document.getElementById("start-process-button").disabled = false;
	logOutUsers();
	socket.send(JSON.stringify(ClientAction));
}

function sendMessage(actionType, id, instance, socket) {
	var ClientAction = {
            action: actionType,
            processID: id,
            clientInstance: instance
        };
    socket.send(JSON.stringify(ClientAction));
}
