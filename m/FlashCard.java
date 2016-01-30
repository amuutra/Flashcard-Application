package m;

import java.io.Serializable;

public class FlashCard implements Serializable {

	private String cardSideA;
	private String cardSideB;
	
	private int answeredCorrectly = 0;
	private int currentLevel = 1;
	
	private boolean learned = false;
	
	public FlashCard(String ASide, String BSide) {
		cardSideA = ASide;
		cardSideB = BSide;
	}
	
	public String getCardASide() {
		return cardSideA;
	}
	
	public String getCardBSide() {
		return cardSideB;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCardSideA(String input) {
		cardSideA = input;
	}
	
	public void setCardSideB(String input) {
		cardSideB = input;
	}
	
	public void answeredCorrectly() {
		currentLevel = currentLevel + 1;
		answeredCorrectly = answeredCorrectly + 1;
		
		if(currentLevel == 5) {
			learned = true;
		}
		
	}
	
	public void answeredIncorrectly() {
		currentLevel = 1;
	}
		
}