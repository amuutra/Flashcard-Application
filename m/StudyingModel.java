package m;

import java.util.Observable;

public class StudyingModel extends Observable {

	private CardSet currentCards;
	private FlashCard currentCard;
	
	public StudyingModel(CardSet currentSet) {
		currentCards = currentSet;
	}
		
	public void setCardSet(CardSet setToSet) {
		currentCards = setToSet;
	}
	
	public void startStudying() {
		getNextCard();
	}
	
	public FlashCard getNextCard() {
		currentCard = currentCards.getNextCard();
		this.setChanged();
		this.notifyObservers(currentCard);
		return currentCard;
	}
	
	public void markCorrect() {
		currentCards.getCurrentCard().answeredCorrectly();
	}
	
	public void markIncorrect() {
		currentCards.getCurrentCard().answeredIncorrectly();
	}
	
}