package saving;

import java.io.File;  // Import the File class
import java.io.IOException;// Import the IOException class to handle errors

import environnementEntreprise.Company;
import pointeuse.SerialPointeuse;  


public class Serializer {


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
	
	
	public void serializePointeuseData(SerialPointeuse[] tabData) {
		
	}
	
}
