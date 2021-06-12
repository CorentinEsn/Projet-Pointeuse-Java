package pointeuse.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import pointeuse.ConfigPointeuse;
import pointeuse.ThreadSendPointeuseData;
import saving.Serializer;

public class WinListener implements WindowListener{


	private JTextField ipAddress;
	private JSpinner port;
	
	public WinListener(JTextField ipAddress, JSpinner port) {
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		ConfigPointeuse confToSave = new ConfigPointeuse(ipAddress.getText(), (int)port.getValue());
		new Serializer().serializeWritePointeuseConfigData(confToSave);
		ThreadSendPointeuseData.writeStockedData();
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
