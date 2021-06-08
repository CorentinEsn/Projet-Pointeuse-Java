package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import core.view.*;
import environnementEntreprise.Company;

public class ButtonAUDepartment implements ActionListener{
	private JTextField nameField;
	private JTextArea descriptionArea;
	private JTable departmenttable;
	private Company entreprise;
	private DefaultTableModel model;
	
	public ButtonAUDepartment(Company entreprise,DefaultTableModel model) {
		this.entreprise=entreprise;
		this.nameField=new JTextField("");
		this.descriptionArea=new JTextArea(3,10);
		this.model=model;
		this.departmenttable=new JTable();
	}
	public ButtonAUDepartment(Company entreprise,DefaultTableModel model,JTable departmenttable) {
		
		this.entreprise=entreprise;
		this.nameField=new JTextField("");
		this.descriptionArea=new JTextArea(3,10);
		this.model=model;
		this.departmenttable=departmenttable;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(departmenttable.getSelectedRow()>0) {
			int ligneSelectionnee=departmenttable.getSelectedRow();
			System.out.println(ligneSelectionnee);
			this.nameField.setText(departmenttable.getValueAt(ligneSelectionnee, 0).toString());
			this.descriptionArea.setText(departmenttable.getValueAt(ligneSelectionnee, 1).toString());
		}
		JFrame frame = new CUDepartments(entreprise,model,nameField,descriptionArea);
		frame.setVisible(true);
}
	
}

