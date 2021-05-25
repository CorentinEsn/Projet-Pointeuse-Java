package saving;

import java.io.File;  // Import the File class
import java.io.IOException;

import environnementEntreprise.Company;  // Import the IOException class to handle errors


public class Serializer {

/*Save the Company's data, then access each dpt on the company's dpt list to save its data, 
 then access each employee on the dpt's list to save its data
 every time it creates another file for each class (company-dpt-employee) */
	public void Serializer(Company company) {
		
		//create a file
		try {
		      File company = new File("filename.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
