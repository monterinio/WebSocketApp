//Auxilary functions for class 2 client
function client23SlidersFactory(name, attributeName, max, step, controlsDiv, processID, clientInstance) {
		
		var slidersDiv = document.createElement("div");
		slidersDiv.setAttribute("class", "sliders");
		controlsDiv.appendChild(slidersDiv);
		
		var anonymousDiv = document.createElement("div");
		slidersDiv.appendChild(anonymousDiv);
		
		var anonymousSpan = document.createElement("span");
		anonymousDiv.appendChild(anonymousSpan);
		
		var anonymousB = document.createElement("b");
		anonymousB.innerHTML = name;
		anonymousSpan.appendChild(anonymousB);
		
		var output = document.createElement("output");
		output.setAttribute("id", "output" + attributeName + "_" + clientInstance);
		output.value = 0;
		anonymousSpan.appendChild(output);
		
		var input = document.createElement("input");
		input.setAttribute("type", "range");
		input.setAttribute("min", "0");
		input.setAttribute("max", max.toString());
		input.setAttribute("step", step.toString());
		input.setAttribute("value", "0");
		input.setAttribute("id", "input" + attributeName + "_" + clientInstance);
		input.setAttribute("onchange", "output" + attributeName + "_" + clientInstance + ".value = input" + attributeName + "_" + clientInstance + ".value");
		input.setAttribute("onmouseup", "sendSliderState(event," + processID + "," + clientInstance + ",'" + "output" +  attributeName + "_" + clientInstance + "'"  + ");");
		input.disabled = true;
		slidersDiv.appendChild(input);
}