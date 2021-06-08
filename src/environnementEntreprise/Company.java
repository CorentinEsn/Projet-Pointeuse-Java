package environnementEntreprise;

import java.util.ArrayList;

public class Company {
	//attributes
	private String name;
	private ArrayList<Department> departments;
	
	//methods
	public ArrayList<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(ArrayList<Department> departments) {
		this.departments = departments;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
	public void addDepartment(Department department) {
		departments.add(department);
	}
	public void delDepartment(Department department) {
		for (int i=0;i <departments.size();i++) {
			if (departments.get(i).getName()==department.getName()) {
				departments.remove(i);
			}
		}
	}
	public void modDepartment(Department oldDepartment,Department newDepartment) {
		for (int i=0;i <departments.size();i++) {
			if (departments.get(i).getName()==oldDepartment.getName()) {
				departments.get(i).setName(newDepartment.getName());
				departments.get(i).setDescription(newDepartment.getDescription());
				}
			}
	}
	
	public Company(String name){
		this.name=name;
		this.departments=new ArrayList<>();
	}
}
