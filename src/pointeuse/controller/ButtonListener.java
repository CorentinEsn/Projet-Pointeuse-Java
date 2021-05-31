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
		int spPortNum = (int) spPort.getValue();

		//UUID uid = UUID.fromString(textFieldValue);
		//System.out.println(uid);


		String ldtTimeOnClick = PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);

		System.out.println(textFieldValue + " checked in/out at " + ldtTimeOnClick);

		SerialPointeuse dataToSend = new SerialPointeuse(tfUUID.getText(), ldtTimeOnClick, PointeuseController.getFullTimeFromCurrentDateTime());

		PointeuseController.sendPointeuseDataTCP(dataToSend, textFieldIP, spPortNum);

		//readPointeuseDataTCP(8080);

	}


}
