package environnementEntreprise;

import java.util.HashMap;
import java.time.LocalTime;

public class Schedule {
	
	private HashMap< Integer , Pair<LocalTime,LocalTime> > SCH;
	
	public Schedule () {
		SCH=new HashMap<>();
	    SCH.put(0, new Pair<>(LocalTime.now(), LocalTime.now()));
	    SCH.put(1, new Pair<>(LocalTime.now(), LocalTime.now()));
	    SCH.put(1, new Pair<>(LocalTime.now(), LocalTime.now()));
	    SCH.put(3, new Pair<>(LocalTime.now(), LocalTime.now()));
	    SCH.put(4, new Pair<>(LocalTime.now(), LocalTime.now()));
	}

	public void addHrs(Integer key, Pair<LocalTime,LocalTime> hours) {
		
		if(	key == 0 || 
			key == 1 || 
			key == 2 || 
			key == 3 || 
			key == 4) {
		SCH.put(key,hours);
		}
		
	}
	
	public HashMap< Integer , Pair<LocalTime,LocalTime> > getSCH() {
		return SCH;
	}

	public void setSCH(HashMap< Integer , Pair<LocalTime,LocalTime> > sch) {
		SCH = sch;
	}

}
