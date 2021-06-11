package environnementEntreprise;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable{
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
	public int modDepartment(Department oldDepartment,Department newDepartment) {
		for (int i=0;i <departments.size();i++) {
			if (departments.get(i).getName()==oldDepartment.getName()) {
				departments.get(i).setName(newDepartment.getName());
				departments.get(i).setDescription(newDepartment.getDescription());
				return i;
				}

			}
		return 0;
	}
	
	public ArrayList<Employee> allEmployees(){
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ArrayList<Employee> rez = new ArrayList<Employee>();
		
		for(int i=0;i<departments.size();i++) {
			employees = departments.get(i).getEmployees();
			for(int j=0;j < employees.size();j++) {
				rez.add(employees.get(j));
			}
			
		}
		
		return rez;
	}
	
	public Company(String name){
		this.name=name;
		this.departments=new ArrayList<>();
	}

}
