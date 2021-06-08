package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;
import core.view.*;

public class ButtonNewEmployeeDepartment implements ActionListener {
	 private JTextField name;
	 private JTextArea description;
	 private Company entreprise;
	 private DefaultTableModel model;
	 public ButtonNewEmployeeDepartment(Company entreprise, JTextField name, JTextArea description ,DefaultTableModel model) {
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
		 Object[][] tableData;
		 String[] names = { "Nom", "Description" };		 
			if(entreprise.getDepartments().size()==0) {
				tableData = new Object[1][2];
			}
			else{
				tableData = new Object[entreprise.getDepartments().size()][2];
			}
			for(int i=0;i<entreprise.getDepartments().size();i++) {
				tableData[i][0]=entreprise.getDepartments().get(i).getName();
				tableData[i][1]=entreprise.getDepartments().get(i).getDescription();
			}
			

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
