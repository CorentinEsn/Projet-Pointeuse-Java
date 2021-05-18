package pointeuse;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.*;



public class PoiteuseController extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PoiteuseController() {
	      super("Pointeuse");//titre de la fenetre

	      WindowListener l = new WindowAdapter() {
	         public void windowClosing(WindowEvent e){
	            System.exit(0);
	         }
	      };

	      addWindowListener(l);
	      
	      String sLdtDateCurrent = getFullDateFromCurrentDateTime();
	      String sLdtTimeCurrent = getFullTimeFromCurrentDateTime();
	      //System.out.println(ldtCurrentString);
	      JLabel  lbDateCurrent = new JLabel (sLdtDateCurrent);
	      JLabel  lbTimeCurrent = new JLabel (sLdtTimeCurrent);
	      
	      JButton bouton = new JButton("Check In/Out"); 
	      JTextField  testField1 = new JTextField (15);
	      TextPrompt tp = new TextPrompt("Employe UUID", testField1);
	      
	      JPanel frame = new JPanel();
	      frame.setLayout(new GridBagLayout());
	      
	      GridBagConstraints gbc = new GridBagConstraints();
	      
	      gbc.gridx = 1;
	      gbc.gridy = 0; // la grille commence en (0, 0)	      
	      gbc.anchor = GridBagConstraints.LINE_START; 
	      gbc.insets = new Insets(10, 15, 0, 0);
	      frame.add(lbDateCurrent, gbc);
	      
	      gbc.gridx = 1;
	      gbc.gridy = 1;
	      frame.add(lbTimeCurrent, gbc);
	      
	      
	      
	      gbc.gridx = 0;
	      gbc.gridy = 2;
	      frame.add(testField1, gbc);
	      
	      gbc.gridx = 2;
	      frame.add(bouton, gbc);
	      
	      
	      
	      setContentPane(frame);
	      //frame.setMinimumSize(new Dimension(400, 200));
	      
	      setSize(500,200);
	      setVisible(true);
	   }
	
	public static void main(String [] args){
	      JFrame frame = new PoiteuseController();
	   }
	
	private String getFullDateFromCurrentDateTime() {
		
		LocalDateTime ldtCurrent = LocalDateTime.now();
	      
	    DateTimeFormatter formatter;
	    
	      
		switch (ldtCurrent.getDayOfMonth() % 10) {
        case 1:
        	formatter = DateTimeFormatter.ofPattern("MMM d'st' , u");
    	    formatter = formatter.withLocale(Locale.ENGLISH);
    	    break;
        case 2:
        	formatter = DateTimeFormatter.ofPattern("MMM d'nd' , u");
    	    formatter = formatter.withLocale(Locale.ENGLISH);
    	    break;
        case 3:
        	formatter = DateTimeFormatter.ofPattern("MMM d'rd' , u");
    	    formatter = formatter.withLocale(Locale.ENGLISH);
    	    break;
        default:
        	formatter = DateTimeFormatter.ofPattern("MMM d'th' , u");
    	    formatter = formatter.withLocale(Locale.ENGLISH);
		}
		
		
	   return ldtCurrent.format(formatter);
	}
	
	
	private String getFullTimeFromCurrentDateTime() {
		
		LocalDateTime ldtCurrent = LocalDateTime.now();
	      
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH':'mm");
	    String endLdt = ldtCurrent.format(formatter);
	    
	    int currentMin = ldtCurrent.getMinute() % 15;
	    
	    if(currentMin > 7) {
	    	ldtCurrent = ldtCurrent.plusMinutes(15-currentMin);
	    }else {
	    	ldtCurrent = ldtCurrent.minusMinutes(currentMin);
	    }
 
	    
	    endLdt = endLdt+" ... Let's say "+ldtCurrent.format(formatter);
	    
		return endLdt;
	}
	
	
}

