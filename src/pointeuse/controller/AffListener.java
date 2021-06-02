package pointeuse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JLabel;


public class AffListener implements ActionListener{

	private JLabel lbTimeCurrent;
	private JLabel lbDateCurrent;

	
	public AffListener(JLabel lbTimeCurrent,JLabel lbDateCurrent) {
		super();
		this.lbTimeCurrent = lbTimeCurrent;
		this.lbDateCurrent = lbDateCurrent;
	}
	
	
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
