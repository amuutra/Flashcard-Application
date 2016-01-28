package m;

import java.util.Observable;
import java.util.Observer;

public class StudyingModel extends Observable {

	private CardSet currentCards;
	private FlashCard currentCard;
	
	public StudyingModel(CardSet currentSet ) {
		currentCards = currentSet;
	}
	
	public void setCardSet(CardSet setToSet) {
		currentCards = setToSet;

	}
	
	public void startStudying() {
		getNextCard();
	}
	
	private void getNextCard() {
		currentCard = currentCards.getNextCard();
		this.setChanged();
		this.notifyObservers(currentCard);
	}
	
	public void markCorrect() {
		currentCards.getCurrentCard().answeredCorrectly();
	}
	
	public void markIncorrect() {
		currentCards.getCurrentCard().answeredIncorrectly();
	}

	
	
}
