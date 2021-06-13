 
package environnementEntreprise;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class Company.
 */
public class Company implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	private String name;
	
	/** The departments. */
	private ArrayList<Department> departments;
	
	/**
	 * Gets the departments of the current company.
	 *
	 * @return ArrayList<Department> departments : the departments of the current company
	 */
	//methods
	public ArrayList<Department> getDepartments() {
		return departments;
	}

	/**
	 * Sets the departments of the current company.
	 *
	 * @param ArrayList<Department> departments : the new departments
	 */
	public void setDepartments(ArrayList<Department> departments) {
		this.departments = departments;
	}
	
	/**
	 * Gets the name of the current company.
	 *
	 * @return String name : the name of the current company
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the current company.
	 *
	 * @param String newName : the new name of the current company
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Adds a department to the current company.
	 *
	 * @param Department department : the department to add
	 */
	public void addDepartment(Department department) {
		departments.add(department);
	}
	
	/**
	 * Deletes a departments from the current company.
	 *
	 * @param Department department : the department to delete, the name of the parameter department needs to be the same as the department you want to delete
	 */
	public void delDepartment(Department department) {
		for (int i=0;i <departments.size();i++) {
			if (departments.get(i).getName()==department.getName()) {
				departments.remove(i);
			}
		}
	}
	
	/**
	 * Modifies a department.
	 *
	 * @param oldDepartment the old department
	 * @param newDepartment the new department
	 * @return int i the place of the department in the list
	 */
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
	
	/**
	 * Returns a ArrayList of all employees of the current company.
	 *
	 * @return ArrayList<Employee> rez : contains all employees
	 */
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
	
	/**
	 * Instantiates a new company.
	 *
	 * @param name the name of the new company
	 */
	public Company(String name){
		this.name=name;
		this.departments=new ArrayList<>();
	}
	
	/**
	 * Returns a list of all the employees who checked in on the inputed day
	 *
	 * @param LocalDate day where we want to check who was here
	 * @return ArrayList<Employee> employees : contains all employees who checked in that day
	 */
	public ArrayList<Employee> whoWasHere(LocalDate day) {
		//return value
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		//lengths and condition value for the for loops
		int nbEmployees;
		int nbDepartments = getDepartments().size();
        boolean added = false;

		for(int i = 0 ; i < nbDepartments ; i++){
			nbEmployees = getDepartments().get(i).getEmployees().size();
			
			for(int j = 0 ; j < nbEmployees ; j++){
				added = false;
				if(departments.get(i).getEmployees().get(j).getHistory().get(day) != null && added==false) {
					employees.add(getDepartments().get(i).getEmployees().get(j));
					added = true;
				}
				
			}
			
		}
		
		return employees;
	}

}
