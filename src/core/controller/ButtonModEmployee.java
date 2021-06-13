/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.Company;
import environnementEntreprise.Employee;
import environnementEntreprise.Pair;
import environnementEntreprise.Schedule;

/**
 * The Class ButtonModEmployee.
 * used to modify an employee using the data of the form
 */
public class ButtonModEmployee implements ActionListener{

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

	/** The old employee. */
	private Employee oldEmployee;

	/** The selectedline. */
	private int selectedline;


	/**
	 * Instantiates a new button mod employee.
	 *
	 * @param entreprise the entreprise
	 * @param name the name
	 * @param firstname the firstname
	 * @param departmentbox the departmentbox
	 * @param tabBoxs the tab boxs
	 * @param model the model
	 * @param oldEmployee the old employee
	 * @param selectedline the selectedline
	 */
	public ButtonModEmployee(Company entreprise, JTextField name, JTextField firstname, JComboBox<String> departmentbox, ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs, DefaultTableModel model, Employee oldEmployee,int selectedline) {
		super();
		this.entreprise=entreprise;
		this.name=name;
		this.firstname=firstname;
		this.departmentbox=departmentbox;
		this.tabBoxs=tabBoxs;
		this.model=model;
		this.oldEmployee=oldEmployee;
		this.selectedline=selectedline;
	}


	/**
	 * Action performed.
	 *
	 * @param ae the ActionEvent
	 */
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
		Employee newEmployee= new Employee(name.getText(), firstname.getText(),schedule);
		for (int i=0;i<entreprise.getDepartments().size();i++) {
			if (entreprise.getDepartments().get(i).getName()==departmentbox.getSelectedItem().toString()) {
				entreprise.getDepartments().get(i).modEmployee(oldEmployee,newEmployee);
				model.removeRow(selectedline);
				Employee employeeToModify=entreprise.getDepartments().get(i).getEmployees().get(entreprise.getDepartments().get(i).getEmployees().size()-1);

				String check="Absent";
				if (employeeToModify.isCheckedIn()) {
					check="Présent";
				}

				model.addRow(
						new Object[] {
								employeeToModify.getUUID(),
								employeeToModify.getName(),
								employeeToModify.getFirstname(),
								employeeToModify.getName(),
								employeeToModify.getoverTime(),
								check

						}
						);

			}
		}
		//reset of the frame
		name.setText(null);
		firstname.setText(null);
		for (int j =0;j<5;j++) {
			this.tabBoxs.get(j).get(0).setSelectedIndex(0);
			this.tabBoxs.get(j).get(1).setSelectedIndex(0);
			this.tabBoxs.get(j).get(2).setSelectedIndex(0);
			this.tabBoxs.get(j).get(3).setSelectedIndex(0);
		}
	}

}

