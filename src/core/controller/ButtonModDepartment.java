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
 * The Class ButtonModDepartment.
 * used to modify a Department with the data of the form
 */

public class ButtonModDepartment implements ActionListener {
	
	/** The name. */
	private JTextField name;
	
	/** The description. */
	private JTextArea description;
	
	/** The entreprise. */
	private Company entreprise;
	
	/** The model. */
	private DefaultTableModel model;
	
	/** The old department. */
	private Department oldDepartment;
	
/**
 * Instantiates a new button mod department.
 *
 * @param entreprise the Company
 * @param name the name
 * @param description the description
 * @param model the model
 * @param oldDepartment the old department
 */
//constructor using the data of the form
	public ButtonModDepartment(Company entreprise, JTextField name, JTextArea description ,DefaultTableModel model,Department oldDepartment) {
		super();
		this.entreprise=entreprise;
		this.name=name;
		this.description=description;
		this.model=model;
		this.oldDepartment=oldDepartment;
	}

	
	/**
	 * Action performed.
	 *
	 * @param ae the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent ae){
		
//creating a new department 
		Department temp=new Department(name.getText(),description.getText());
		model.setRowCount(0);
		int modif = entreprise.modDepartment(oldDepartment,temp);//replacing the old department with the new one
		for (int i=0;i<entreprise.getDepartments().size();i++) {
			 model.addRow(
	                   new Object[]{
	                         entreprise.getDepartments().get(i).getName(),
	                         entreprise.getDepartments().get(i).getDescription()
	                   });
		 }
		
		//restoring the field of the form with blank text
		name.setText("");
		description.setText("");
	}
}
