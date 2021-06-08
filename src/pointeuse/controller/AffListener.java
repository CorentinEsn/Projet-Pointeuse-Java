package pointeuse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JLabel;


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
	 * @brief constructor
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
	 * @brief will update the main screen with the new time and date
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