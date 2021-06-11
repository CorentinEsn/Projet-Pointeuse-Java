package saving;

import environnementEntreprise.Company;
import environnementEntreprise.Department;

public class Main_serial {
	public static void main(String[] args) {
		// Assemble all the pieces of the MVC
		Company Entreprise =new Company("Polytech") ;
		Department department=new Department("info","test");
		Entreprise.addDepartment(department);
		
		Serializer serializer = new Serializer();
		serializer.serializeCompany(Entreprise);
		serializer.unserialiseCompagny();
		
}
}