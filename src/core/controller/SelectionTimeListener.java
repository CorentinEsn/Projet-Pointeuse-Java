package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import environnementEntreprise.Company;
import environnementEntreprise.Employee;

public class SelectionTimeListener implements ActionListener{
	Company entreprise;
	ArrayList<Employee> employeesHere;
	DefaultTableModel model;
	LocalDate date;

public SelectionTimeListener(Company entreprise, DefaultTableModel model, LocalDate date) {
	this.entreprise=entreprise;
	this.model=model;
	this.date=date;
}

@Override
public void actionPerformed(ActionEvent ae) {
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
			DateTimeFormatter hourFormat=DateTimeFormatter.ofPattern("HH':'mm") ;
			String arrivalEffective=employeesHere.get(i).getHistory().get(date).getL().format(hourFormat);
			if (!employeesHere.get(i).isCheckedIn()) {
				departureEffective=employeesHere.get(i).getHistory().get(date).getR().format(hourFormat);
			}
			model.addRow(
					new Object[]{
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