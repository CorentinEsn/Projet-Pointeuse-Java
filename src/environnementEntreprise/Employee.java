
package environnementEntreprise;

import java.util.HashMap;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Class Employee.
 */
public class Employee implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The uuid. */
	private UUID uuid;
	
	/** The name. */
	private String name ;
	
	/** The firstname. */
	private String firstname;

	/** The over time. */
	//overTime contains the amount of spare minutes an employee has
	private long overTime;

	/** checked in. */
	private boolean checkedIn;

	/**
	 * Checks if the Employee is checked in.
	 *
	 * @return true, if is checked in
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/**
	 * Sets the checked in.
	 *
	 * @param checkedIn the new checked in
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/** The sch. */
	public Schedule SCH;

	/**
	 * Gets the sch.
	 *
	 * @return the sch
	 */
	public Schedule getSCH() {
		return SCH;
	}

	/**
	 * Sets the sch.
	 *
	 * @param sCH the new sch
	 */
	public void setSCH(Schedule sCH) {
		SCH = sCH;
	}

	/** The max ID. */
	public static int maxID=0;

	/** The history. */
	private HashMap<LocalDate,Pair<LocalTime,LocalTime>> history;

	/**
	 * Instantiates a new employee.
	 */
	public Employee() {

		this.uuid = UUID.randomUUID();
		checkedIn = false;
		overTime = 0;
	}

	/**
	 * Instantiates a new employee.
	 *
	 * @param name the name of the employee
	 * @param firstname the firstname of the employee
	 * @param SCH the schedule of the employee
	 */
	public Employee(String name, String firstname,Schedule SCH) {

		this.setName(name);
		this.setFirstname(firstname);
		this.uuid = UUID.randomUUID();
		this.SCH=SCH;
		checkedIn = false;
		overTime = 0;
	}

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public UUID getUUID() {
		return uuid;
	}

	/**
	 * Sets the uuid.
	 *
	 * @param newUUID the new uuid
	 */
	public void setUUID(UUID newUUID) {
		uuid = newUUID;
	}

	/**
	 * Gets the over time.
	 *
	 * @return the over time
	 */
	public long getoverTime() {
		return overTime;
	}

	/**
	 * Sets the over time.
	 *
	 * @param newoverTime the new over time
	 */
	public void setoverTime(long newoverTime) {
		overTime = newoverTime;
	}
	
	/**
	 * Sets the history.
	 *
	 * @param newHistory the new history
	 */
	public void setHistory(HashMap<LocalDate,Pair<LocalTime,LocalTime>> newHistory) {
		history = newHistory;
	}
	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	public HashMap<LocalDate,Pair<LocalTime,LocalTime>> getHistory(){
		return history;
	}
	
	/**
	 * Day of week.
	 *
	 * @param date the date
	 * @return rez corresponds to the day of the week
	 */
	public int dayOfWeek(LocalDate date) {
		int rez;
		DayOfWeek dayOfWeek = date.getDayOfWeek();

		switch (dayOfWeek) {
		case MONDAY:
			rez=0;
		case TUESDAY:
			rez=1;
		case WEDNESDAY:
			rez=2;
		case THURSDAY:
			rez=3;
		case FRIDAY:
			rez=4;
		default:
			rez=-1;
		}
		
		return rez;
	}
	
	/**
	 * checkIO checks an enmployee in or out
	 *
	 * @param time the time of checking
	 */
	public void checkIO(LocalDateTime time){

		LocalTime timeOfDay = time.toLocalTime();
		long timeDiff;
		Pair<LocalTime,LocalTime> pair ;
		
		//THE EMPLOYEE IS CHECKING IN
		if (checkedIn == false) {
			checkedIn = true;
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
			pair = new Pair<LocalTime,LocalTime>(timeOfDay,timeOfDay);
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
			pair = history.get(time.toLocalDate());
			
			pair.setR(timeOfDay);

			setoverTime(getoverTime()+timeDiff);
		}
		
		history.put(time.toLocalDate(), pair);

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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param firstname the new firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


}
