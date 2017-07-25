package pl.pwr.model;

public class AppSixData {
	private boolean contactor1a;
	private boolean contactor1b;
	private boolean contactor1c;
	private boolean contactor2a;
	private boolean contactor3a;
	private boolean contactor3b;
	
	public boolean isContactor1a() {
		return contactor1a;
	}
	public void setContactor1a(boolean contactor1a) {
		this.contactor1a = contactor1a;
	}
	public boolean isContactor1b() {
		return contactor1b;
	}
	public void setContactor1b(boolean contactor1b) {
		this.contactor1b = contactor1b;
	}
	public boolean isContactor1c() {
		return contactor1c;
	}
	public void setContactor1c(boolean contactor1c) {
		this.contactor1c = contactor1c;
	}
	public boolean isContactor2a() {
		return contactor2a;
	}
	public void setContactor2a(boolean contactor2a) {
		this.contactor2a = contactor2a;
	}
	public boolean isContactor3a() {
		return contactor3a;
	}
	public void setContactor3a(boolean contactor3a) {
		this.contactor3a = contactor3a;
	}
	public boolean isContactor3b() {
		return contactor3b;
	}
	public void setContactor3b(boolean contactor3b) {
		this.contactor3b = contactor3b;
	}
	public AppSixData() {

	}
	public void clear() {
		contactor1a = false;
		contactor1b = false;
		contactor1c = false;
		contactor2a = false;
		contactor3a = false;
		contactor3b = false;
	}
	public boolean checkStatus(String element, String value, String relation) {
		boolean valueAsBool = Boolean.valueOf(value);
		boolean comparedToValue = true;
		switch(element) {
			case "inputContactor1a":
				comparedToValue = contactor1a;
				break;
			case "inputContactor1b":
				comparedToValue = contactor1b;
				break;
			case "inputContactor1c":
				comparedToValue = contactor1c;
				break;
			case "inputContactor2a":
				comparedToValue = contactor2a;
				break;
			case "inputContactor3a":
				comparedToValue = contactor3a;
				break;
			case "inputContactor3b":
				comparedToValue = contactor3b;
				break;
		}
		return ((valueAsBool && comparedToValue) || (!valueAsBool && !comparedToValue));
	}
}
