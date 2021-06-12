
package environnementEntreprise;

import java.util.HashMap;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * The Class Schedule.
 */
public class Schedule implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The schedule represented by a hashmap */
	private HashMap< Integer , Pair<LocalTime,LocalTime> > SCH;

	/**
	 * Instantiates a new schedule.
	 */
	public Schedule () {
		SCH=new HashMap<>();
		SCH.put(0, new Pair<>(LocalTime.now(), LocalTime.now()));
		SCH.put(1, new Pair<>(LocalTime.now(), LocalTime.now()));
		SCH.put(1, new Pair<>(LocalTime.now(), LocalTime.now()));
		SCH.put(3, new Pair<>(LocalTime.now(), LocalTime.now()));
		SCH.put(4, new Pair<>(LocalTime.now(), LocalTime.now()));
	}

	/**
	 * Adds the hours of arrival and leave in the schedule.
	 *
	 * @param key int corresponding to a day of the week
	 * @param hours the hours for that day
	 */
	public void addHrs(Integer key, Pair<LocalTime,LocalTime> hours) {

		if(	key == 0 || 
				key == 1 || 
				key == 2 || 
				key == 3 || 
				key == 4) {
			SCH.put(key,hours);
		}

	}

	/**
	 * Gets the schedule.
	 *
	 * @return the schedule
	 */
	public HashMap< Integer , Pair<LocalTime,LocalTime> > getSCH() {
		return SCH;
	}

	/**
	 * Sets the schedule.
	 *
	 * @param sch the schedule
	 */
	public void setSCH(HashMap< Integer , Pair<LocalTime,LocalTime> > sch) {
		SCH = sch;
	}

}
