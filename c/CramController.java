package c;

import java.util.ArrayList;
import java.util.Observer;

import v.CramViewManager;
import m.CramModelManager;
import m.FlashCard;

public class CramController {

	private CramViewManager view;
	private CramModelManager model;
	
	public CramController(CramModelManager cramModel, CramViewManager cramView) {
		view = cramView;	
		model = cramModel;
		view.addController(this);
	}
	
	public FlashCard getNextCard() {
		return model.getNextCard();
	}
	
	public void selectCardSource() {
		model.selectCardSource();
	}
	
	public void saveFile() {
		model.saveFile();
	}
	
	public boolean checkCardSource() {
		
		if (model.cardFileExists()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void markCorrect() {
		model.markCorrect();
	}
	
	public void markIncorrect() {
		model.markIncorrect();
	}
	
	public void createACard(String sideA, String sideB) {
		model.addACard(sideA, sideB);
	}
	
	public void observeFileManager(Observer obs) {
		model.observeFileManager(obs);
	}
	
	public void observeCardSetBuilder(Observer obs) {
		model.observeCardSetBuilder(obs);
	}
	
	public void observeStudyModel(Observer obs) {
		model.observeStudyingModel(obs);
	}
	
	public void addController(CramViewManager view) {
		view.addController(this);
	}
	
	public void showMainMenu() {
		view.showMainMenu();
	}
	
	public void showStudyingScreen() {
		view.showStudyingScreen();
	}
	
	public void showCardEditScreen() {
		view.showCardCreationScreen();
	}
	
	public void createNewCardSet() {
		model.createNewCardSet();
	}
	
	public ArrayList<FlashCard> getCurrentCardSet() {
		return model.getCurrentCards();
	}
		
}