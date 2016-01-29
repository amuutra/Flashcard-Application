package v;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import m.FlashCard;
import c.CramController;

public class CardCreationScreen extends JPanel {
	
	private CramController control;
	
	private JTextArea cardASideText;
	private JTextArea cardBSideText;
	private JButton addNewCardButton;
	private JButton createNewSet;
	private JButton openExistingSet;
	private JButton backButton;
	private JButton saveSet;
	private JPanel cardAddingPanel;
	private JPanel startingPanel;
	private JPanel cardCreationPanel;	
	private JScrollPane scrollingPanel;	
	private int panelsCreated = 0;
	
	private ArrayList<JPanel> cardCreationPanels;
	
	public CardCreationScreen(CramController cramControl) {

		cardCreationPanels = new ArrayList<JPanel>();
		
		control = cramControl;
		addStartingPanel();
		
		cardAddingPanel = new JPanel();
		BoxLayout layout = new BoxLayout(cardAddingPanel, BoxLayout.Y_AXIS);
		cardAddingPanel.setLayout(layout);
	
		scrollingPanel = new JScrollPane(cardAddingPanel);
		scrollingPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollingPanel);
		
	}
	
	private void addStartingPanel() {

		startingPanel = new JPanel();
	
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		startingPanel.setLayout(layout);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		createNewSet = new JButton("Create new set");
		openExistingSet = new JButton("Open existing set");
		
		createNewSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createNewSet.setEnabled(false);
				openExistingSet.setEnabled(false);
				addCardCreationPanel("", "");
				control.createNewCardSet();
				saveSet.setEnabled(true);
				addNewCardButton.setEnabled(true);
			}
			
		});
		
		openExistingSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.selectCardSource();
				saveSet.setEnabled(true);
				fillScreen();
			}	
		});
		
		backButton = new JButton("Go back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				int a = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure? Unsaved changes will be lost", "Confirm", JOptionPane.YES_NO_OPTION);

				if (a == JOptionPane.YES_OPTION) {
					control.showMainMenu();	
				}
				
			}
			
		});
		
		saveSet = new JButton("Save set");
		saveSet.setEnabled(false);
		
		saveSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				control.createNewCardSet();
				createCards();
				control.saveFile();
			}		
		});
		
		addNewCardButton = new JButton("Add new card");
		addNewCardButton.setEnabled(false);
		addNewCardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addCardCreationPanel("", "");
				
				SwingUtilities.invokeLater(new Runnable() {
					  public void run() {
					   scrollingPanel.getVerticalScrollBar().setValue(scrollingPanel.getVerticalScrollBar().getMaximum());
					  }
					 });
			}	
		});
		
		c.gridx = 0;
		c.gridy = 0;
		
		startingPanel.add(createNewSet, c);
		
		c.gridx = 1;
		c.gridy = 0;
		
		startingPanel.add(openExistingSet, c);
		
		c.gridx = 2;
		c.gridy = 0;
		
		startingPanel.add(backButton, c);
		
		c.gridx = 3;
		c.gridy = 0;
		
		startingPanel.add(saveSet, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		startingPanel.add(addNewCardButton, c);
		
		this.add(startingPanel);

	}

	private void fillScreen() {
		
		ArrayList<FlashCard> CardSet = control.getCurrentCardSet();
		
		for(int i = 0; i < CardSet.size(); i++) {
			addCardCreationPanel(CardSet.get(i).getCardASide(), CardSet.get(i).getCardBSide());
		}
		
	}
	
	private void addCardCreationPanel(String ASideText, String BSideText) {
		
		panelsCreated = panelsCreated + 1;
		cardCreationPanel = new JPanel();
		addToPanelList(cardCreationPanel);
		
		cardCreationPanel.setFocusTraversalKeysEnabled(false);
		
		cardASideText = new JTextArea(5,20);
		
		cardASideText.setLineWrap(true);
		cardASideText.setText(ASideText);
		JScrollPane cardASideInputScroller = new JScrollPane(cardASideText);
		
		cardBSideText = new JTextArea(5,20);
		cardBSideText.setLineWrap(true);
		cardBSideText.setText(BSideText);
		JScrollPane cardBSideInputScroller = new JScrollPane(cardBSideText);
		
		cardASideText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent evt) {	

				if(evt.getKeyChar() == KeyEvent.VK_TAB) {
					cardBSideText.requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {	
			}

			@Override
			public void keyTyped(KeyEvent evt) {
			}
			
		});
		
		cardBSideText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent evt) {
			
				if(evt.getKeyChar() == KeyEvent.VK_TAB) {
					addCardCreationPanel("","");
					
					SwingUtilities.invokeLater(new Runnable() {
						  public void run() {
						   scrollingPanel.getVerticalScrollBar().setValue(scrollingPanel.getVerticalScrollBar().getMaximum());
						  }
						 });
					
				}
					
			}

			@Override
			public void keyReleased(KeyEvent arg0) {	
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
			
		cardCreationPanel.add(cardASideInputScroller);
		cardCreationPanel.add(cardBSideInputScroller);
		
		cardAddingPanel.add(cardCreationPanel);
		
		if(panelsCreated<5) {
			JFrame topFrame = (JFrame) this.getTopLevelAncestor();
			topFrame.pack();
		}
		
		cardASideText.grabFocus();
		scrollingPanel.revalidate();		
	}		
	
	public void addToPanelList(JPanel panelToAdd) {
		cardCreationPanels.add(panelToAdd);
	}
	
	public void createCards() {
			
		for(int i = 0; i < cardCreationPanels.size(); i++) {
			
			String aSide = "";
			String bSide = "";
			
			for(int j = 0; j < cardCreationPanels.get(0).getComponentCount(); j++) {
				
				if(cardCreationPanels.get(i).getComponent(j) instanceof JScrollPane) {
					JScrollPane scrollPane = (JScrollPane) cardCreationPanels.get(i).getComponent(j);
					JViewport viewport = scrollPane.getViewport(); 
					JTextArea textArea = (JTextArea)viewport.getView();		
					
					if(aSide.isEmpty()) {
						aSide = textArea.getText();
					} else {
						bSide = textArea.getText();
						control.createACard(aSide, bSide);
					}
					
				}
				
			}
			
		}
	
	}
			
}