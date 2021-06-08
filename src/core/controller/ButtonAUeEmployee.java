package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import core.view.CUEmployee;
import environnementEntreprise.Company;

public class ButtonAUeEmployee implements ActionListener{
	private Company entreprise;
	private JTextField nameField;
	private JTextField firstnameField;
	private JComboBox<String> departmentBox;
	ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs=new ArrayList<>(5);
	
	public ButtonAUeEmployee(Company Entreprise) {
		entreprise=Entreprise;
		this.nameField=new JTextField("");
		this.firstnameField=new JTextField("");
		//this.departmentBox=new JComboBox<String>();
		//this.tabBoxs=new ArrayList<ArrayList<JComboBox<Integer>>>();
	}
	
	public ButtonAUeEmployee(JTextField nameField,
							JTextField firstnameField, 
							JComboBox<String> departmentBox, 
							ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs) {
		this.nameField=nameField;
		this.firstnameField=firstnameField;
		this.departmentBox=departmentBox;
		this.tabBoxs=tabBoxs;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		JFrame frame = new CUEmployee(entreprise,nameField, firstnameField);
		frame.setVisible(true);
	}
}
