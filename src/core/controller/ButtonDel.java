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
	public ButtonDel(Company entreprise, DefaultTableModel model,JTable departmentTable) {
		this.entreprise=entreprise;
		this.model=model;
		this.departmentTable=departmentTable;
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent ae){

		int option = JOptionPane.showConfirmDialog(null,
				"Êtes vous sur de vouloir supprimer "+entreprise.getDepartments().get(departmentTable.getSelectedRow()).getName()+" ?",
				"Êtes vous sûr de vous?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION){
			  entreprise.delDepartment(entreprise.getDepartments().get(departmentTable.getSelectedRow()));  
			  model.removeRow(departmentTable.getSelectedRow());
			}
	}
}

