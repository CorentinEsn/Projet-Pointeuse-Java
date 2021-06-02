package saving;

import java.io.File;  // Import the File class
import java.io.IOException; // Import the IOException class to handle errors

import environnementEntreprise.Company;  


public class Serializer {

	/*Save the Company's data, then access each dpt on the company's dpt list to save its data, 
 then access each employee on the dpt's list to save its data
 every time it creates another file for each class (company-dpt-employee) */
	public void Serializer(Company company) {

		//create a file the same name of the company's

		try {
			File companyFile = new File(company.getName()+".csv");
			if (companyFile.createNewFile()) {
				System.out.println("File created: " + companyFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
