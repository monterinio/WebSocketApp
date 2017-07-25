function client6RowFactory(name, htmlID, controlsDiv, processID, clientInstance) {

	var subrowDiv = document.createElement("div");
	subrowDiv.setAttribute("class", "subrow");
	controlsDiv.appendChild(subrowDiv);

	var labelDiv = document.createElement("div");
	labelDiv.setAttribute("class", "label");
	subrowDiv.appendChild(labelDiv);

	var anonymousP = document.createElement("p");
	anonymousP.innerHTML = name;
	labelDiv.appendChild(anonymousP);

	var toggleItemDiv = document.createElement("div");
	toggleItemDiv.setAttribute("class", "toggle-item");
	subrowDiv.appendChild(toggleItemDiv);

	var switchLabel = document.createElement("label");
	switchLabel.setAttribute("class", "switch");
	toggleItemDiv.appendChild(switchLabel);

	var switchInput = document.createElement("input");
	switchInput.setAttribute("type", "checkbox");
	switchInput.setAttribute("id", htmlID + "_" + clientInstance);
	switchInput.setAttribute("OnClick","sendControlState(event," + processID + "," + clientInstance + ");");
	switchInput.disabled = true;
	switchLabel.appendChild(switchInput);

	var sliderDiv = document.createElement("div");
	sliderDiv.setAttribute("class", "slider");
	switchLabel.appendChild(sliderDiv);
}