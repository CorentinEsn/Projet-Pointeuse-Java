package saving;

import java.io.EOFException;
import java.io.File;  // Import the File class
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;// Import the IOException class to handle errors
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import environnementEntreprise.Company;
import pointeuse.SerialPointeuse;  

/**
 * 
 * @author Lilian and Corentin
 *
 * Will Serialize data for multiple structures, like the pointeuse or the company class
 * all the file names and dirs are already preselected for now
 */
public class Serializer {

	/**
	 * the input stream that will read from files
	 */
	private ObjectInputStream iS;
	/**
	 * the output stream that will write to files
	 */
    private ObjectOutputStream oS;
    
    /**
     * @brief Open a file and create it if it doesn't exist
     * @param fileName the name of the file to open or create and open
     * @return a File object with the file open
     */
	public File createOpenFile(String fileName) {
		
		File nameFile = null;
		
		//create a file
		try {
			nameFile = new File(fileName);
			if (nameFile.createNewFile()) {
				System.out.println("File created: " + nameFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return nameFile;
	}
	
	/** Save the Company's data, then access each dpt on the company's dpt list to save its data, 
	 * then access each employee on the dpt's list to save its data
	 * every time it creates another file for each class (company-dpt-employee) 
	 *
	 * @param company the company to serialize
	 */
	public void serializeCompany(Company company) {
		
		File compFile = createOpenFile("CompanyFile.txt");
	}
	
	/**
	 * @brief Serialize the data from the "pointeuse" in a file in "data/PendingPointingData.dat"
	 * 
	 * will create the directory if it doesn't exist
	 * 
	 * @param tabData the Array of data not sent to serialize
	 */
	public void serializeWritePointeuseData(ArrayList<SerialPointeuse> tabData) {
		
		File directory = new File("data");
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	    
		File dataFile = createOpenFile("data"+File.separator+"PendingPointingData.dat");
		try {
			oS = new ObjectOutputStream(new FileOutputStream(dataFile));
			oS.writeObject(tabData);
			oS.close();	
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * @brief read the serialized data to create an Arraylist with all of it
	 * @return an Arraylist with all the data not sent previously
	 */
	@SuppressWarnings("unchecked") //for the ArrayList cast, because there's no reason it wouldn't be one if the file had been left untouched
	public ArrayList<SerialPointeuse> serializeReadPointeuseData() {

		File directory = new File("data");
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	    
		File dataFile = createOpenFile("data"+File.separator+"PendingPointingData.dat");
		//SerialPointeuse[] tabData = null;
		ArrayList<SerialPointeuse> arrayData = null;
		try {
			iS = new ObjectInputStream(new FileInputStream(dataFile));
			arrayData = (ArrayList<SerialPointeuse>) iS.readObject();
			
			iS.close();	
		}catch(EOFException e) {
			System.out.println("Fin de Fichier atteinte, fichier vide ?");
		}catch(ClassNotFoundException e) {
			//this could happen if the file has been modified, or if there was some difference between the classes version
			e.printStackTrace();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return arrayData;

	}
	
}