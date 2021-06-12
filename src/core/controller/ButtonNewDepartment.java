/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;
import core.view.*;

/**
 * The Class ButtonNewDepartment.
 * used to create a department using the data in the form
 */
public class ButtonNewDepartment implements ActionListener {
	 
 	/** The name. */
 	private JTextField name;
	 
 	/** The description. */
 	private JTextArea description;
	 
 	/** The Company. */
 	private Company entreprise;
	 
 	/** The DefaultTableModel. */
 	private DefaultTableModel model;
	 
	 /**
 	 * Instantiates a new button new department.
 	 *
 	 * @param entreprise the Company
 	 * @param name the name
 	 * @param description the description
 	 * @param model the model
 	 */
 	public ButtonNewDepartment(Company entreprise, JTextField name, JTextArea description ,DefaultTableModel model) {
		 super();
		 this.entreprise=entreprise;
		 this.name=name;
		 this.description=description;
		 this.model=model;
	 }
	 
	 /**
 	 * Action performed.
 	 *
 	 * @param ae the ActionEvent
 	 */
 	@Override
		public void actionPerformed(ActionEvent ae){
		 model.setRowCount(0);
		 Department temp=new Department(name.getText(),description.getText());
		 entreprise.addDepartment(temp);//add to the storage
		 for (int i=0;i<entreprise.getDepartments().size();i++) {
			 //add tot the table
			 model.addRow(
	                   new Object[]{
	                         entreprise.getDepartments().get(i).getName(),
	                         entreprise.getDepartments().get(i).getDescription()
	                   });
		 }
		 			 
		 name.setText("");
		 description.setText("");
	 }
}
