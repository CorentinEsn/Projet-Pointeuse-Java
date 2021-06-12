
package environnementEntreprise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class Department.
 */
public class Department implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	//attributs
	private String name;
	
	/** The description. */
	private String description;
	
	/** The employees. */
	private ArrayList<Employee> employees;
	//methods
	
	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * Sets the employees.
	 *
	 * @param employees the new employees
	 */
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Sets the name.
	 *
	 * @param nom the new name
	 */
	public void setName(String nom) {
		this.name = nom;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param newDescription the new description
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	/**
	 * Adds the employee.
	 *
	 * @param newEmployee the new employee
	 */
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);
		
	}

	/**
	 * Adds the all employee.
	 *
	 * @param newEmployees the new employees
	 */
	public void addAllEmployee(ArrayList<Employee> newEmployees) {
		employees.addAll(newEmployees);
	}
	
	/**
	 * modEmployee.
	 *
	 * @param oldEmployee the old employee
	 * @param newEmployee the new employee
	 * @return i place of the employee in the list, 0 if not found
	 */
	public int modEmployee(Employee oldEmployee,Employee newEmployee) {
		for (int i=0;i <employees.size();i++) {
			if (employees.get(i).getUUID()==oldEmployee.getUUID()) {
				employees.get(i).setName(newEmployee.getName());
				employees.get(i).setFirstname(newEmployee.getFirstname());
				employees.get(i).setoverTime(newEmployee.getoverTime());
				employees.get(i).setSCH(newEmployee.getSCH());
				employees.get(i).setUUID(newEmployee.getUUID());
				employees.get(i).setHistory(newEmployee.getHistory());
				return i;
				}

			}
		return 0;
	}
	
	/**
	 * Rem employee.
	 *
	 * @param uuid the uuid
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
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new department.
	 *
	 * @param name the name
	 * @param description the description
	 */
	public Department(String name,String description) {
		this.name=name;
		this.description=description;
		this.employees=new ArrayList<>();
	}
	
}
