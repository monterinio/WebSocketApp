package pl.pwr.model;

import java.util.Map;
import java.util.TreeMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import pl.pwr.websocket.WebsocketService;

@ApplicationScoped
public class AppData {

	@Inject
	WebsocketService websocketService;

	private Map<Integer, AppOneData> appOneData = new TreeMap<>();
	private Map<Integer, AppTwoData> appTwoData = new TreeMap<>();
	private Map<Integer, AppThreeData> appThreeData = new TreeMap<>();
	private Map<Integer, AppFourData> appFourData = new TreeMap<>();
	private Map<Integer, AppFiveData> appFiveData = new TreeMap<>();
	private Map<Integer, AppSixData> appSixData = new TreeMap<>();

	public void createAppData(int processID, int clientInstance) {
		switch (processID) {
		case 1:
			appOneData.put(clientInstance, new AppOneData());
			break;
		case 2:
			appTwoData.put(clientInstance, new AppTwoData());
			break;
		case 3:
			appThreeData.put(clientInstance, new AppThreeData());
			break;
		case 4:
			appFourData.put(clientInstance, new AppFourData());
			break;
		case 5:
			appFiveData.put(clientInstance, new AppFiveData());
			break;
		case 6:
			appSixData.put(clientInstance, new AppSixData());
			break;
		}
	}

	public void removeAppData(int processID, int clientInstance) {
		switch (processID) {
		case 1:
			appOneData.remove(clientInstance);
			break;
		case 2:
			appTwoData.remove(clientInstance);
			break;
		case 3:
			appThreeData.remove(clientInstance);
			break;
		case 4:
			appFourData.remove(clientInstance);
			break;
		case 5:
			appFiveData.remove(clientInstance);
			break;
		case 6:
			appSixData.remove(clientInstance);
			break;
		}
	}

	public AppOneData getAppOneData(int clientInstance) {
		return appOneData.get(clientInstance);
	}

	public void setAppOneData(int clientInstance, AppOneData appOneDataObject) {
		appOneData.put(clientInstance, appOneDataObject);
	}

	public AppTwoData getAppTwoData(int clientInstance) {
		return appTwoData.get(clientInstance);
	}

	public void setAppTwoData(int clientInstance, AppTwoData appTwoDataObject) {
		appTwoData.put(clientInstance, appTwoDataObject);
	}

	public AppThreeData getAppThreeData(int clientInstance) {
		return appThreeData.get(clientInstance);
	}

	public void setAppThreeData(int clientInstance, AppThreeData appThreeDataObject) {
		appThreeData.put(clientInstance, appThreeDataObject);
	}

	public AppFourData getAppFourData(int clientInstance) {
		return appFourData.get(clientInstance);
	}

	public void setAppFourData(int clientInstance, AppFourData appFourDataObject) {
		appFourData.put(clientInstance, appFourDataObject);
	}

	public AppFiveData getAppFiveData(int clientInstance) {
		return appFiveData.get(clientInstance);
	}

	public void setAppFiveData(int clientInstance, AppFiveData appFiveDataObject) {
		appFiveData.put(clientInstance, appFiveDataObject);
	}

	public AppSixData getAppSixData(int clientInstance) {
		return appSixData.get(clientInstance);
	}

	public void setAppSixData(int clientInstance, AppSixData appSixDataObject) {
		appSixData.put(clientInstance, appSixDataObject);
	}
	
	public boolean checkProcessConditions(int conditionProcessID, int conditionClientInstance, String element,
			String value, String relation) {
		System.out.println(conditionProcessID + " " + conditionClientInstance + " " + element + " " + value + " " + relation);
		if (element.equals("status")) {
			return websocketService.checkUserStatus(conditionProcessID, conditionClientInstance, value);
		}

		switch (conditionProcessID) {
		case 1:
			AppOneData appOneDataInstance = getAppOneData(conditionClientInstance);
			return appOneDataInstance.checkStatus(element, value);
		case 2:
			AppTwoData appTwoDataInstance = getAppTwoData(conditionClientInstance);
			return appTwoDataInstance.checkStatus(element, value, relation);
		case 3:
			AppThreeData appThreeDataInstance = getAppThreeData(conditionClientInstance);
			return appThreeDataInstance.checkStatus(element, value, relation);
		case 4:
			AppFourData appFourDataInstance = getAppFourData(conditionClientInstance);
			return appFourDataInstance.checkStatus(element, value, relation);
		case 5:
			AppFiveData appFiveDataInstance = getAppFiveData(conditionClientInstance);
			return appFiveDataInstance.checkStatus(element, value, relation);
		case 6:
			AppSixData appSixDataInstance = getAppSixData(conditionClientInstance);
			return appSixDataInstance.checkStatus(element, value, relation);
		}

		return false;
	}

	public void resetClientState(int processID, int clientInstance) {
		switch(processID) {
		case 1:
			appOneData.get(clientInstance).clear();
			break;
		case 2:
			appTwoData.get(clientInstance).clear();
			break;
		case 3:
			appThreeData.get(clientInstance).clear();
			break;
		case 4:
			appFourData.get(clientInstance).clear();
			break;
		case 5:
			appFiveData.get(clientInstance).clear();
			break;
		case 6:
			appSixData.get(clientInstance).clear();
			break;
		}
		
	}
	
	
}
