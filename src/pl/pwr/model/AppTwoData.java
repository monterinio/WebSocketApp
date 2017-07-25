package pl.pwr.model;

public class AppTwoData {
	private double pressureInput;
	private double velocityInput;
	
	public double getPressureInput() {
		return pressureInput;
	}
	public void setPressureInput(double pressureInput) {
		this.pressureInput = pressureInput;
	}
	public double getVelocityInput() {
		return velocityInput;
	}
	public void setVelocityInput(double velocityInput) {
		this.velocityInput = velocityInput;
	}
	
	public AppTwoData() { }
	public boolean checkStatus(String element, String value, String relation) {
		double valueAsDouble = Double.valueOf(value);
		double measuredValue = 0;
		
		switch(element) {
		case "inputVelocity":
			measuredValue = velocityInput;
			break;
		case "inputPressure":
			measuredValue = pressureInput;
			break;
		}
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
	}
	
	public void clear() {
		pressureInput = 0;
		velocityInput = 0;
	};
}
