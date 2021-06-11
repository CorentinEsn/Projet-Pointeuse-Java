package core.view;
import javax.swing.*;

public class ScheduleView  extends JFrame{
	private JTable schedule;

	public ScheduleView(JTable Schedule) {
		super("EDT");
		this.schedule=Schedule;
		setDefaultCloseOperation(HIDE_ON_CLOSE);//setting the exit when clicking on the X
		setSize(500, 150);//size of the frame
		Schedule.setSize(500, 150);;
		add(Schedule);
	}


}
