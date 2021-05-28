package pointeuse;

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

import java.io.Serializable;

public class PointeuseView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public PointeuseView() {
	      super("Pointeuse");//windows title

	      WindowListener closeListener = new WindowAdapter() {
	         public void windowClosing(WindowEvent e){
	            System.exit(0);
	         }
	      };

	      addWindowListener(closeListener);
	      
	      String sLdtDateCurrent = PointeuseController.getFullDateFromCurrentDateTime();
	      String sLdtTimeCurrent = 	LocalDateTime.now().format(PointeuseController.formatter)
									+" ... Let's say "
									+PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);
	      //System.out.println(ldtCurrentString);
	      JLabel  lbDateCurrent = new JLabel (sLdtDateCurrent);
	      JLabel  lbTimeCurrent = new JLabel (sLdtTimeCurrent);
	      lbDateCurrent.setHorizontalAlignment(SwingConstants.CENTER);
	      lbTimeCurrent.setHorizontalAlignment(SwingConstants.CENTER);
	      
	      
	      
	      
	      JTextField  tfUUID = new JTextField (22);
	      TextPrompt tpUUID = new TextPrompt("Employe UUID (format 8-4-4-4-12)", tfUUID);
	      
	      JTextField  tfIPAddress = new JTextField (9);
	      TextPrompt tpIPAddress = new TextPrompt("IP Address", tfIPAddress);
	      
	      
	      SpinnerModel modelPort = new SpinnerNumberModel(8080, 0, 65535, 1); 
	      JSpinner spPort = new JSpinner(modelPort);
	      JComponent editor = new JSpinner.NumberEditor(spPort, "#####");
	      spPort.setEditor(editor);
	      JLabel  lbPort = new JLabel ("Port :");
	      
	      JButton checkInOutbutton = new JButton("Check In/Out");
	      checkInOutbutton.setEnabled(false);
	      checkInOutbutton.addActionListener(new AListener(tfUUID, tfIPAddress, spPort));
	      
	      DocumentListener tfListener = new DListener(tfUUID, tfIPAddress, checkInOutbutton);
	      
	      tfUUID.getDocument().addDocumentListener(tfListener);
	      tfIPAddress.getDocument().addDocumentListener(tfListener);
	      
	      JPanel frame = new JPanel();
	      frame.setLayout(new GridBagLayout());
	      
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
	      
	      Timer timerRepaint;
	      ActionListener taskPerformer = new AffListener(lbTimeCurrent, lbDateCurrent);
	      
	      
	      timerRepaint = new Timer(PointeuseController.delay, taskPerformer);
	      timerRepaint.start();
	      
	}
	
}
