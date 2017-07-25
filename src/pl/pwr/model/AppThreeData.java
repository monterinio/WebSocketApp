package pl.pwr.model;

public class AppThreeData {
	private double voltage1;
	private double voltage2;
	
	public double getVoltage1() {
		return voltage1;
	}
	public void setVoltage1(double controlValue) {
		this.voltage1 = controlValue;
	}
	public double getVoltage2() {
		return voltage2;
	}
	public void setVoltage2(double voltage2) {
		this.voltage2 = voltage2;
	}
	
	public AppThreeData() { }
	public boolean checkStatus(String element, String value, String relation) {
		double valueAsDouble = Double.valueOf(value);
		double measuredValue = 0;
		
		switch(element) {
		case "inputVoltage1":
			measuredValue = voltage1;
			break;
		case "inputVoltage2":
			measuredValue = voltage2;
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
		voltage1 = 0;
		voltage2 = 0;
	};
}
