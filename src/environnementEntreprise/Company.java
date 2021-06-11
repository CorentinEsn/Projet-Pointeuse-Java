
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
	 * Gets the departments.
	 *
	 * @return the departments
	 */
	//methods
	public ArrayList<Department> getDepartments() {
		return departments;
	}

	/**
	 * Sets the departments.
	 *
	 * @param departments the new departments
	 */
	public void setDepartments(ArrayList<Department> departments) {
		this.departments = departments;
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
	 * @param newName the new name
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Adds the department.
	 *
	 * @param department the department
	 */
	public void addDepartment(Department department) {
		departments.add(department);
	}
	
	/**
	 * Del department.
	 *
	 * @param department the department
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
	 * allEmployees.
	 *
	 * @return rez an ArrayList containing all employees
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
	 * @param name the name
	 */
	public Company(String name){
		this.name=name;
		this.departments=new ArrayList<>();
	}
	
	/**
	 * whoWasHere
	 *
	 * @param LocalDate day where we want to check who was here
	 * @return employees an ArrayList containing all employees who checked on that day
	 */
	public ArrayList<Employee> whoWasHere(LocalDate day) {
		//return value
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		//lengths for the for loops
		int nbEmployees;
		int nbDepartments = getDepartments().size();
		
		//iterator and entry for the history search
        //Iterator<Map.Entry<LocalDateTime, String> > iterator;
        //Map.Entry<LocalDateTime,String> entry ;
        boolean added = false;

		for(int i = 0 ; i < nbDepartments ; i++){
			nbEmployees = getDepartments().get(i).getEmployees().size();
			
			for(int j = 0 ; j < nbEmployees ; j++){
				added = false;
				//some old unnecessary useless fucking piece of shit code
				//iterator = departments.get(i).getEmployees().get(j).getHistory().entrySet().iterator();
				/*
				while (iterator.hasNext() && added == false) {
					
					entry = iterator.next();
					
					if ( day == entry.getKey().toLocalDate() ) {
						employees.add(getDepartments().get(i).getEmployees().get(j));
						added = true;
					}
					
				}
				*/
				if(departments.get(i).getEmployees().get(j).getHistory()!=null && departments.get(i).getEmployees().get(j).getHistory().get(day) != null && added==false) {
					employees.add(getDepartments().get(i).getEmployees().get(j));
					added = true;
				}
				
			}
			
		}
		
		return employees;
	}

}
