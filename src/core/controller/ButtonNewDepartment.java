package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;
import core.view.*;

public class ButtonNewDepartment implements ActionListener {
	 private JTextField name;
	 private JTextArea description;
	 private Company entreprise;
	 private DefaultTableModel model;
	 public ButtonNewDepartment(Company entreprise, JTextField name, JTextArea description ,DefaultTableModel model) {
		 super();
		 this.entreprise=entreprise;
		 this.name=name;
		 this.description=description;
		 this.model=model;
	 }
	 
	 @Override
		public void actionPerformed(ActionEvent ae){
		 
		 Department temp=new Department(name.getText(),description.getText());
		 entreprise.addDepartment(temp);
		 			 model.addRow(
	                   new Object[]{
	                         entreprise.getDepartments().get(entreprise.getDepartments().size()-1).getName(),
	                         entreprise.getDepartments().get(entreprise.getDepartments().size()-1).getDescription()
	                   });
		 System.out.println(entreprise.getDepartments().get(entreprise.getDepartments().size()-1).getDescription());
		 name.setText("");
		 description.setText("");
	 }
}
