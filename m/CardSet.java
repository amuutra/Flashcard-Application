package m;

import java.util.ArrayList;
import java.util.Observable;

public class CardSet extends Observable {
	
	private CramModelManager model;

	private ArrayList<FlashCard> currentCardSet;
	private ArrayList<FlashCard> learnedCards;
	private FlashCard currentCard;

	public CardSet(CramModelManager cramModel) {
		model = cramModel;
	}
	
	//Returns the first card at the lowest learned level
	public FlashCard getNextCard() {
			
		for(int i = 0; i<currentCardSet.size(); i++) {
			if(currentCardSet.get(i).getCurrentLevel() == 1) {
				currentCard = currentCardSet.get(i);
				return currentCardSet.get(i);
			}
		}
		
		for(int i = 0; i<currentCardSet.size(); i++) {
			if(currentCardSet.get(i).getCurrentLevel() == 2) {
				currentCard = currentCardSet.get(i);
				return currentCardSet.get(i);
			}
		}
		
		for(int i = 0; i<currentCardSet.size(); i++) {
			if(currentCardSet.get(i).getCurrentLevel() == 3) {
				currentCard = currentCardSet.get(i);
				return currentCardSet.get(i);
			}
		}
		
		for(int i = 0; i<currentCardSet.size(); i++) {
			if(currentCardSet.get(i).getCurrentLevel() == 4) {
				currentCard = currentCardSet.get(i);
				return currentCardSet.get(i);
			}
		}
		
		for(int i = 0; i<currentCardSet.size(); i++) {
			if(currentCardSet.get(i).getCurrentLevel() == 5) {
				currentCard = currentCardSet.get(i);
				return currentCardSet.get(i);
			}
		}
		
		return currentCardSet.get(0);
	}
	
	private void saveFile() {
		model.saveFile();
	}
	
	public FlashCard getCurrentCard() {
		return currentCard;
	}
	
	public ArrayList<FlashCard> getCurrentSet() {
		return currentCardSet;
	}
	
	public void setCurrentSet(ArrayList<FlashCard> set) {
		currentCardSet = set;
	}
	
	private void addToLearned(FlashCard learnedCard) {
		
		if(learnedCards == null) {
			learnedCards = new ArrayList<FlashCard>();
		}
		
		currentCardSet.remove(currentCard);
		learnedCards.add(learnedCard);
	}
	
	public boolean cardSetExists() {
		if (currentCardSet == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void createNewCardSet() {
		currentCardSet = new ArrayList<FlashCard>();
	}
	
	public void addCard(String sideA, String sideB) {
		FlashCard x = new FlashCard(sideA, sideB);
		currentCardSet.add(x);
		setChanged();
		notifyObservers(currentCardSet);
	}
	
	public void removeCard(FlashCard cardToRemove) {
		currentCardSet.remove(cardToRemove);
	}
			
}