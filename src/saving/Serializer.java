package saving;

import java.io.EOFException;
import java.io.File;  // Import the File class
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;// Import the IOException class to handle errors
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import core.ConfigCore;
import environnementEntreprise.*;
import pointeuse.ConfigPointeuse;
import pointeuse.SerialPointeuse;  

/**
 * 
 * @author Lilian
 * @author Corentin
 *
 * Will Serialize data for multiple structures, like the pointeuse or the company class
 * all the file names and dirs are already preselected for now
 */
public class Serializer {

	/**
	 * The input stream that will read from files
	 */
	private ObjectInputStream iS;
	/**
	 * The output stream that will write to files
	 */
	private ObjectOutputStream oS;

	/**
	 * Open a file and create it if it doesn't exist
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

		File directory = new File("CoreData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File compFile = createOpenFile("CoreData"+File.separator+"CompanyFile.dat");
		try {
			oS = new ObjectOutputStream(new FileOutputStream(compFile));
			oS.writeObject(company);
			oS.close();	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read from a file to create a company and all its data
	 * @return a new company with all the department, employee and data on them
	 */
	public Company unserialiseCompagny() {

		File directory = new File("CoreData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("CoreData"+File.separator+"CompanyFile.dat");
		Company company = null;
		try {
			iS = new ObjectInputStream(new FileInputStream(dataFile));
			company = (Company) iS.readObject();
			iS.close();	
		}catch(EOFException e) {
			System.out.println("Empty file");

		}catch(ClassNotFoundException e) {
			//this could happen if the file has been modified, or if there was some difference between the classes version
			e.printStackTrace();

		}catch(IOException e) {
			e.printStackTrace();
		}

		return company;

	}



	/**
	 * Serialize the company name and port used by the server
	 * @param configToSave The data taht will be saved
	 * 
	 */
	public void serializeWriteCoreConfigData(ConfigCore configToSave) {

		File directory = new File("CoreData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("CoreData"+File.separator+"config.dat");
		try {
			oS = new ObjectOutputStream(new FileOutputStream(dataFile));
			oS.writeObject(configToSave);
			oS.close();	
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read the serialized data to read all the config for the core App
	 * 
	 * @return all the config that need to be used by the core
	 */
	public ConfigCore serializeReadCoreConfigData() {

		File directory = new File("CoreData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("CoreData"+File.separator+"config.dat");
		//SerialPointeuse[] tabData = null;
		ConfigCore config = null; //default port if something goes wrong
		try {
			iS = new ObjectInputStream(new FileInputStream(dataFile));
			config = (ConfigCore) iS.readObject();
			iS.close();	
		}catch(EOFException e) {
			System.out.println("EOF ? ressorting to the default port 8080 and company name DefaultCompanyName");
			config = new ConfigCore("DefaultCompanyName", 8080);

		}catch(ClassNotFoundException e) {
			//this could happen if the file has been modified, or if there was some difference between the classes version
			e.printStackTrace();
			config = new ConfigCore("DefaultCompanyName", 8080);

		}catch(IOException e) {
			e.printStackTrace();
			config = new ConfigCore("DefaultCompanyName", 8080);
		}

		return config;

	}

	
	
	/**
	 * Serialize the config used for the pointeuse
	 * @param dataConfig the structure with all the necessary data
	 * 
	 */
	public void serializeWritePointeuseConfigData(ConfigPointeuse dataConfig) {

		File directory = new File("PointeuseData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("PointeuseData"+File.separator+"config.dat");
		try {
			oS = new ObjectOutputStream(new FileOutputStream(dataFile));
			oS.writeObject(dataConfig);
			oS.close();	
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read the serialized data to read all the config for the core App
	 * this function should be expanded with a whole config class if you have more data to serialize
	 * 
	 * @return an the port that need to be used by the core server
	 */
	public ConfigPointeuse serializeReadPointeuseConfigData() {

		File directory = new File("PointeuseData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("PointeuseData"+File.separator+"config.dat");
		//SerialPointeuse[] tabData = null;
		ConfigPointeuse dataConfig = null; //default port if something goes wrong
		try {
			iS = new ObjectInputStream(new FileInputStream(dataFile));
			dataConfig = (ConfigPointeuse) iS.readObject();
			iS.close();	
		}catch(EOFException e) {
			System.out.println("EOF ?");

		}catch(ClassNotFoundException e) {
			//this could happen if the file has been modified, or if there was some difference between the classes version
			e.printStackTrace();

		}catch(IOException e) {
			e.printStackTrace();
		}

		return dataConfig;

	}
	
	
	/**
	 * Serialize the data from the "pointeuse" in a file in "data/PendingPointingData.dat"
	 * 
	 * will create the directory if it doesn't exist
	 * 
	 * @param tabData the Array of data not sent to serialize
	 */
	public void serializeWritePointeuseData(ArrayList<SerialPointeuse> tabData) {

		File directory = new File("PointeuseData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("PointeuseData"+File.separator+"PendingPointingData.dat");
		try {
			oS = new ObjectOutputStream(new FileOutputStream(dataFile));
			oS.writeObject(tabData);
			oS.close();	
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Read the serialized data to create an Arraylist with all of it
	 * @return an Arraylist with all the data not sent previously
	 */
	@SuppressWarnings("unchecked") //for the ArrayList cast, because there's no reason it wouldn't be one if the file had been left untouched
	public ArrayList<SerialPointeuse> serializeReadPointeuseData() {

		File directory = new File("PointeuseData");
		if (! directory.exists()){
			directory.mkdir();
		}

		File dataFile = createOpenFile("PointeuseData"+File.separator+"PendingPointingData.dat");
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
