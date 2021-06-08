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

import javax.swing.*;
import javax.swing.event.*;

import java.io.Serializable;



public class PoiteuseController extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH':'mm");
	
	
	public PoiteuseController() {
	      super("Pointeuse");//windows title

	      WindowListener closeListener = new WindowAdapter() {
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
	      
	      
	      
	      
	      JTextField  tfUUID = new JTextField (15);
	      TextPrompt tp = new TextPrompt("Employe UUID", tfUUID);
	      
	      JButton checkInOutbutton = new JButton("Check In/Out");
	      checkInOutbutton.setEnabled(false);
	      checkInOutbutton.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent ae){
	    	      String textFieldValue = tfUUID.getText();
	    	    
	    	      String ldtTimeOnClick = getFullTimeFromCurrentDateTime().format(formatter);
	    	      
	    	      System.out.println(textFieldValue + " checked in/out at " + ldtTimeOnClick);
	    	      
	    	      SerialPointeuse dataToSend = new SerialPointeuse(tfUUID.getText(), ldtTimeOnClick, getFullTimeFromCurrentDateTime());
	    	      
	    	      sendPointeuseDataTCP(dataToSend, "localhost", 8080);
	    	      
	    	      readPointeuseDataTCP(8080);

	    	   }
	    	});
	      
	      
	      DocumentListener tfUUIDlistener = new DocumentListener() {
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
	    	        if (tfUUID.getText().isEmpty()) {
	    	                canEnable = false;
	    	        }
	    	        
	    	        checkInOutbutton.setEnabled(canEnable);
	    	    }
	    	};
	    	
	    	tfUUID.getDocument().addDocumentListener(tfUUIDlistener);
	      
	      
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
	      
	      
	      
	      setContentPane(frame);
	      //frame.setMinimumSize(new Dimension(400, 200));
	      
	      setSize(500,200);
	      setVisible(true);
	      
	      Timer timerRepaint;
	      int delay = 1000; //milliseconds
	      ActionListener taskPerformer = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	        	  String sLdtDateNew = getFullDateFromCurrentDateTime(); 
	    	      String sLdtTimeNew = 		LocalDateTime.now().format(formatter)
	    	    		  					+" ... Let's say "
	    	    		  					+getFullTimeFromCurrentDateTime().format(formatter);
	    	      
	    	      lbTimeCurrent.setText(sLdtTimeNew);
	    	      lbDateCurrent.setText(sLdtDateNew);
	              //frame.repaint();
	          }
	      };
	       
	      timerRepaint = new Timer(delay, taskPerformer);
	      timerRepaint.start();
	      
	      
	   }
	
	public static void main(String [] args){
	      JFrame frame = new PoiteuseController();     
	   }
	
	private String getFullDateFromCurrentDateTime() {
		
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
	
	
	private LocalDateTime getFullTimeFromCurrentDateTime() {
		
		LocalDateTime ldtCurrent = LocalDateTime.now();	    
	    int currentMin = ldtCurrent.getMinute() % 15;
	    
	    if(currentMin > 7) {
	    	ldtCurrent = ldtCurrent.plusMinutes(15-currentMin);
	    }else {
	    	ldtCurrent = ldtCurrent.minusMinutes(currentMin);
	    }
  
		return ldtCurrent;
	}
	
	
	//TODO : make those into whole classes
	public void sendPointeuseDataTCP(SerialPointeuse dataToSend, String address, int port) {
		
		Thread t = new Thread(new ThreadSendPointeuseData(dataToSend, address, port));
		t.start();
		
	}
	
	public SerialPointeuse readPointeuseDataTCP(int port) {
		
		SerialPointeuse dataToRead = new SerialPointeuse("", "", null);
		
		Thread t = new Thread(new ThreadReadPointeuseData(dataToRead, port));
		t.start();
		
		
		
		return dataToRead;
	}
	
}



