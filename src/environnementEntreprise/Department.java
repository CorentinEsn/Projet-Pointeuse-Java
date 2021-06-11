package environnementEntreprise;

import java.util.ArrayList;

public class Department {
	//attributes
	private String name;
	private String description;
	
	private ArrayList<Employee> employees;
	//methods
	
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
	
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);
	}

	public void addEmployee(ArrayList<Employee> newEmployees) {
		employees.addAll(newEmployees);
	}
	
	public void remEmployee(int ID) {
		Boolean removed = false;
		ArrayList<Employee> toBeRemoved = new ArrayList<Employee>();
		
		//detection
		for(Employee employee : employees) {
			if(employee.getID() == ID && removed == false) {
				toBeRemoved.add(employee);
				removed = true;
			}
		
		}
		
		//suppression
		for(Employee employee : toBeRemoved) {
			employees.remove(employee);
		}
		
	}
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(String name,String description) {
		this.name=name;
		this.description=description;
	}
	
}
