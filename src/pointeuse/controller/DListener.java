package pointeuse.controller;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Listener to add some placeholder when the textfields are empty. 
 * Will also remove the ability to press the button if all textfields aren't filled
 * @author Lilian
 *
 */
public class DListener implements DocumentListener{

	/**
	 * reference to the UUID textfield
	 */
	private JTextField tfUUID;
	/**
	 * reference to the IP Address textfield
	 */
	private JTextField tfIPAddress;
	/**
	 * reference to the button
	 */
	private JButton checkInOutbutton;

	/**
	 * Constructor
	 * 
	 * @param tfUUID {@link DListener#tfUUID}
	 * @param tfIPAddress {@link DListener#tfIPAddress}
	 * @param checkInOutbutton {@link DListener#checkInOutbutton}
	 */
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

	/**
	 * Every time a change occurs in one of the two Textfields {@link DListener#tfUUID} or {@link DListener#tfIPAddress},
	 * check whether each fields are filled or not
	 * if they are both filled, the button {@link DListener#checkInOutbutton} is activated so you can send the data
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		boolean canEnable = true;
		if (tfUUID.getText().isEmpty() || tfIPAddress.getText().isEmpty()) {
			canEnable = false;
		}

		checkInOutbutton.setEnabled(canEnable);
	}

}