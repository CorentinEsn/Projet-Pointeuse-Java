package pointeuse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import pointeuse.SerialPointeuse;

public class ButtonListener implements ActionListener{

	/**
	 * reference to the UUID texfield
	 */
	private JTextField tfUUID;
	/**
	 * reference to the IP Address texfield
	 */
	private JTextField tfIPAddress;
	/**
	 * reference to the Port Spinner
	 */
	private JSpinner spPort;
	/**
	 * reference to the frame
	 */
	private JFrame frameRef;

	/**
	 * Regex Pattern for the IP address
	 */
	private final String PATTERNIP = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

	/**
	 * Regex Pattern for the UUID
	 */
	private final String PATTERNUUID = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b";

	/**
	 * 
	 * @param tfUUID {@link ButtonListener#tfUUID}
	 * @param tfIPAddress {@link ButtonListener#tfIPAddress}
	 * @param spPort {@link ButtonListener#spPort}
	 * @param frameRef {@link ButtonListener#frameRef}
	 */
	public ButtonListener(JTextField tfUUID, JTextField tfIPAddress, JSpinner spPort, JFrame frameRef) {
		super();
		this.tfUUID = tfUUID;
		this.tfIPAddress = tfIPAddress;
		this.spPort = spPort;
		this.frameRef = frameRef;
	}

	/**
	 * @brief when activated via the pressing of a button, will gather the data of the textfields and prepare the data for sending
	 * before preparing it, it checks whether the textfields are in a correct format
	 * and will send an error message and throw an illegal argument error
	 */
	@Override
	public void actionPerformed(ActionEvent ae){
		String textFieldValue = tfUUID.getText();
		//checks if UUID is a correct value
		if(!textFieldValue.matches(PATTERNUUID)) {
			JOptionPane.showMessageDialog(frameRef,
					"The UUID is not in a correct format.\n 8-4-4-4-12 like 6a2f41a3-c54c-fce8-32d2-0324e1c32e22",
					"Insane error",
					JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("the UUID do not hgave a correct format");
		}


		String textFieldIP = tfIPAddress.getText();
		//if the IP has a correct format or not
		if(textFieldIP.matches(PATTERNIP) || textFieldIP.contentEquals("localhost")) {
			int spPortNum = (int) spPort.getValue();

			UUID uid = UUID.fromString(textFieldValue);
			System.out.println(uid);

			String ldtTimeOnClick = PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);

			System.out.println(textFieldValue + " checked in/out at " + ldtTimeOnClick);

			//Prepare the data
			//SerialPointeuse dataToSend = new SerialPointeuse(textFieldValue, ldtTimeOnClick, PointeuseController.getFullTimeFromCurrentDateTime());
			SerialPointeuse dataToSend = new SerialPointeuse(uid, ldtTimeOnClick, PointeuseController.getFullTimeFromCurrentDateTime());

			//send it
			PointeuseController.sendPointeuseDataTCP(dataToSend, textFieldIP, spPortNum);

			//readPointeuseDataTCP(8080);
		}else {
			//custom title, error icon
			JOptionPane.showMessageDialog(frameRef,
					"The IP Address is not in a correct format.",
					"Insane error",
					JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("the ip Address isn't a correct IP");
		}


	}


}