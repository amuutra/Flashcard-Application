package v;

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
	
	private JTextArea cardASideText;
	private JTextArea cardBSideText;
	//private JButton submitButton;
	private JButton createNewSet;
	private JButton openExistingSet;
	private CramController control;
	private JButton backButton;
	private JButton saveSet;
	private JPanel cardAddingPanel;
	private JPanel startingPanel;
	private JScrollPane scrollingPanel;	
	private JPanel cardCreationPanel;	
	
	private ArrayList<JPanel> cardCreationPanels;
	
	public CardCreationScreen(CramController cramControl) {

		cardCreationPanels = new ArrayList();
		
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
	
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);

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
		
		startingPanel.add(createNewSet);
		startingPanel.add(openExistingSet);
		startingPanel.add(backButton);
		startingPanel.add(saveSet);
		
		this.add(startingPanel);

	}

	private void createCard(String sideA, String sideB) {
		control.createACard(sideA, sideB);
	}

	private void fillScreen() {
		
		ArrayList<FlashCard> CardSet = control.getCurrentCardSet();
		
		for(int i = 0; i < CardSet.size(); i++) {
			addCardCreationPanel(CardSet.get(i).getCardASide(), CardSet.get(i).getCardBSide());
		}
		
		}
	
	
/*			
 * 		Add card creation panel
 */
	int x = 0;
	private void addCardCreationPanel(String ASideText, String BSideText) {
		
		x = x + 1;
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
		
	/*	submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createCard(cardASideText.getText(), cardBSideText.getText());
				addCardCreationPanel(" ", " ");
						
				SwingUtilities.invokeLater(new Runnable() {
					  public void run() {
					   scrollingPanel.getVerticalScrollBar().setValue(scrollingPanel.getVerticalScrollBar().getMaximum());
					  }
					 }
					);
				
			}		
		});   */
	
		cardCreationPanel.add(cardASideInputScroller);
		cardCreationPanel.add(cardBSideInputScroller);
		//cardCreationPanel.add(submitButton);
		
		cardAddingPanel.add(cardCreationPanel);
		
		if(x<5) {
			JFrame bla = (JFrame) this.getTopLevelAncestor();
			bla.pack();
			bla.setLocationRelativeTo(null);
		}
		
		cardASideText.grabFocus();
	/*	scrollingPanel.repaint();
		this.repaint();
		this.revalidate(); */
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