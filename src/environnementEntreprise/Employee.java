package environnementEntreprise;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalDateTime;

public class Employee {
	
	private int ID;
	
	//overTimes contains the sum of all early and late arrivals of the employee
	private LocalTime overTime;
	
	/*
	Since a local time can't be negative:
	otSign is True if employee has time to spare and
	False if they have time to make up for
	*/
	private boolean otSign;
	
	public Schedule SCH;
	
	//methods
 	public int getID() {
		return ID;
	}
	
	public void setID(int newID) {
		ID = newID;
	}

	public LocalTime getoverTime() {
		return overTime;
	}

	public void setoverTime(LocalTime newoverTime) {
		overTime = newoverTime;
	}

	public boolean getotSign() {
		return otSign;
	}

	public void setotSign(boolean newotSign) {
		otSign = newotSign;
	}
	
	public void checkIO(LocalDateTime time){
		switch (time.getDayOfWeek()) {
		case 0:
			
		case 1:
			
		case 2:
			
		case 3:
			
		case 4:
			
		}
		
	}
	
}
