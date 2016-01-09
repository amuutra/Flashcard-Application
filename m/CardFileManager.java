package m;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class CardFileManager extends Observable {

	private File currentCardSource;
	private JFileChooser fileSelecter;
	
	private CramModelManager model;
	
	public CardFileManager(CramModelManager parentModel) {
		model = parentModel;
	}
	
	public boolean cardSourceExists() {
		
		if(currentCardSource == null) {
			return false;
		} else {
			return true;
		}

	}
	
	public void saveFile(ArrayList<FlashCard> fileToSave) {
			
		JFileChooser saveDialog = new JFileChooser();
		
		JFrame mainFrame = new JFrame();
		saveDialog.showSaveDialog(mainFrame);
		
		File file = saveDialog.getSelectedFile();
		
		 try {
			 
			 FileOutputStream fileOut;
			 
			 if(file.toString().endsWith(".CRAMCARDS")) {
				fileOut = new FileOutputStream(file); 
			 } else {
				fileOut = new FileOutputStream(file + ".CRAMCARDS");	 
			 }
	        
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	      
	         if(file.exists()) {
	        	 int x = JOptionPane.showConfirmDialog(null, "File exists, overwrite?", "Overwrite file", JOptionPane.YES_NO_OPTION);
	        	 
	        	 if (x == JOptionPane.YES_OPTION) {
			         out.writeObject(fileToSave);
	        	 }
	        	 
	         } else {
		         out.writeObject(fileToSave);	        	 
	         }

	         out.close();
	         fileOut.close();

	         currentCardSource = file;
	         this.setChanged();
	         this.notifyObservers(true);

	     } catch (IOException i) {

	     }
	
	}
		
	public void addAnObserver(Observer observer) {
		this.addObserver(observer);
	}
	
	public void setCurrentFile(File fileToSet) {
		currentCardSource = fileToSet;
	}

	public void selectAFile() {
		JFrame mainFrame = new JFrame();
		fileSelecter = new JFileChooser();
		
		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File file) {
				if(file.getName().endsWith(".CRAMCARDS")) {
					return true;
				} else {
					return false;
				}
			}

			@Override
			public String getDescription() {
				return null;
			}
			
		};
		
		fileSelecter.setFileFilter(filter);
		fileSelecter.setDialogTitle("Open card source");
		fileSelecter.showOpenDialog(mainFrame);
		
		checkSelectedFile(fileSelecter.getSelectedFile());
	}
	
	private void checkSelectedFile(File selectedFile) {
		if(selectedFile == null) {
			this.setChanged();
			this.notifyObservers(false);
		} else {
			currentCardSource = selectedFile;
			
			try {
				getCardsFromFile();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.setChanged();
			this.notifyObservers(true);
		}
		
	}
	
	public void getCardsFromFile() throws IOException, ClassNotFoundException {

		FileInputStream fin = new FileInputStream(currentCardSource);
		ObjectInputStream ois = new ObjectInputStream(fin);
		
		ArrayList<FlashCard> currentCards = (ArrayList<FlashCard>) ois.readObject();
		model.setCurrentCards(currentCards);
		
		ois.close();
	}
		
}