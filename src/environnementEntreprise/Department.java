package environnementEntreprise;

import java.util.ArrayList;

public class Department {
	//attributs
	private String name;
	private String description;
	private ArrayList<Employee> employees;
	
	//methodes
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
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
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	public Department(String name,String description) {
		this.name=name;
		this.description=description;
	}
}
