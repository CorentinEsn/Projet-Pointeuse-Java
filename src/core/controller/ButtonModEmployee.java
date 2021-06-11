package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import environnementEntreprise.Company;
import environnementEntreprise.Employee;
import environnementEntreprise.Pair;
import environnementEntreprise.Schedule;

public class ButtonModEmployee implements ActionListener{
	private JTextField name;
	private JTextField firstname;
	private JComboBox<String> departmentbox;
	private ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs;
	private Company entreprise;
	private DefaultTableModel model;
	private Employee oldEmployee;
	private int selectedline;


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
				model.addRow(
						new Object[] {
								entreprise.getDepartments().get(i).getEmployees().get(entreprise.getDepartments().get(i).getEmployees().size()-1).getUUID(),
								entreprise.getDepartments().get(i).getEmployees().get(entreprise.getDepartments().get(i).getEmployees().size()-1).getName(),
								entreprise.getDepartments().get(i).getEmployees().get(entreprise.getDepartments().get(i).getEmployees().size()-1).getFirstname(),
								entreprise.getDepartments().get(i).getName(),
								entreprise.getDepartments().get(i).getEmployees().get(entreprise.getDepartments().get(i).getEmployees().size()-1).getoverTime()
						}
						);
			}

		}
	}

}

