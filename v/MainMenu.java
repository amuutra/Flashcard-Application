package v;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

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
				// TODO Auto-generated method stub

				if(control.checkCardSource() == true) {
					parentView.showStudyingScreen();
				} else {
					
					control.selectCardSource();
					
					if(control.checkCardSource() == true) {
						parentView.showStudyingScreen();
					} else {
						
					}
					
					
					
				}
					
			}
			
		});
		
		
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
