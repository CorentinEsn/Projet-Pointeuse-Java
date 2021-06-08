package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.Company;
import environnementEntreprise.Pair;
import environnementEntreprise.Schedule;

public class ButtonNewEmployee implements ActionListener {
	 private JTextField name;
	 private JTextField firstname;
	 private JComboBox<String> departmentbox;
	 private ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs;
	 private Company entreprise;
	 private DefaultTableModel model;
	 
	 public ButtonNewEmployee(Company entreprise, JTextField name, JTextField firstname, JComboBox<String> departmentbox, ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs, DefaultTableModel model) {
		 super();
		 this.entreprise=entreprise;
		 this.name=name;
		 this.firstname=firstname;
		 this.departmentbox=departmentbox;
		 this.tabBoxs=tabBoxs;
		 this.model=model;
	 }
	 
	 @Override
		public void actionPerformed(ActionEvent ae){
		 
		 //creation of the schedule
		 Schedule schedule=new Schedule();
		 for (int i=0;i<5;i++) {
			 //creation of the localtime stored in the schedule
			 //arrival
		 LocalTime arrivalLocalTime=LocalTime.of(
				 Integer.parseInt(tabBoxs.get(i).get(0).getSelectedItem().toString()),
				 Integer.parseInt(tabBoxs.get(i).get(1).getSelectedItem().toString()),
				 0, 0);
		 
		 //departure
		 LocalTime departureLocalTime=LocalTime.of(
				 Integer.parseInt(tabBoxs.get(i).get(2).getSelectedItem().toString()),
				 Integer.parseInt(tabBoxs.get(i).get(3).getSelectedItem().toString()),
				 0, 0);
		 
		 //merging them in a Pair
		 Pair<LocalTime,LocalTime> temPair=new Pair<LocalTime, LocalTime>(arrivalLocalTime, departureLocalTime);
		 schedule.addHrs(i, temPair);//adding pair to the schedule
	 }
	 }
}
