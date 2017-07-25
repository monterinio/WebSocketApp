package pl.pwr.model;

public class AppOneData {
	private boolean valve1;
	private boolean valve2;
	private boolean motorA;
	private boolean motorB;
	private boolean exhaust;
	private boolean filter;
	
	public boolean isValve1() {
		return valve1;
	}
	public void setValve1(boolean valve1) {
		this.valve1 = valve1;
	}
	public boolean isValve2() {
		return valve2;
	}
	public void setValve2(boolean valve2) {
		this.valve2 = valve2;
	}
	public boolean isMotorA() {
		return motorA;
	}
	public void setMotorA(boolean motorA) {
		this.motorA = motorA;
	}
	public boolean isMotorB() {
		return motorB;
	}
	public void setMotorB(boolean motorB) {
		this.motorB = motorB;
	}
	public boolean isExhaust() {
		return exhaust;
	}
	public void setExhaust(boolean exhaust) {
		this.exhaust = exhaust;
	}
	public boolean isFilter() {
		return filter;
	}
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
	
	public AppOneData() {
		
	}
	public boolean checkStatus(String element, String value) {
		boolean valueAsBool = Boolean.valueOf(value);
		boolean comparedToValue = true;
		switch(element) {
			case "inputValve1":
				comparedToValue = valve1;
				break;
			case "inputValve2":
				comparedToValue = valve2;
				break;
			case "inputMotor3":
				comparedToValue = motorA;
				break;
			case "inputMotor4":
				comparedToValue = motorB;
				break;
			case "inputCheckbox5":
				comparedToValue = exhaust;
				break;
			case "inputCheckbox6":
				comparedToValue = filter;
				break;
		}
		return ((valueAsBool && comparedToValue) || (!valueAsBool && !comparedToValue));

		
	}
	
	public void clear() {
		valve1 = false;
		valve2 = false;
		motorA = false;
		motorB = false;
		exhaust = false;
		filter= false;
	}
}
