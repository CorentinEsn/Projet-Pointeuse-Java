
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
	 * Checks if the current Employee is checked in.
	 *
	 * @return boolean checkedIn : True if the employee is currently checked in
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/**
	 * Sets the checked in value.
	 *
	 * @param checkedIn the new checked in value for the current employee
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/** The schedule. */
	public Schedule SCH;

	/**
	 * Gets the schedule of the current employee.
	 *
	 * @return Schedule SCH : current employees schedule
	 */
	public Schedule getSCH() {
		return SCH;
	}

	/**
	 * Sets the schedule of the current employee.
	 *
	 * @param Schedule sCH : the new schedule of the current employee
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
	 * @param String name : name of the employee
	 * @param String firstname : firstname of the employee
	 * @param Schedule SCH : schedule of the employee
	 */
	public Employee(String name, String firstname,Schedule SCH) {


		this.history=new HashMap<LocalDate, Pair<LocalTime,LocalTime>>();
		this.setName(name);
		this.setFirstname(firstname);
		this.uuid = UUID.randomUUID();
		this.SCH=SCH;
		checkedIn = false;
		overTime = 0;
	}

	/**
	 * Gets the uuid of the current employee.
	 *
	 * @return UUID uuid : the uuid of the current employee
	 */
	public UUID getUUID() {
		return uuid;
	}

	/**
	 * Sets the uuid of the current employee.
	 *
	 * @param UUID newUUID : the new uuid of the current employee
	 */
	public void setUUID(UUID newUUID) {
		uuid = newUUID;
	}

	/**
	 * Gets the over time of the current employee.
	 *
	 * @return long overTime : overtime value of the current employee
	 */
	public long getoverTime() {
		return overTime;
	}
	public String getovertimeFormatted(){
		String temp;
		Long hourOnly=overTime/60;
		Long minuteOnly=overTime%60;
		Integer hour=hourOnly.intValue();
		Integer minute=minuteOnly.intValue();
		if (overTime<0) {
			temp="-"+Math.abs(hour)+":"+Math.abs(minute);
		}
		else temp=Math.abs(hour)+":"+Math.abs(minute);
		return temp;

	}

	/**
	 * Sets the over time of the current employee.
	 *
	 * @param long newoverTime : the new over time of the current employee
	 */
	public void setoverTime(long newoverTime) {
		overTime = newoverTime;
	}

	/**
	 * Sets the history of the current employee.
	 *
	 * @param HashMap<LocalDate,Pair<LocalTime,LocalTime>> newHistory : the new history of the current employee
	 */
	public void setHistory(HashMap<LocalDate,Pair<LocalTime,LocalTime>> newHistory) {
		history = newHistory;
	}

	/**
	 * Gets the history of the current employee.
	 *
	 * @return HashMap<LocalDate,Pair<LocalTime,LocalTime>> history : the history of the current employee
	 */
	public HashMap<LocalDate,Pair<LocalTime,LocalTime>> getHistory(){
		return history;
	}

	/**
	 * determines what day of the week the inputed date is.
	 *
	 * @param LocalDate date : the date we want to check
	 * @return int rez : corresponds to the day of the week (from 0 to 4 from monday to friday)
	 */
	public int dayOfWeek(LocalDate date) {
		int rez;
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		switch (dayOfWeek) {
		case MONDAY:
			rez=0;
			break;
		case TUESDAY:
			rez=1;
			break;
		case WEDNESDAY:
			rez=2;
			break;
		case THURSDAY:
			rez=3;
			break;
		case FRIDAY:
			rez=4;
			break;
		default:
			rez=-1;
			break;
		}

		return rez;
	}

	/**
	 * checkIO checks an employee in or out
	 *
	 * @param LocalDateTime time :the time of checking in or out
	 */
	public void checkIO(LocalDateTime time){

		LocalTime timeOfDay = time.toLocalTime();
		LocalTime temp=LocalTime.of(1, 1, 1,1);
		long timeDiff;
		Pair<LocalTime,LocalTime> pair =new Pair<LocalTime, LocalTime>(timeOfDay, temp);

		//THE EMPLOYEE IS CHECKING IN
		if (checkedIn == false) {
			checkedIn = true;
			switch (time.getDayOfWeek()) {
			case MONDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(0).getR());
				break;

			case TUESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(1).getR());
				break;

			case WEDNESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(2).getR());
				break;

			case THURSDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(3).getR());
				break;

			case FRIDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(4).getR());
				break;

			default:
				timeDiff = 0;
				break;
			}
			System.out.println(timeDiff);
			pair = new Pair<LocalTime,LocalTime>(timeOfDay,temp);
			setoverTime(getoverTime()+timeDiff);

		}

		//THE EMPLOYEE IS CHECKING OUT
		else {
			checkedIn = false;
			switch (time.getDayOfWeek()) {
			case MONDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(0).getR());
				break;

			case TUESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(1).getR());
				break;

			case WEDNESDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(2).getR());
				break;

			case THURSDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(3).getR());
				break;

			case FRIDAY:
				timeDiff = MINUTES.between(timeOfDay, SCH.getSCH().get(4).getR());
				break;

			default:
				timeDiff = 0;
				break;
			}
			pair = history.get(time.toLocalDate());

			pair.setR(timeOfDay);

			setoverTime(getoverTime()-timeDiff-1);
		}
		System.out.println(timeDiff);
		history.put(time.toLocalDate(), pair);

	}

	/**
	 * Gets the name of the current employee.
	 *
	 * @return String name : the name of the crrent employee
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the current employee.
	 *
	 * @param String name : new name of the current employee
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the firstname of the current employee.
	 *
	 * @return String firstname : the firstname of the current employee
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the firstname of the current employee.
	 *
	 * @param String firstname : the new firstname of the current employee
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


}
