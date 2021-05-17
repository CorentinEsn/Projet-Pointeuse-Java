package pointeuse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextPrompt extends JLabel implements FocusListener, DocumentListener 
{

	
	private JTextComponent component;
	private Document document;
	
	
	
	public TextPrompt(String text, JTextComponent component)
	{
		this.component = component;
		document = component.getDocument();
	
		setText( text );
		setFont( component.getFont() );
		setForeground( component.getForeground() );
		setBorder( new EmptyBorder(component.getInsets()) );
		setHorizontalAlignment(JLabel.LEADING);
	
		component.addFocusListener( this );
		document.addDocumentListener( this );
	
		component.setLayout( new BorderLayout() );
		component.add( this );
		checkForPrompt();
	}
	
	private void checkForPrompt()
	{
		//  Text has been entered, remove the prompt
		if (document.getLength() > 0)
		{
			setVisible( false );
			
		}else {
			setVisible( true );
		}
	
	
		return;

	}
	
	//Implement FocusListener
	
	public void focusGained(FocusEvent e)
	{
		checkForPrompt();
	}
	
	public void focusLost(FocusEvent e)
	{
		checkForPrompt();
	}
	
	//Implement DocumentListener
	
	public void insertUpdate(DocumentEvent e)
	{
		checkForPrompt();
	}
	
	public void removeUpdate(DocumentEvent e)
	{
		checkForPrompt();
	}
	
	public void changedUpdate(DocumentEvent e) {}
}
