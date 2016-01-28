package v;

import javax.swing.JFrame;

import m.CramModelManager;
import c.CramController;

public class CramViewManager {

	private JFrame mainFrame;
	
	private MainMenu mainmenu;
	private StudyingScreen studyscreen;
	private CardCreationScreen cardScreen;
	
	private CramModelManager model;
	
	private CramController control;
	
	public CramViewManager() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
	}
	
	public JFrame getFrame() {
		return mainFrame;
	}
	
	public void addController(CramController cramControl) {
		control = cramControl;
	}

	public void showMainMenu() {
		mainmenu = new MainMenu(this, control);
		mainFrame.getContentPane().removeAll();
		mainFrame.add(mainmenu);
		
		mainFrame.setResizable(true);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public void showStudyingScreen() {
		studyscreen = new StudyingScreen(control);
		mainFrame.getContentPane().removeAll();
		mainFrame.add(studyscreen);
		
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
	}
	
	public void showCardCreationScreen() {

		mainFrame.getContentPane().removeAll();
		cardScreen = new CardCreationScreen(control);
		mainFrame.add(cardScreen);
		
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
	}
			
}