package environnementEntreprise;

import java.util.ArrayList;
import java.util.UUID;

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

	public void addAllEmployee(ArrayList<Employee> newEmployees) {
		employees.addAll(newEmployees)
		
		;
	}
	
	public int modEmployee(Employee oldEmployee,Employee newEmployee) {
		for (int i=0;i <employees.size();i++) {
			if (employees.get(i).getUUID()==oldEmployee.getUUID()) {
				employees.get(i).setName(newEmployee.getName());
				employees.get(i).setFirstname(newEmployee.getFirstname());
				employees.get(i).setoverTime(newEmployee.getoverTime());
				employees.get(i).setSCH(newEmployee.getSCH());
				employees.get(i).setUUID(newEmployee.getUUID());
				return i;
				}

			}
		return 0;
	}
	
	public void remEmployee(UUID uuid) {
		Boolean removed = false;
		ArrayList<Employee> toBeRemoved = new ArrayList<Employee>();
		
		//detection
		for(Employee employee : employees) {
			if(employee.getUUID() == uuid && removed == false) {
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
		this.employees=new ArrayList<>();
	}
	
}
