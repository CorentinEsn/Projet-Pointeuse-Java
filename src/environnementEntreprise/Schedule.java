package environnementEntreprise;

import java.util.HashMap;
import java.time.LocalTime;

public class Schedule {
	
	private HashMap< String , Pair<LocalTime,LocalTime> > SCH;
	
	public Schedule () {
	    SCH.put("Monday", null);
	    SCH.put("Tuesday", null);
	    SCH.put("Wednesday", null);
	    SCH.put("Thursday", null);
	    SCH.put("Friday", null);
	}

	public void addHrs(String key, Pair<LocalTime,LocalTime> hours) {
		
		if(	key == "Monday" || 
			key == "Tuesday" || 
			key == "Wednesday" || 
			key == "Thursday" || 
			key == "Friday") {
		SCH.put(key,hours);
		}
		
	}
	
	public HashMap< String , Pair<LocalTime,LocalTime> > getSCH() {
		return SCH;
	}

	public void setSCH(HashMap< String , Pair<LocalTime,LocalTime> > sch) {
		SCH = sch;
	}

}
