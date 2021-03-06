package pointeuse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JLabel;

/**
 * Listener to update the time and data shown on screen
 * @author Lilian
 *
 */
public class AffListener implements ActionListener{

	/**
	 * the time that will be shown
	 */
	private JLabel lbTimeCurrent;
	/**
	 * the date that will be shown
	 */
	private JLabel lbDateCurrent;

	/**
	 * Constructor
	 * 
	 * @param lbTimeCurrent {@link AffListener#lbTimeCurrent}
	 * @param lbDateCurrent {@link AffListener#lbDateCurrent}
	 */
	public AffListener(JLabel lbTimeCurrent,JLabel lbDateCurrent) {
		super();
		this.lbTimeCurrent = lbTimeCurrent;
		this.lbDateCurrent = lbDateCurrent;
	}

	/**
	 * Will update the main screen with the new time and date
	 */
	public void actionPerformed(ActionEvent evt) {
		String sLdtDateNew = PointeuseController.getFullDateFromCurrentDateTime(); 
		String sLdtTimeNew = 		LocalDateTime.now().format(PointeuseController.formatter)
				+" ... Let's say "
				+PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);

		lbTimeCurrent.setText(sLdtTimeNew);
		lbDateCurrent.setText(sLdtDateNew);
		//frame.repaint();
	}

}