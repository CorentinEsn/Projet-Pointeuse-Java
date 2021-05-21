package environnementEntreprise;

import java.time.LocalTime;
import java.time.LocalTime.*;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDateTime;
import java.time.LocalDateTime.*;

public class Employee {
	
	private int ID;
	private String nom;
	private String prenom;
	
	//overTime contains the amount of spare minutes an employee has
	private long overTime;
	
	private boolean checkedIn;
	
	public Schedule SCH;
	public static int maxID=0;
	
	//methods
	public Employee() {
		maxID++;
		this.setID(maxID);
		checkedIn = false;
		overTime = 0;
	}
	
	public Employee(String nom, String prenom) {
		maxID++;
		this.setID(maxID);
		
		this.setNom(nom);
		this.setPrenom(prenom);
		
		checkedIn = false;
		overTime = 0;
	}
	
 	public int getID() {
		return ID;
	}
	
	public void setID(int newID) {
		ID = newID;
	}

	public long getoverTime() {
		return overTime;
	}

	public void setoverTime(long newoverTime) {
		overTime = newoverTime;
	}
	
	public void checkIO(LocalDateTime time){
		
		LocalTime timeOfDay = time.toLocalTime();
		long timeDiff;
		
		//THE EMPLOYEE IS CHECKING IN
		if (checkedIn == false) {
			checkedIn = true;
			switch (time.getDayOfWeek()) {
			case MONDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Monday").getL());
				
			case TUESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Tuesday").getL());
				
			case WEDNESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Wednesday").getL());
				
			case THURSDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Thursday").getL());
				
			case FRIDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Friday").getL());
				
			default:
				timeDiff = 0;
		}
		
		setoverTime(getoverTime()-timeDiff);
		}
		
		//THE EMPLOYEE IS CHECKING OUT
		else {
			checkedIn = false;
			switch (time.getDayOfWeek()) {
			case MONDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Monday").getR());
				
			case TUESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Tuesday").getR());
				
			case WEDNESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Wednesday").getR());
				
			case THURSDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Thursday").getR());
				
			case FRIDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get("Friday").getR());
				
			default:
				timeDiff = 0;
			}
			
			setoverTime(getoverTime()+timeDiff);
		}
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	//ADAM TEST
	public static void main(String args[]) { 
		Employee employee = new Employee("nassiri","adam");
		//Pair<LocalTime,LocalTime> horaires = new Pair();
		
		//employee.SCH.addHrs("Monday",); 
	}
	
}
