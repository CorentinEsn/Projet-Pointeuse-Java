/*
 * 
 */
package core.controller;

import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import environnementEntreprise.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonNewEmployee.
 */
public class ButtonNewEmployee implements ActionListener {
	
	/** The name. */
	private JTextField name;
	
	/** The firstname. */
	private JTextField firstname;
	
	/** The departmentbox. */
	private JComboBox<String> departmentbox;
	
	/** The tab boxs. */
	private ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs;
	
	/** The entreprise. */
	private Company entreprise;
	
	/** The model. */
	private DefaultTableModel model;
	
	/** The employee table. */
	private JTable employeeTable;

	/**
	 * Instantiates a new button new employee.
	 *
	 * @param entreprise the entreprise
	 * @param name the name
	 * @param firstname the firstname
	 * @param departmentbox the departmentbox
	 * @param tabBoxs the tab boxs
	 * @param model the model
	 * @param employeeTable the employee table
	 */
	public ButtonNewEmployee(Company entreprise, JTextField name, 
			JTextField firstname, JComboBox<String> departmentbox, 
			ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs, 
			DefaultTableModel model,JTable employeeTable) {
		super();
		this.entreprise=entreprise;
		this.name=name;
		this.firstname=firstname;
		this.departmentbox=departmentbox;
		this.tabBoxs=tabBoxs;
		this.model=model;
		this.employeeTable=employeeTable;
	}

	/**
	 * Action performed.
	 *
	 * @param ae the ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae){

		//creation of the schedule
		Schedule schedule=new Schedule();
		model.setRowCount(0);
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

		for (int i=0;i<entreprise.getDepartments().size();i++) {
			if (entreprise.getDepartments().get(i).getName()==departmentbox.getSelectedItem().toString()) {
				entreprise.getDepartments().get(i).addEmployee(new Employee(name.getText(), firstname.getText(),schedule));;
			}
			for (int j=0;j<entreprise.getDepartments().get(i).getEmployees().size();j++) {
				Employee temp =entreprise.getDepartments().get(i).getEmployees().get(j);
				String check="Absent";
				if(temp.isCheckedIn()) {
					check="Présent";
				}
			model.addRow(
					new Object[] {
							temp.getUUID(),
							temp.getName(),
							temp.getFirstname(),
							entreprise.getDepartments().get(i).getName(),
							temp.getovertimeFormatted(),
							check

					}
					);
		}
		}

		name.setText(null);
		firstname.setText(null);


	}
}
