/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.Company;
import environnementEntreprise.Employee;
import environnementEntreprise.Pair;


/**
 * The listener interface for receiving selectionTime events.
 * The class that is interested in processing a selectionTime
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addSelectionTimeListener<code> method. When
 * the selectionTime event occurs, that object's appropriate
 * method is invoked.
 *
 * @see SelectionTimeEvent
 */
public class SelectionTimeListener implements ActionListener{
	
	/** The entreprise. */
	Company entreprise;
	
	/** The employees here. */
	ArrayList<Employee> employeesHere;
	
	/** The model. */
	DefaultTableModel model;
	
	/** The date. */
	LocalDate date;
	
	/** The day combo box. */
	JComboBox<Integer>dayComboBox;
	
	/** The month combo box. */
	JComboBox<String> monthComboBox;
	
	/** The years combo box. */
	JComboBox<Integer>yearsComboBox;

/**
 * Instantiates a new selection time listener.
 *
 * @param entreprise the Company
 * @param model the model
 * @param dayComboBox the day combo box
 * @param monthComboBox the month combo box
 * @param yearsComboBox the years combo box
 */
public SelectionTimeListener(Company entreprise, DefaultTableModel model,JComboBox<Integer>dayComboBox,JComboBox<String> monthComboBox,JComboBox<Integer>yearsComboBox) {
	this.entreprise=entreprise;
	this.model=model;
	this.dayComboBox=dayComboBox;
	this.monthComboBox=monthComboBox;
	this.yearsComboBox=yearsComboBox;
	
}

/**
 * Action performed.
 *
 * @param ae the ActionEvent
 */
@Override
public void actionPerformed(ActionEvent ae) {
	this.date=LocalDate.of(Integer.parseInt(yearsComboBox.getSelectedItem().toString()),
			monthComboBox.getSelectedIndex(),
			Integer.parseInt(dayComboBox.getSelectedItem().toString()));
	model.setRowCount(0);
	employeesHere=entreprise.whoWasHere(date);
	if(employeesHere.size()==0) {//if there are no employees checked, create an empty table
		model.addRow(
				new Object[]{
						"",
						""
				});
	}
	else{ //if there are employees checked, use them to create the table
		for(int i=0;i<employeesHere.size();i++) {
			LocalTime arrivalTheoric=LocalTime.now();
			LocalTime departureTheoric=LocalTime.now();
			if(employeesHere.get(i).dayOfWeek(date)>=0) {
			 arrivalTheoric=employeesHere.get(i).getSCH().getSCH().get(employeesHere.get(i).dayOfWeek(date)).getL();
			 departureTheoric=employeesHere.get(i).getSCH().getSCH().get(employeesHere.get(i).dayOfWeek(date)).getR();
			}
			String departureEffective="";
			DateTimeFormatter hourFormat=DateTimeFormatter.ofPattern("HH':'mm") ;//format to display
			String arrivalEffective=employeesHere.get(i).getHistory().get(date).getL().format(hourFormat);
			if (!employeesHere.get(i).isCheckedIn()) {
				departureEffective=employeesHere.get(i).getHistory().get(date).getR().format(hourFormat);
			}
			System.out.println(employeesHere.get(i).getHistory().get(date).getR());
			model.addRow(
					//create the new row
					new Object[]{
							date,
							employeesHere.get(i).getUUID(),
							employeesHere.get(i).getName(),
							employeesHere.get(i).getFirstname(),
							arrivalEffective,
							arrivalTheoric,
							departureEffective,
							departureTheoric
							
					});
		}
	}		
}
}