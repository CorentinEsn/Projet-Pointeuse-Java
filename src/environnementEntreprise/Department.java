package environnementEntreprise;
import java.util.List;

public class Department {
	//attributs
	private String name;
	private String description;
	private List<Employee> employees;
	
	//methodes
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
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
	
}
