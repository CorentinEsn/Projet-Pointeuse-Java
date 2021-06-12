package pointeuse.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.*;
/* we use this
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 */
import java.net.*;
/*we use this
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import javax.swing.*;
import javax.swing.event.*;

import pointeuse.ConfigPointeuse;
import pointeuse.TextPrompt;
import pointeuse.ThreadSendPointeuseData;
import pointeuse.controller.*;
import saving.Serializer;

import java.io.Serializable;

public class PointeuseView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor for the view, which generate the windows and all its elements
	 */
	public PointeuseView() {
		super("Pointeuse");//windows title
		ConfigPointeuse config = new Serializer().serializeReadPointeuseConfigData();
		
		
		

		//create the strings and label for the time anda date
		String sLdtDateCurrent = PointeuseController.getFullDateFromCurrentDateTime();
		String sLdtTimeCurrent = 	LocalDateTime.now().format(PointeuseController.formatter)
				+" ... Let's say "
				+PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);
		//System.out.println(ldtCurrentString);
		JLabel  lbDateCurrent = new JLabel (sLdtDateCurrent);
		JLabel  lbTimeCurrent = new JLabel (sLdtTimeCurrent);
		lbDateCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lbTimeCurrent.setHorizontalAlignment(SwingConstants.CENTER);



		//textfields of the IP and UUID
		JTextField  tfUUID = new JTextField (22);
		TextPrompt tpUUID = new TextPrompt("Employe UUID (format 8-4-4-4-12)", tfUUID);
		
		JTextField  tfIPAddress = new JTextField (9);
		TextPrompt tpIPAddress = new TextPrompt("IP Address", tfIPAddress);

		//Spinner to choose a port, restricted to a port's limits
		SpinnerModel modelPort = new SpinnerNumberModel(8080, 0, 65535, 1); 
		JSpinner spPort = new JSpinner(modelPort);
		JComponent editor = new JSpinner.NumberEditor(spPort, "#####");
		spPort.setEditor(editor);
		JLabel  lbPort = new JLabel ("Port :");
		
		if(config != null) {
			tfIPAddress.setText(config.getIpAddress());
			spPort.setValue(config.getPort());
		}

		//checking button, with a listener to make it do stuff
		JButton checkInOutbutton = new JButton("Check In/Out");
		checkInOutbutton.setEnabled(false);
		checkInOutbutton.addActionListener(new ButtonListener(tfUUID, tfIPAddress, spPort, this));

		//Listener that checks whether you can send the data or not
		DocumentListener tfListener = new DListener(tfUUID, tfIPAddress, checkInOutbutton);

		tfUUID.getDocument().addDocumentListener(tfListener);
		tfIPAddress.getDocument().addDocumentListener(tfListener);
		
		
		/**
		 * Listener that will serialize data when the app is closed
		 */
		WindowListener closeListener = new WinListener(tfIPAddress, spPort);
		addWindowListener(closeListener);
		
		

		JPanel frame = new JPanel();
		frame.setLayout(new GridBagLayout());


		//SETTING UP ALL THE PART OF THE UI

		GridBagConstraints gbc = new GridBagConstraints();


		gbc.gridx = 0;
		gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; 

		gbc.insets = new Insets(10, 15, 0, 0);
		frame.add(lbDateCurrent, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.add(lbTimeCurrent, gbc);



		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		frame.add(tfUUID, gbc);

		gbc.gridx = 2;
		frame.add(checkInOutbutton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		frame.add(tfIPAddress, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		frame.add(lbPort, gbc);

		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		frame.add(spPort, gbc);



		setContentPane(frame);
		//frame.setMinimumSize(new Dimension(400, 200));

		setSize(600,200);
		setVisible(true);


		//timer to refresh the date regularly
		Timer timerRepaint;
		ActionListener taskPerformer = new AffListener(lbTimeCurrent, lbDateCurrent);


		timerRepaint = new Timer(PointeuseController.delay, taskPerformer);
		timerRepaint.start();

	}

}