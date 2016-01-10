package m;

import java.util.ArrayList;
import java.util.Observer;

public class CramModelManager {

	private CardFileManager fileManager;
	private StudyingModel studyModel;
	private CardSet setBuilder;
	
	public CramModelManager() {
		fileManager = new CardFileManager(this);
		setBuilder = new CardSet(this);	
	}
	
	public FlashCard getNextCard() {
		return setBuilder.getNextCard();
	}
	
	public void saveFile() {
		fileManager.saveFile(setBuilder.getCurrentSet());
	}
	
	public void selectCardSource() {
		fileManager.selectAFile();
	}
	
	public void setCurrentCards(ArrayList<FlashCard> set) {
		setBuilder.setCurrentSet(set);
	}
	
	public ArrayList<FlashCard> getCurrentCards() {
		return setBuilder.getCurrentSet();
	}
	
	public boolean cardFileExists() {
	
		if(fileManager.cardSourceExists()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void markCorrect() {
		setBuilder.getCurrentCard().answeredCorrectly();
	}
	
	public void markIncorrect() {
		setBuilder.getCurrentCard().answeredIncorrectly();
	}
	
	public void addACard(String sideA, String sideB) {
		setBuilder.addCard(sideA, sideB);
	}
	
	public void createNewCardSet() {
		setBuilder.createNewCardSet();
	}
	
	public void observeFileManager(Observer obs) {
		fileManager.addObserver(obs);
	}
	
	public void observeStudyingModel(Observer obs) {
		studyModel.addObserver(obs);
	}
	
	public void observeCardSetBuilder(Observer obs) {
		setBuilder.addObserver(obs);
	}
	
}
