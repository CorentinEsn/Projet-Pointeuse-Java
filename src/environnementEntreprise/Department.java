
package environnementEntreprise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The Class Department.
 */
public class Department implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The employees. */
	private ArrayList<Employee> employees;
	//methods
	
	/**
	 * Gets the employees of the current Department.
	 *
	 * @return the list of all employees in the current department
	 */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * Sets the employees of the current Department.
	 *
	 * @param employees the new list of all employees in the current department
	 */
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	

	/**
	 * Gets the name of the department.
	 *
	 * @return the name of the current department
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Sets the name of the current department.
	 *
	 * @param name the new name of the current department
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the current department.
	 *
	 * @return description of the current department
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the current department.
	 *
	 * @param newDescription : new description of the current department
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	/**
	 * Adds an employee to the current department.
	 *
	 * @param newEmployee : the employee to add
	 */
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);
		
	}

	/**
	 * Adds all the employees in the inputed list to the current department.
	 *
	 * @param newEmployees : list of all employees to add
	 */
	public void addAllEmployee(ArrayList<Employee> newEmployees) {
		employees.addAll(newEmployees);
	}
	
	/**
	 * modifies an employee of the current department.
	 *
	 * @param oldEmployee the old employee (searched by uuid)
	 * @param newEmployee the new employee
	 * @return place of the employee in the list, 0 if not found
	 */
	public int modEmployee(Employee oldEmployee,Employee newEmployee) {
		for (int i=0;i <employees.size();i++) {
			if (employees.get(i).getUUID()==oldEmployee.getUUID()) {
				employees.get(i).setName(newEmployee.getName());
				employees.get(i).setFirstname(newEmployee.getFirstname());
				employees.get(i).setSCH(newEmployee.getSCH());
				return i;
				}

			}
		return 0;
	}
	
	/**
	 * Removes an employee from the current department.
	 *
	 * @param uuid : the uuid of the employee to remove
	 */
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
	
	/**
	 * Instantiates a new department.
	 */
	public Department() {
		
	}
	
	/**
	 * Instantiates a new department.
	 *
	 * @param name : the name of the new department
	 * @param description : the description of the new employee
	 */
	public Department(String name,String description) {
		this.name=name;
		this.description=description;
		this.employees=new ArrayList<>();
	}
	
}
