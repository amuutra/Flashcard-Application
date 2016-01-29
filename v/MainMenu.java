package v;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import c.CramController;

public class MainMenu extends JPanel {

	private JButton startStudying;
	private JButton createCards;
	private CramViewManager parentView;
	private CramController control;
	
	public MainMenu(CramViewManager parent, CramController cramControl) {
		control = cramControl;
		parentView = parent;
		addComponents();
	}
	
	private void addComponents() {
				
		GridLayout layoutt = new GridLayout(0,1);
		this.setLayout(layoutt);
		
		startStudying = new JButton("Start studying");
		this.add(startStudying);
		startStudying.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(control.cardFileExists() == true) {
					parentView.showStudyingScreen();
				} else {
					
				control.selectCardSource();
					
				if(control.cardFileExists() == true) {
					parentView.showStudyingScreen();
				}
					
		}}});
		
		createCards = new JButton("Edit cards");
		this.add(createCards);
		createCards.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parentView.showCardCreationScreen();
			}
		});
			
	}
	
}