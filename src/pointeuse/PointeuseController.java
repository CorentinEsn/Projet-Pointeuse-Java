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


//TODO: make it into view/controller (all the listener => class implements listener);
//view extends JFrame, the view create it all, and call function from the controller with the listener
public class PointeuseController{


	private static final long serialVersionUID = 1L;
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH':'mm");
	public static final int delay = 1000; //milliseconds
	
	public PointeuseController() {
	      

	      /*WindowListener closeListener = new WindowAdapter() {
	         public void windowClosing(WindowEvent e){
	            System.exit(0);
	         }
	      };

	      addWindowListener(closeListener);
	      
	      String sLdtDateCurrent = getFullDateFromCurrentDateTime();
	      String sLdtTimeCurrent = 	LocalDateTime.now().format(formatter)
									+" ... Let's say "
									+getFullTimeFromCurrentDateTime().format(formatter);
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
	      checkInOutbutton.addActionListener(new AListener(tfUUID, tfIPAddress, spPort);
	      */
	      /*{
	    	   public void actionPerformed(ActionEvent ae){
	    	      String textFieldValue = tfUUID.getText();
	    	      String textFieldIP = tfIPAddress.getText();
	    	      int spPortNum = (int) spPort.getValue();
	    	      
	    	      UUID uid = UUID.fromString(textFieldValue);
	    	      System.out.println(uid);
	    	      
	    	      
	    	      String ldtTimeOnClick = getFullTimeFromCurrentDateTime().format(formatter);
	    	      
	    	      System.out.println(textFieldValue + " checked in/out at " + ldtTimeOnClick);
	    	      
	    	      SerialPointeuse dataToSend = new SerialPointeuse(tfUUID.getText(), ldtTimeOnClick, getFullTimeFromCurrentDateTime());
	    	      
	    	      sendPointeuseDataTCP(dataToSend, textFieldIP, spPortNum);
	    	      
	    	      readPointeuseDataTCP(8080);

	    	   }
	    	}*/
	      
	      
	      //DocumentListener tfListener = new DListener(tfUUID, tfIPAddress, checkInOutbutton);
	      /*{
	    	    @Override
	    	    public void removeUpdate(DocumentEvent e) { 
	    	    	changedUpdate(e);
	    	    }
	    	    
	    	    @Override
	    	    public void insertUpdate(DocumentEvent e) { 
	    	    	changedUpdate(e); 
	    	    }

	    	    @Override
	    	    public void changedUpdate(DocumentEvent e) {
	    	        boolean canEnable = true;
	    	        if (tfUUID.getText().isEmpty() || tfIPAddress.getText().isEmpty()) {
	    	                canEnable = false;
	    	        }
	    	        
	    	        checkInOutbutton.setEnabled(canEnable);
	    	    }
	    	};*/
	      
	      /*
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
	      */
	      /*{
	          public void actionPerformed(ActionEvent evt) {
	        	  String sLdtDateNew = getFullDateFromCurrentDateTime(); 
	    	      String sLdtTimeNew = 		LocalDateTime.now().format(formatter)
	    	    		  					+" ... Let's say "
	    	    		  					+getFullTimeFromCurrentDateTime().format(formatter);

	    	      lbTimeCurrent.setText(sLdtTimeNew);
	    	      lbDateCurrent.setText(sLdtDateNew);
	              //frame.repaint();
	          }
	      };*/

	      //timerRepaint = new Timer(delay, taskPerformer);
	      //timerRepaint.start();
	      
	      
	   }
	
