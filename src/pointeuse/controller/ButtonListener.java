package pointeuse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import pointeuse.SerialPointeuse;

public class ButtonListener implements ActionListener{

	private JTextField tfUUID;
	private JTextField tfIPAddress;
	private JSpinner spPort;
	
	private final String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
	
	public ButtonListener(JTextField tfUUID, JTextField tfIPAddress, JSpinner spPort) {
		super();
		this.tfUUID = tfUUID;
		this.tfIPAddress = tfIPAddress;
		this.spPort = spPort;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae){
		String textFieldValue = tfUUID.getText();
		String textFieldIP = tfIPAddress.getText();
		//if the IP has a correct format or not
		if(textFieldIP.matches(PATTERN) || textFieldIP.contentEquals("localhost")) {
			int spPortNum = (int) spPort.getValue();

			//UUID uid = UUID.fromString(textFieldValue);
			//System.out.println(uid);


			String ldtTimeOnClick = PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);

			System.out.println(textFieldValue + " checked in/out at " + ldtTimeOnClick);

			SerialPointeuse dataToSend = new SerialPointeuse(tfUUID.getText(), ldtTimeOnClick, PointeuseController.getFullTimeFromCurrentDateTime());

			PointeuseController.sendPointeuseDataTCP(dataToSend, textFieldIP, spPortNum);

			//readPointeuseDataTCP(8080);
		}else {
			throw new IllegalArgumentException("the ip Address isn't a correct IP");
		}
		

	}


}
