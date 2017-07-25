function client4RandomNumberFactory(controlsDiv, processID, clientInstance) {
	
	var app4ContentDiv = document.createElement("div");
	app4ContentDiv.setAttribute("id", "app-4-content" + "_" + clientInstance);
	controlsDiv.appendChild(app4ContentDiv);
	
	var leftSideDiv = document.createElement("div");
	leftSideDiv.setAttribute("id", "left-side");
	app4ContentDiv.appendChild(leftSideDiv);
	
	addImage(leftSideDiv, "upper-image" + "_" + clientInstance);
	addImage(leftSideDiv, "middle-image" + "_" + clientInstance);
	addImage(leftSideDiv, "bottom-image" + "_" + clientInstance);
	
	var rightSideDiv = document.createElement("div");
	rightSideDiv.setAttribute("id", "right-side");
	app4ContentDiv.appendChild(rightSideDiv);
	
	addRandomNumberRowInterface(rightSideDiv, "pierwszej", "first-turn" + "_" + clientInstance);
	addRandomNumberRowInterface(rightSideDiv, "drugiej", "second-turn" + "_" + clientInstance);
	addRandomNumberRowInterface(rightSideDiv, "trzeciej", "third-turn" + "_" + clientInstance);
}

function addImage(leftSideDiv, imagePosition) {
	var imageDiv = document.createElement("div");
	imageDiv.setAttribute("class", "image");
	leftSideDiv.appendChild(imageDiv);
	
	var img = document.createElement("img");
	img.setAttribute("src", "source/blank.jpg");
	img.setAttribute("id", imagePosition);
	img.setAttribute("alt", imagePosition);
	imageDiv.appendChild(img);
}

function addRandomNumberRowInterface(rightSideDiv, turnText, turnID) {
	
	var app4LabelDiv = document.createElement("div");
	app4LabelDiv.setAttribute("class", "app-4-label");
	rightSideDiv.appendChild(app4LabelDiv);
	
	var anonymousSpan = document.createElement("span");
	app4LabelDiv.appendChild(anonymousSpan);
	
	var anonymousB = document.createElement("b");
	anonymousB.innerHTML = "Ilość elementów w " + turnText + " turze: ";
	anonymousSpan.appendChild(anonymousB);
	
	var output = document.createElement("output");
	output.setAttribute("id", turnID);
	output.value = 0;
	anonymousSpan.appendChild(output);
}