function client5RandomNumberFlowGeneratorFactory(controlsDiv, processID, clientInstance) {
	
	var titleDiv = document.createElement("div");
	titleDiv.setAttribute("id", "title");
	controlsDiv.appendChild(titleDiv);
	
	var anonymousB = document.createElement("b");
	anonymousB.innerHTML = "Pomiar przepływu";
	titleDiv.appendChild(anonymousB);
	
	var measurementsDiv = document.createElement("div");
	measurementsDiv.setAttribute("id", "measurements");
	controlsDiv.appendChild(measurementsDiv);
	
	var measurementOutputDiv = document.createElement("div");
	measurementOutputDiv.setAttribute("id", "measurement-output");
	measurementsDiv.appendChild(measurementOutputDiv);
	
	var anonymousSpan = document.createElement("span");
	measurementOutputDiv.appendChild(anonymousSpan);
	
	anonymousB = document.createElement("b");
	anonymousB.innerHTML = "Przepływ: ";
	anonymousSpan.appendChild(anonymousB);
	
	
	var output = document.createElement("output");
	output.setAttribute("id", "outputFlow" + "_" + clientInstance);
	output.value = 0;
	anonymousSpan.appendChild(output);
	
	var measurementSliderDiv = document.createElement("div");
	measurementSliderDiv.setAttribute("id", "measurement-slider");
	measurementsDiv.appendChild(measurementSliderDiv);
	
	var input = document.createElement("input");
	input.setAttribute("type", "range");
	input.setAttribute("min", "0");
	input.setAttribute("max", "300");
	input.setAttribute("step", "5");
	input.setAttribute("value", "0");
	input.setAttribute("id", "inputFlow" + "_" + clientInstance);
	input.setAttribute("onchange", "outputFlow.value = inputFlow.value");
	input.disabled = true;
	measurementSliderDiv.appendChild(input);
	
	var measurementButtonContainerDiv = document.createElement("div");
	measurementButtonContainerDiv.setAttribute("id", "measurement-button-container");
	controlsDiv.appendChild(measurementButtonContainerDiv);
	
	var measurementButton = document.createElement("button");
	measurementButton.setAttribute("type", "button");
	measurementButton.setAttribute("id", "measurement-button" + "_" + clientInstance);
	measurementButton.setAttribute("OnClick", "measure(" + clientInstance + ")");
	measurementButton.disabled = true;
	measurementButton.innerHTML = "Wykonaj pomiar";
	measurementButtonContainerDiv.appendChild(measurementButton);
	
	
}