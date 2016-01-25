package v;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import m.FlashCard;
import c.CramController;

public class StudyingScreen extends JPanel implements Observer {

	private JTextArea cardASide;
	private JTextArea cardBSide;
	
	private JButton revealAnswer;	
	private JButton wasCorrect;
	private JButton wasIncorrect;
	private JButton switchActiveSide;
	private JButton backToMainMenu;
	
	private FlashCard currentCard;	
	private CramController control;

	private boolean aSideActive = true;
	private boolean bSideActive = false;
	
	public StudyingScreen(CramController cramControl) {
		control = cramControl;
		addComponents();
		
		currentCard = control.getNextCard();
		setCardInfo();
	}
	
	private void setCardInfo() {
		
		if(aSideActive) {
			cardASide.setText(currentCard.getCardASide());
			cardBSide.setText("???");	
		}
		
		if(bSideActive) {
			cardASide.setText("???");
			cardBSide.setText(currentCard.getCardBSide());	
		}
			
		wasCorrect.setEnabled(false);
		wasIncorrect.setEnabled(false);
				
		this.revalidate();
		this.repaint();
	}
	
	private void addComponents() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(layout);
		
		cardASide = new JTextArea(5,20);
		cardBSide = new JTextArea(5,20);
		cardASide.setLineWrap(true);
		cardBSide.setLineWrap(true);
		cardASide.setFocusable(false);
		cardBSide.setFocusable(false);
		
		JScrollPane aSideScroll = new JScrollPane(cardASide);
		JScrollPane bSideScroll = new JScrollPane(cardBSide);
		
		revealAnswer = new JButton("Reveal answer");
		
		revealAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				wasCorrect.setEnabled(true);
				wasIncorrect.setEnabled(true);
				cardBSide.setText(currentCard.getCardBSide());
				control.saveFileProgress();
			}
			
		});
		
		wasCorrect = new JButton("Mark correct");
		wasCorrect.setEnabled(false);
		
		wasCorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.markCorrect();
				currentCard = control.getNextCard();
				setCardInfo();
			}
			
		});
		
		wasIncorrect = new JButton("Mark incorrect");
		wasIncorrect.setEnabled(false);
		
		wasIncorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.markIncorrect();
				currentCard = control.getNextCard();
				setCardInfo();
			}
			
		});
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(aSideScroll, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		this.add(bSideScroll, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(revealAnswer, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(wasCorrect, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(wasIncorrect, c);
		
		switchActiveSide = new JButton("Switch active side (Currently: " + getActiveCardSide() + ")");
		switchActiveSide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				if(aSideActive) {
					aSideActive = false;
					bSideActive = true;
				} else {
					bSideActive = false;
					aSideActive = true;
				}
				
				switchActiveSide.setText("Switch active side (Currently: " + getActiveCardSide() + ")");
				setCardInfo();
								
			}});
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		this.add(switchActiveSide, c);
		
		c.gridy = 4;
		backToMainMenu = new JButton("Back to main menu");
		backToMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.showMainMenu();
			}
			
		});
		this.add(backToMainMenu, c);
	}
	
	public String getActiveCardSide() {
		
		if(aSideActive) {
			return "A";
		} else {
			return "B";
		}
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {

	}	
}