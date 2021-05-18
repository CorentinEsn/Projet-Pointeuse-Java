package environnementEntreprise;

public class Department {
	//attributs
	private String name;
	private Employee[] employees;
	
	//methodes
	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}
	

	public String getName() {
		return name;
	}

	
	public void setName(String nom) {
		this.name = nom;
	}
	
}
