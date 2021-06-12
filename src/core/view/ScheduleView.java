/*
 * @author Thomas Blumstein
 */
package core.view;
import javax.swing.*;

/**
 * The Class ScheduleView.
 */
public class ScheduleView  extends JFrame{
	
	/** The schedule. */
	private JTable schedule;

	/**
	 * Instantiates a new schedule view.
	 *
	 * @param Schedule the Table of the schedule
	 */
	public ScheduleView(JTable Schedule) {
		super("EDT");
		this.schedule=Schedule;
		setDefaultCloseOperation(HIDE_ON_CLOSE);//setting the exit when clicking on the X
		setSize(500, 150);//size of the frame
		Schedule.setSize(500, 150);;
		add(Schedule);
	}


}
