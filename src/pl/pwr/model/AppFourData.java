package pl.pwr.model;

public class AppFourData {
	private int firstTurn;
	private int secondTurn;
	private int thirdTurn;
	private int turnNumber;
	
	public int getFirstTurn() {
		return firstTurn;
	}
	public void setFirstTurn(int firstTurn) {
		this.firstTurn = firstTurn;
	}
	public int getSecondTurn() {
		return secondTurn;
	}
	public void setSecondTurn(int secondTurn) {
		this.secondTurn = secondTurn;
	}
	public int getThirdTurn() {
		return thirdTurn;
	}
	public void setThirdTurn(int thirdTurn) {
		this.thirdTurn = thirdTurn;
	}
	
	public void incrementTurn() {
		turnNumber++;
		if(turnNumber == 3) {
			turnNumber = 0;
		} 
	}
	
	public int getTurn() {
		return turnNumber;
	}
	
	public int resetTurns() {
		turnNumber = 0;
		return turnNumber;
	}
	
	public AppFourData() { 
		firstTurn = 0;
		secondTurn = 0;
		thirdTurn = 0;
		turnNumber = 0;
	}
	public boolean checkStatus(String element, String value, String relation) {
		double valueAsDouble = Integer.valueOf(value);
		double measuredValue = 0;
		switch(element) {
		case "first-turn":
			measuredValue = firstTurn;
			break;
		case "second-turn":
			measuredValue = secondTurn;
			break;
		case "third-turn":
			measuredValue = thirdTurn;
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
		firstTurn = 0;
		secondTurn = 0;
		thirdTurn = 0;
		turnNumber = 0;
	};
}
