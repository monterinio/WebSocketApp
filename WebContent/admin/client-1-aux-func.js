//Auxilary functions for class 1 client
function client1RowFactory(title, htmlID, x, controlsDiv, processID, processInstance) {
		var rowDiv = document.createElement("div");
		rowDiv.setAttribute("class", "row");
		controlsDiv.appendChild(rowDiv);
		
		var labelDiv = document.createElement("div");
		labelDiv.setAttribute("class", "label");
		rowDiv.appendChild(labelDiv);
		
		var valveParagraph1 = document.createElement("p");
		valveParagraph1.innerHTML = title;
		labelDiv.appendChild(valveParagraph1);
		
		x(rowDiv, htmlID, processID, processInstance);
}

function client1ToggleElementFactory(rowDiv, htmlID, processID, processInstance) {
		var toggleItem = document.createElement("div");
		toggleItem.setAttribute("class", "toggle-item");
		rowDiv.appendChild(toggleItem);
		
		var switchLabel = document.createElement("label");
		switchLabel.setAttribute("class", "switch");
		toggleItem.appendChild(switchLabel);
		
		var switchInput = document.createElement("input");
		switchInput.setAttribute("type", "checkbox");
		switchInput.setAttribute("id", htmlID + "_" + processInstance);
		switchInput.setAttribute("onclick", "sendControlState(event," + processID + "," + processInstance + ");");
		switchInput.disabled = true;
		switchLabel.appendChild(switchInput);
		
		var sliderDiv = document.createElement("div");
		sliderDiv.setAttribute("class", "slider");
		switchLabel.appendChild(sliderDiv);
}

function client1RadioBoxFactory(rowDiv, htmlID, processID, processInstance) {
		var item = document.createElement("div");
		item.setAttribute("class", "item");
		rowDiv.appendChild(item);
		
		for(i=0; i<2; i++) {
			var radioInput = document.createElement("input");
			radioInput.setAttribute("type", "radio");
			radioInput.setAttribute("name", "silnik" + "_" + processInstance);
			radioInput.setAttribute("id", htmlID[i] + "_" + processInstance);
			radioInput.setAttribute("onclick", "sendControlState(event," + processID + "," + processInstance + ");");
			radioInput.disabled = true;
			item.appendChild(radioInput);
			
			var radioText = document.createTextNode("Silnik "+i);
			item.appendChild(radioText);
			
			var radioNewLine = document.createElement("BR");
			item.appendChild(radioNewLine);
		}
}

function client1CheckBoxFactory(rowDiv, htmlID, processID, processInstance) {
		var item = document.createElement("div");
		item.setAttribute("class", "item");
		rowDiv.appendChild(item);
		var processName = ["Oddymianie", "Filtrowanie"];
		
		for(i=0; i<2; i++) {
			var checkInput = document.createElement("input");
			checkInput.setAttribute("type", "checkbox");
			checkInput.setAttribute("name", "warunki" + "_" + processInstance);
			checkInput.setAttribute("id", htmlID[i] + "_" + processInstance);
			checkInput.setAttribute("onclick", "sendControlState(event," + processID + "," + processInstance + ");");
			checkInput.disabled = true;
			item.appendChild(checkInput);
			
			var checkText = document.createTextNode(processName[i]);
			item.appendChild(checkText);
			
			var checkNewLine = document.createElement("BR");
			item.appendChild(checkNewLine);
		}	
}