package environnementEntreprise;

import java.util.HashMap;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Employee {


	private UUID uuid;
	private String name ;
	private String firstname;

	//overTime contains the amount of spare minutes an employee has
	private long overTime;

	private boolean checkedIn;

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public Schedule SCH;

	public Schedule getSCH() {
		return SCH;
	}

	public void setSCH(Schedule sCH) {
		SCH = sCH;
	}

	public static int maxID=0;

	private HashMap<LocalDateTime,String> history;

	//methods
	public Employee() {

		this.uuid = UUID.randomUUID();
		checkedIn = false;
		overTime = 0;
	}

	public Employee(String name, String firstname,Schedule SCH) {

		this.setName(name);
		this.setFirstname(firstname);
		this.uuid = UUID.randomUUID();
		this.SCH=SCH;
		checkedIn = false;
		overTime = 0;
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID newUUID) {
		uuid = newUUID;
	}

	public long getoverTime() {
		return overTime;
	}

	public void setoverTime(long newoverTime) {
		overTime = newoverTime;
	}
	
	public void setHistory(HashMap<LocalDateTime,String> newHistory) {
		history = newHistory;
	}
	
	public HashMap<LocalDateTime,String> getHistory(){
		return history;
	}
	public void checkIO(LocalDateTime time){

		LocalTime timeOfDay = time.toLocalTime();
		long timeDiff;
		String message = "cheched out";

		//THE EMPLOYEE IS CHECKING IN
		if (checkedIn == false) {
			checkedIn = true;
			message = "cheched in";
			switch (time.getDayOfWeek()) {
			case MONDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(0).getL());

			case TUESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(1).getL());

			case WEDNESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(2).getL());

			case THURSDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(3).getL());

			case FRIDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(4).getL());

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
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(0).getR());

			case TUESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(1).getR());

			case WEDNESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(2).getR());

			case THURSDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(3).getR());

			case FRIDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(4).getR());

			default:
				timeDiff = 0;
			}

			setoverTime(getoverTime()+timeDiff);
			history.put(time, message);
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


}
