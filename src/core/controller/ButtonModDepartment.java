package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;
import core.view.*;

public class ButtonModDepartment implements ActionListener {
	private JTextField name;
	private JTextArea description;
	private Company entreprise;
	private DefaultTableModel model;
	private Department oldDepartment;
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

		Department temp=new Department(name.getText(),description.getText());
		int modif = entreprise.modDepartment(oldDepartment,temp);
		model.removeRow(modif);
		model.addRow(
				new Object[]{
						entreprise.getDepartments().get(modif).getName(),
						entreprise.getDepartments().get(modif).getDescription()
				});
		System.out.println(entreprise.getDepartments().get(entreprise.getDepartments().size()-1).getDescription());
		name.setText("");
		description.setText("");
	}
}