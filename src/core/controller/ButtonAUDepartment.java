package core.controller;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.view.*;
import environnementEntreprise.Company;


//this button is located on the departmentcard of the mainframe, 
//it is the add or the modified button (depends of "satus")
//it create the form  "CUDepartments"
public class ButtonAUDepartment implements ActionListener{
	
	//items for the form
	private JTextField nameField;
	private JTextArea descriptionArea;
	
	//items used to add or modify the datas
	private JTable departmenttable;
	private Company entreprise;
	private DefaultTableModel model;
	private int status; //=0 if add; =1 if modify
	private int selectedline=0;
	
	//constructor for adding button
	public ButtonAUDepartment(Company entreprise,DefaultTableModel model) {
		this.entreprise=entreprise;
		this.nameField=new JTextField("");
		this.descriptionArea=new JTextArea(3,10);
		this.model=model;
		this.departmenttable=new JTable();
		status=0;
	}
	
	//constructor for modifyng button
	public ButtonAUDepartment(Company entreprise,DefaultTableModel model,JTable departmenttable) {
		
		this.entreprise=entreprise;
		this.nameField=new JTextField("");
		this.descriptionArea=new JTextArea(3,10);
		this.model=model;
		this.departmenttable=departmenttable;
		status=1;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		 //showing an alert if there are no selected row
		if (status==1 && departmenttable.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Vous n'avez selectionné aucune ligne!", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
			if (status==1) {
			selectedline=departmenttable.getSelectedRow();//getting the row to modify
			this.nameField.setText(departmenttable.getValueAt(selectedline, 0).toString());//setting the text of namefield of the further form 
			this.descriptionArea.setText(departmenttable.getValueAt(selectedline, 1).toString());//setting the text of descriptionarea of the further form 
		}
			
		JFrame frame = new CUDepartments(entreprise,model,nameField,descriptionArea,status,selectedline);//creating the form frame
		frame.setVisible(true);
}
	
}

