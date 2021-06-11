package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.view.CUEmployee;
import core.view.Main_view;
import environnementEntreprise.Company;


//this button is located on the employeecard of the mainframe, 
//it is the add or the modified button (depends of "status")
//it create the form  "CUDepartments"
public class ButtonAUeEmployee implements ActionListener{
	Integer[] hoursList= {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
	Integer[] minutesList= {0,15,30,45};
	private Company entreprise;
	private JTextField nameField;
	private JTextField firstnameField;
	private JComboBox<String> departmentBox;
	private DefaultTableModel model;
	private ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs;
	private JTable employeeTable;
	private int status; //=0 if add; =1 if modify
	private int selectedline=0;


	public ButtonAUeEmployee(Company Entreprise,DefaultTableModel model) {
		entreprise=Entreprise;
		this.nameField=new JTextField("");
		this.firstnameField=new JTextField("");
		this.departmentBox=new JComboBox<String>();
		this.model=model;
		status = 0;

		this.tabBoxs=new ArrayList<ArrayList<JComboBox<Integer>>>(5);
		for (int i=0;i<5;i++) {
			this.tabBoxs.add(new ArrayList<JComboBox<Integer>>(4));
		}

		for(int i=0;i<5;i++) {
			tabBoxs.get(i).add(new JComboBox<Integer>(hoursList)); 
			tabBoxs.get(i).add(new JComboBox<Integer>(minutesList)); 
			tabBoxs.get(i).add(new JComboBox<Integer>(hoursList)); 
			tabBoxs.get(i).add(new JComboBox<Integer>(minutesList)); 
		}
	}
	public ButtonAUeEmployee(Company Entreprise,DefaultTableModel model,JTable employeeTable,int status) {
		entreprise=Entreprise;
		this.nameField=new JTextField("");
		this.firstnameField=new JTextField("");
		this.departmentBox=new JComboBox<String>();
		this.model=model;
		this.employeeTable=employeeTable;
		this.status=status;

		this.tabBoxs=new ArrayList<ArrayList<JComboBox<Integer>>>(5);	    
		for (int i=0;i<5;i++) {
			tabBoxs.add(new ArrayList<JComboBox<Integer>>(4));}
		for(int i=0;i<5;i++) {
			tabBoxs.get(i).add(new JComboBox<Integer>(hoursList)); 
			tabBoxs.get(i).add(new JComboBox<Integer>(minutesList)); 
			tabBoxs.get(i).add(new JComboBox<Integer>(hoursList)); 
			tabBoxs.get(i).add(new JComboBox<Integer>(minutesList)); 
		}

	}


	@Override
	public void actionPerformed(ActionEvent ae) {

		departmentBox.removeAllItems();
		for(int i=0;i<entreprise.getDepartments().size();i++) {
			departmentBox.addItem(entreprise.getDepartments().get(i).getName());

		}



		if (status==1 && employeeTable.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Vous n'avez selectionné aucune ligne!", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		if (status==1) {
			selectedline=employeeTable.getSelectedRow();//getting the row to modify
			this.nameField.setText(employeeTable.getValueAt(selectedline, 1).toString());//setting the text of namefield of the further form 
			this.firstnameField.setText(employeeTable.getValueAt(selectedline, 2).toString());//setting the text of firstnamefield of the further form 

			int numberofdepartment=0;
			//preselect departmentcombobox
			for (int i =0 ;i<entreprise.getDepartments().size();i++) {
				if (employeeTable.getValueAt(selectedline, 3).toString()==entreprise.getDepartments().get(i).getName()) {
					this.departmentBox.setSelectedIndex(i);
					numberofdepartment=i;


				}
			}
			//preselect schedules combobox
			for (int i=0;i<entreprise.getDepartments().get(numberofdepartment).getEmployees().size();i++) {

				for (int j =0;j<5;j++) {
					this.tabBoxs.get(j).get(0).setSelectedIndex(entreprise.getDepartments().get(numberofdepartment).getEmployees().get(i).getSCH().getSCH().get(j).getL().getHour());
					this.tabBoxs.get(j).get(1).setSelectedIndex(entreprise.getDepartments().get(numberofdepartment).getEmployees().get(i).getSCH().getSCH().get(j).getL().getMinute());
					this.tabBoxs.get(j).get(2).setSelectedIndex(entreprise.getDepartments().get(numberofdepartment).getEmployees().get(i).getSCH().getSCH().get(j).getR().getHour());
					this.tabBoxs.get(j).get(3).setSelectedIndex(entreprise.getDepartments().get(numberofdepartment).getEmployees().get(i).getSCH().getSCH().get(j).getR().getMinute());
				}
			}

		}
		JFrame frame = new CUEmployee(entreprise,nameField, firstnameField,departmentBox,model,tabBoxs,status,employeeTable);
		frame.setVisible(true);
	}
}
