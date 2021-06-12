/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonDel.
 */
public class ButtonDel implements ActionListener{
	
	/** The entreprise. */
	private Company entreprise;
	
	/** The model. */
	private DefaultTableModel model;
	
	/** The table. */
	JTable table;
	
	/** The status. */
	private int status; //if 0, employee, else department
	
	/** The message. */
	String message;
	
	/**
	 * Instantiates a new button del.
	 *
	 * @param entreprise the entreprise
	 * @param model the model
	 * @param table the table
	 * @param status the status
	 */
	//constructor
	public ButtonDel(Company entreprise, DefaultTableModel model,JTable table,int status) {
		this.entreprise=entreprise;
		this.model=model;
		this.table=table;
		this.status=status;
		
	}
	
	
	

	/**
	 * Action performed.
	 *
	 * @param ae the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent ae){
		 //showing an alert if there are no selected row
		if (table.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Vous n'avez selectionn� aucune ligne!", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if (status==0) {
				message="�tes vous sur de vouloir supprimer "+entreprise.getDepartments().get(table.getSelectedRow()).getName()+" ?";
			}
			else {
				message="�tes vous sur de vouloir supprimer "+table.getValueAt(table.getSelectedRow(), 1)+" ?";
			}
//create an alertbox to ask if the user really want to delete the item 
		int option = JOptionPane.showConfirmDialog(null,
				message,
				"�tes vous s�r de vous?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		
		//if user select "YES"
		if(option == JOptionPane.OK_OPTION){
			if (status==0) {
			  entreprise.delDepartment(entreprise.getDepartments().get(table.getSelectedRow())); //deleting the item for the data 
			  
			}
			else {
				for (int i=0;i<entreprise.getDepartments().size();i++) {
					for (int j= 0;j<entreprise.getDepartments().get(i).getEmployees().size();j++)
					if(entreprise.getDepartments().get(i).getEmployees().get(j).getUUID()==table.getValueAt(table.getSelectedRow(), 0)){
						entreprise.getDepartments().get(i).getEmployees().remove(j);
					}
				}
			}
			model.removeRow(table.getSelectedRow());//deleting the row of the table
			}
		
	}
}
	}


