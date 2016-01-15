package mainPackage;

import m.CramModelManager;
import v.CramViewManager;
import c.CramController;

public class Start {

	private CramViewManager view;
	private CramController control;
	private CramModelManager model;
	
	public Start() {
		model = new CramModelManager();
		view = new CramViewManager();
		control = new CramController(model, view);
	}
	
	public void startCram() {
		control.showMainMenu();
	}
	
	public static void main(String[] args) {
		Start start = new Start();
		start.startCram();
	}

}
