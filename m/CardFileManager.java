package m;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	private File currentCardSourceFile;
	private JFileChooser fileSelecter;
	private CramModelManager model;
	
	public CardFileManager(CramModelManager parentModel) {
		model = parentModel;
	}
	
	public File openAFile() {
		
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
		
		return fileSelecter.getSelectedFile();
	}
	
	public void saveFileProgress() {
		
		FileOutputStream fileOut;
			 
			try {
				fileOut = new FileOutputStream(currentCardSourceFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(model.getCurrentCards());
				out.close();
		        fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
	}
	
	public boolean cardSourceExists() {	
		if(currentCardSourceFile == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void outputFile(ArrayList<FlashCard> fileToSave, File file) {
		
		 try {
			 
			 FileOutputStream fileOut;
			 
			 if(file.toString().endsWith(".CRAMCARDS")) {
				fileOut = new FileOutputStream(file); 
			 } else {
				file = new File(file + ".CRAMCARDS");
				fileOut = new FileOutputStream(file);	 
			 }
	        
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(fileToSave);
	        	 
	         out.close();
	         fileOut.close();
	         currentCardSourceFile = file;
	         this.setChanged();
	         this.notifyObservers(true);

	     } catch (IOException i) {

	     }
		
	}
	
	public void saveFile(ArrayList<FlashCard> fileToSave) {
		
		File file = openAFile();
		
		if(file.exists()) {
			
		int x = JOptionPane.showConfirmDialog(null, "File exists, overwrite?", "Overwrite file", JOptionPane.YES_NO_OPTION);
       	
        if (x == JOptionPane.YES_OPTION) {
	       outputFile(fileToSave, file);
       	}
			
		} else {
		   outputFile(fileToSave, file);
		}
		
	}
		
	public void addAnObserver(Observer observer) {
		this.addObserver(observer);
	}
	
	public void setCurrentFile(File fileToSet) {
		currentCardSourceFile = fileToSet;
	}
	
	public void checkSelectedFile(File selectedFile) {
		
		if(selectedFile == null) {
			this.setChanged();
			this.notifyObservers(false);
		} else {
			currentCardSourceFile = selectedFile;
	
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

		FileInputStream fin = new FileInputStream(currentCardSourceFile);
		ObjectInputStream ois = new ObjectInputStream(fin);
		
		ArrayList<FlashCard> currentCards = (ArrayList<FlashCard>) ois.readObject();
		model.setCurrentCardset(currentCards);
		
		ois.close();
	}
		
}