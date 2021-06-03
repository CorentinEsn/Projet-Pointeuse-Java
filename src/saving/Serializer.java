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


public class Serializer {

	private ObjectInputStream iS;
    private ObjectOutputStream oS;
    
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
	
	/*Save the Company's data, then access each dpt on the company's dpt list to save its data, 
	 then access each employee on the dpt's list to save its data
	 every time it creates another file for each class (company-dpt-employee) */
	public void serializeCompany(Company company) {
		
		File compFile = createOpenFile("CompanyFile.txt");
	}
	
	
	public void serializeWritePointeuseData(ArrayList<SerialPointeuse> tabData) {
		
		File dataFile = createOpenFile("PendingPointingData.dat");
		try {
			oS = new ObjectOutputStream(new FileOutputStream(dataFile));
			oS.writeObject(tabData);
			oS.close();	
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}

	@SuppressWarnings("unchecked") //for the ArrayList cast, because there's no reason it wouldn't be one if the file had been left untouched
	public ArrayList<SerialPointeuse> serializeReadPointeuseData() {

		File dataFile = createOpenFile("PendingPointingData.dat");
		//SerialPointeuse[] tabData = null;
		ArrayList<SerialPointeuse> arrayData = null;
		try {
			iS = new ObjectInputStream(new FileInputStream(dataFile));
			arrayData = (ArrayList<SerialPointeuse>) iS.readObject();
			
			iS.close();	
		}catch(EOFException e) {
			System.out.println("Fin de Fichier atteinte, fichier vide ?");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return arrayData;

	}
	
}
