package environnementEntreprise;

public class Company {
	//attributes
	private String name;
	private Department[] departments;
	
	//methods
	public Department[] getDepartments() {
		return departments;
	}

	public void setDepartments(Department[] departments) {
		this.departments = departments;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
	
	
}