	public static void main(String [] args){
	      JFrame frame = new PointeuseView();     
	   }
	
	
	/**
	 * give the current date with a specified format
	 * @return the date with a string format of "Month Day(st/nd/rd/th) , Year"
	 */
	public static String getFullDateFromCurrentDateTime() {
		
		LocalDateTime ldtCurrent = LocalDateTime.now();
	      
	    DateTimeFormatter formatterFull;
	    
	      
		switch (ldtCurrent.getDayOfMonth() % 10) {
        case 1:
        	formatterFull = DateTimeFormatter.ofPattern("MMM d'st' , u");
        	formatterFull = formatterFull.withLocale(Locale.ENGLISH);
    	    break;
        case 2:
        	formatterFull = DateTimeFormatter.ofPattern("MMM d'nd' , u");
        	formatterFull = formatterFull.withLocale(Locale.ENGLISH);
    	    break;
        case 3:
        	formatterFull = DateTimeFormatter.ofPattern("MMM d'rd' , u");
        	formatterFull = formatterFull.withLocale(Locale.ENGLISH);
    	    break;
        default:
        	formatterFull = DateTimeFormatter.ofPattern("MMM d'th' , u");
        	formatterFull = formatterFull.withLocale(Locale.ENGLISH);
		}
		
		
	   return ldtCurrent.format(formatterFull);
	}
	
	/**
	 * give the current time with a specified format, rounded to by 15 min
	 * @return the date with a LocalDateTime
	 */
	public static LocalDateTime getFullTimeFromCurrentDateTime() {
		
		LocalDateTime ldtCurrent = LocalDateTime.now();	    
	    int currentMin = ldtCurrent.getMinute() % 15;
	    
	    if(currentMin > 7) {
	    	ldtCurrent = ldtCurrent.plusMinutes(15-currentMin);
	    }else {
	    	ldtCurrent = ldtCurrent.minusMinutes(currentMin);
	    }
  
		return ldtCurrent;
	}
	
	
	/**
	 * create a thread that will send the data, as well as send the older unsent data, and stock them if it fails
	 * @param dataToSend the data to send
	 * @param address the IP address to which the data will be sent
	 * @param port the port to which the data will be sent
	 */
	public static void sendPointeuseDataTCP(SerialPointeuse dataToSend, String address, int port) {
		
		Thread t = new Thread(new ThreadSendPointeuseData(dataToSend, address, port));
		t.start();
		
	}
	
	/**
	 * create a thread that will recieve the data, currently used to test the sending
	 * @param port the port to which the data will be sent
	 * @return a SerialPointeuse object containing the data
	 */
	public SerialPointeuse readPointeuseDataTCP(int port) {
		
		SerialPointeuse dataToRead = new SerialPointeuse("", "", null);
		
		Thread t = new Thread(new ThreadReadPointeuseData(dataToRead, port));
		t.start();
		
		
		
		return dataToRead;
	}
	
}



class AListener implements ActionListener{

	private JTextField tfUUID;
	private JTextField tfIPAddress;
	private JSpinner spPort;
	
	public AListener(JTextField tfUUID, JTextField tfIPAddress, JSpinner spPort) {
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

		UUID uid = UUID.fromString(textFieldValue);
		System.out.println(uid);


		String ldtTimeOnClick = PointeuseController.getFullTimeFromCurrentDateTime().format(PointeuseController.formatter);

		System.out.println(textFieldValue + " checked in/out at " + ldtTimeOnClick);

		SerialPointeuse dataToSend = new SerialPointeuse(tfUUID.getText(), ldtTimeOnClick, PointeuseController.getFullTimeFromCurrentDateTime());

		PointeuseController.sendPointeuseDataTCP(dataToSend, textFieldIP, spPortNum);

		//readPointeuseDataTCP(8080);

	}


}


class DListener implements DocumentListener{

	private JTextField tfUUID;
	private JTextField tfIPAddress;
	private JButton checkInOutbutton;
	
	public DListener(JTextField tfUUID, JTextField tfIPAddress, JButton checkInOutbutton) {
		super();
		
		this.tfUUID = tfUUID;
		this.tfIPAddress = tfIPAddress;
		this.checkInOutbutton = checkInOutbutton;
	}
	
	
	@Override
	public void removeUpdate(DocumentEvent e) { 
		changedUpdate(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) { 
		changedUpdate(e); 
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		boolean canEnable = true;
		if (tfUUID.getText().isEmpty() || tfIPAddress.getText().isEmpty()) {
			canEnable = false;
		}

		checkInOutbutton.setEnabled(canEnable);
	}

}


class AffListener implements ActionListener{

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

