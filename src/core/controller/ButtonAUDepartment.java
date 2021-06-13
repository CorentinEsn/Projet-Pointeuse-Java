/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.view.*;
import environnementEntreprise.Company;




/**
 * The Class ButtonAUDepartment.
 * this button is located on the departmentcard of the mainframe, 
 * it is the add or the modified button (depends of "status")
 * it create the form  "CUDepartments"
 */

public class ButtonAUDepartment implements ActionListener{

	/** The name TextField. */
	private JTextField nameField;

	/** The description TextArea. */
	private JTextArea descriptionArea;


	/**items used to add or modify the datas*/
	/** The departmenttable. */
	private JTable departmenttable;

	/** The entreprise. */
	private Company entreprise;

	/** The model. */
	private DefaultTableModel model;

	/** The status. */
	private int status; //=0 if add; =1 if modify

	/** The selectedline. */
	private int selectedline=0;

	/**
	 * Instantiates a new button AU department.
	 * Constructor for adding button
	 * @param entreprise the entreprise
	 * @param model the model
	 */

	public ButtonAUDepartment(Company entreprise,DefaultTableModel model) {
		this.entreprise=entreprise;
		this.nameField=new JTextField("");
		this.descriptionArea=new JTextArea(3,10);
		this.model=model;
		this.departmenttable=new JTable();
		status=0;
	}

	/**
	 * Instantiates a new button AU department.
	 * Constructor for modifyng button
	 * @param entreprise the entreprise
	 * @param model the model
	 * @param departmenttable the departmenttable
	 */

	public ButtonAUDepartment(Company entreprise,DefaultTableModel model,JTable departmenttable) {

		this.entreprise=entreprise;
		this.nameField=new JTextField("");
		this.descriptionArea=new JTextArea(3,10);
		this.model=model;
		this.departmenttable=departmenttable;
		status=1;
	}

	/**
	 * Action performed.
	 *
	 * @param ae the ActionEvent
	 */
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

