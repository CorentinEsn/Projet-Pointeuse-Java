package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;
import core.view.*;


//this button is located on the modifying form
public class ButtonModDepartment implements ActionListener {
	private JTextField name;
	private JTextArea description;
	private Company entreprise;
	private DefaultTableModel model;
	private Department oldDepartment;
	
//constructor using the data of the form
	public ButtonModDepartment(Company entreprise, JTextField name, JTextArea description ,DefaultTableModel model,Department oldDepartment) {
		super();
		this.entreprise=entreprise;
		this.name=name;
		this.description=description;
		this.model=model;
		this.oldDepartment=oldDepartment;
	}

	
	@Override
	public void actionPerformed(ActionEvent ae){
		
//creating a new department 
		Department temp=new Department(name.getText(),description.getText());
		int modif = entreprise.modDepartment(oldDepartment,temp);//replacing the old department with the new one
		model.removeRow(modif);//removing the department of the table
		model.addRow(//adding the new department on the table
				new Object[]{
						entreprise.getDepartments().get(modif).getName(),
						entreprise.getDepartments().get(modif).getDescription()
				});
		
		//retoring the field of the form with blank text
		name.setText("");
		description.setText("");
	}
}
