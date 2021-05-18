package environnementEntreprise;

import java.util.HashMap;
import java.time.LocalTime;

public class Schedule {
	
	private HashMap< String , Pair<LocalTime,LocalTime> > SCH;
	
	public Schedule () {
	    SCH.put("Lundi", null);
	    SCH.put("Mardi", null);
	    SCH.put("Mercredi", null);
	    SCH.put("Jeudi", null);
	    SCH.put("Vendredi", null);
	}

	public void addHrs(String key, Pair<LocalTime,LocalTime> hours) {
		
		if(	key == "Lundi" || 
			key == "Mardi" || 
			key == "Mercredi" || 
			key == "Jeudi" || 
			key == "Vendredi") {
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
