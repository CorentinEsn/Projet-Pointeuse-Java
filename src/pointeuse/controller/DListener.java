package pointeuse.controller;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class DListener implements DocumentListener{

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
