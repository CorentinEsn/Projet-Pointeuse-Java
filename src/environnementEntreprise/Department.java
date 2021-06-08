package environnementEntreprise;
import java.util.List;

import java.util.ArrayList;

public class Department {
	//attributs
	private String name;
	private String description;
<<<<<<< HEAD
	private List<Employee> employees;
=======
	private ArrayList<Employee> employees;
>>>>>>> branch 'main' of https://github.com/CorentinEsn/Projet-Pointeuse-Java
	
	//methodes
<<<<<<< HEAD
	public List<Employee> getEmployees() {
=======
	public ArrayList<Employee> getEmployees() {
>>>>>>> branch 'main' of https://github.com/CorentinEsn/Projet-Pointeuse-Java
		return employees;
	}

<<<<<<< HEAD
	public void setEmployees(List<Employee> employees) {
=======
	public void setEmployees(ArrayList<Employee> employees) {
>>>>>>> branch 'main' of https://github.com/CorentinEsn/Projet-Pointeuse-Java
		this.employees = employees;
	}
	

	public String getName() {
		return name;
	}

	
	public void setName(String nom) {
		this.name = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
<<<<<<< HEAD
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);
	}

	public void addEmployee(List<Employee> newEmployees) {
		employees.addAll(newEmployees);
	}
	
	public void remEmployee(int ID) {
		Boolean removed = false;
		for(Employee employee : employees) {
			if(employee.getID() == ID && removed == false) {
				employees.remove(employee);
				removed = true;
			}
		
		}
		
	}
	
=======
	public Department() {
		// TODO Auto-generated constructor stub
	}
	public Department(String name,String description) {
		this.name=name;
		this.description=description;
	}
>>>>>>> branch 'main' of https://github.com/CorentinEsn/Projet-Pointeuse-Java
}
