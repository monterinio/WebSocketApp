package pl.pwr.model;

public class AppFiveData {
	private int flowData;

	public int getFlowData() {
		return flowData;
	}

	public void setFlowData(int flowData) {
		this.flowData = flowData;
	}
	
	public AppFiveData() {	}

	public void clear() {
		flowData = 0;
	}

	public boolean checkStatus(String element, String value, String relation) {
		double valueAsDouble = Double.parseDouble(value);
		double measuredValue = flowData;
		switch(relation) {
		case "lesser":
			if(measuredValue < valueAsDouble) return true;
			break;
		case "equal":
			if(measuredValue == valueAsDouble) return true;
			break;
		case "greater":
			if(measuredValue > valueAsDouble) return true;
			break;
		}
		return false;
	};
}
