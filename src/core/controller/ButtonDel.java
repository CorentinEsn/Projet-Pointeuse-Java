package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;

public class ButtonDel implements ActionListener{
	private Company entreprise;
	private DefaultTableModel model;
	JTable departmentTable;
	
	//constructor
	public ButtonDel(Company entreprise, DefaultTableModel model,JTable departmentTable) {
		this.entreprise=entreprise;
		this.model=model;
		this.departmentTable=departmentTable;
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent ae){
		 //showing an alert if there are no selected row
		if (departmentTable.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Vous n'avez selectionné aucune ligne!", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else {
//create an alertbox to ask if the user really want to delete the item 
		int option = JOptionPane.showConfirmDialog(null,
				"Êtes vous sur de vouloir supprimer "+entreprise.getDepartments().get(departmentTable.getSelectedRow()).getName()+" ?",
				"Êtes vous sûr de vous?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		
		//if user select "YES"
		if(option == JOptionPane.OK_OPTION){
			  entreprise.delDepartment(entreprise.getDepartments().get(departmentTable.getSelectedRow())); //deleting the item for the data 
			  model.removeRow(departmentTable.getSelectedRow());//deleting the row of the table
			}
	}
}
}

