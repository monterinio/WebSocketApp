function updateOutputs(turns) {
	setTimeout(function() {
		i = turns[turns.length-1].value;
		document.getElementById(turns[i].name).value = turns[i].value;
		setImages(turns[i].value);
		if(i<2) {
			getElementsFromServer();
		}
		if(i === 2) {
			document.getElementById("release-button").disabled = false;
		}
	}, 500);
}

function setImages(outputValue) {
	var middleImageSource = document.getElementById(middleImageID).src;
	var bottomImageSource = document.getElementById(bottomImageID).src;
	var newImageSource;
	if(outputValue <= 20) {
		newImageSource = "../app4/source/low-amount.jpg";
	}
	else if(outputValue > 20 && outputValue <= 40) {
		newImageSource = "../app4/source/medium-amount.jpg";
	}
	else if(outputValue > 40) {
		newImageSource = "../app4/source/high-amount.jpg";
	}
	document.getElementById(upperImageID).src = middleImageSource;
	document.getElementById(middleImageID).src = bottomImageSource;
	document.getElementById(bottomImageID).src = newImageSource;
}

function releaseElements() {
	getElementsFromServer();
	document.getElementById("release-button").disabled = true;
}

function getElementsFromServer() {
	var ClientAction = new clientAction();
	socket.send(JSON.stringify(ClientAction));
}

function clientAction() {
	this.action = "generate-number";
	this.processID = processID; // bierze ID z globalnego scopu czyli: const ID = 4;
	this.clientInstance = clientInstance;
}

function prohibitOperating() {
	var outputs = document.getElementsByTagName("output");
	for(s of outputs) {
		s.value = 0;
	}
	printNotification("PRACA ZATRZYMANA");
	document.getElementById("start-button").innerText = "Uruchom proces";
	document.getElementById("release-button").disabled = true;
}

