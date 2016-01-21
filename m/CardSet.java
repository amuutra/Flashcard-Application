package m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Random;

public class CardSet extends Observable {
	
	private CramModelManager model;

	private ArrayList<FlashCard> currentCardSet;
	private ArrayList<FlashCard> learnedCards;
	private FlashCard currentCard;
	private Random rand;
	
	public CardSet(CramModelManager cramModel) {
		model = cramModel;
		rand = new Random();
	}
	
	public int getCurrentLowestLevel() {
		
		int result = 0;
		
		for(int i = 0; i<currentCardSet.size(); i++) {
			
			if(i == 0) {
				result = currentCardSet.get(i).getCurrentLevel();
			} else if(currentCardSet.get(i).getCurrentLevel() < result) {
				result = currentCardSet.get(i).getCurrentLevel();
			}
		}
		
		return result;
	}
	
	//Returns the first card at the lowest learned level
	public FlashCard getNextCard() {
			
		int lowestLevel = getCurrentLowestLevel();
		ArrayList<FlashCard> lowestCards = new ArrayList<FlashCard>();
	
		for(int i = 0; i<currentCardSet.size(); i++) {
			if(currentCardSet.get(i).getCurrentLevel() == lowestLevel ) {
				currentCard = currentCardSet.get(i);
				lowestCards.add(currentCard);
			}
		}
		
		rand.nextInt(lowestCards.size());
		currentCard = lowestCards.get(rand.nextInt(lowestCards.size()));
		return currentCard;
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