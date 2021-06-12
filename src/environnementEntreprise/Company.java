package environnementEntreprise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
				if(departments.get(i).getEmployees().get(j).getHistory().get(day) != null && added==false) {
					employees.add(getDepartments().get(i).getEmployees().get(j));
					added = true;
				}
				
			}
			
		}
		
		return employees;
	}

}
