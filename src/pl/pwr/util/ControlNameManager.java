package pl.pwr.util;

public final class ControlNameManager {
	private ControlNameManager() { };
	
	public static String adjustClientControlName(String controlName) {
		if(controlName.contains("_")) {
			int endIndex = controlName.lastIndexOf("_");
			controlName = controlName.substring(0, endIndex);
		}
		return controlName;
	}
	
	public static String adjustMasterClientControlName(String controlName, int clientInstance) {
		if(!controlName.contains("_")) {
			controlName = controlName + "_" + clientInstance;
		}
		return controlName;
	}
}
