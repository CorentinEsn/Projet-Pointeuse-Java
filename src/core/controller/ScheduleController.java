/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.view.ScheduleView;
import environnementEntreprise.Company;
import environnementEntreprise.Schedule;


/**
 * The Class ScheduleController.
 * used to instantiate a new view of Schedule
 */
public class ScheduleController implements ActionListener{
	
	/** The schedule array list. */
	private ArrayList<ArrayList<LocalTime>> scheduleArrayList =new ArrayList<>();
	
	/** The schedule. */
	private Schedule SCH;
	
	/** The table. */
	private JTable table;
	
	/** The model. */
	private DefaultTableModel model;
	
	/** The columns. */
	private String[]columns= {"Jour","Arrivée","Départ"};
	
	/** The days. */
	private String[]days= {"Lundi","Mardi","Mercredi","Jeudi","Vendredi"};
	
	/** The entreprise. */
	private Company entreprise;
	
	/** The employee table. */
	private JTable employeeTable;


	/**
	 * Instantiates a new schedule controller.
	 *
	 * @param entreprise the entreprise
	 * @param employeeTable the employee table
	 */
	public ScheduleController(Company entreprise,JTable employeeTable) {

		model=new DefaultTableModel(columns,0);
		table=new JTable(model);
		this.entreprise=entreprise;
		this.employeeTable=employeeTable;

	}


	/**
	 * Action performed.
	 *
	 * @param ae the ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (employeeTable.getSelectedRow()==-1) {//if 0 line selected
			JOptionPane.showMessageDialog(null, "Vous n'avez selectionné aucune ligne!", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else {
		for (int i=0;i<entreprise.getDepartments().size();i++) {
			for (int j=0;j<entreprise.getDepartments().get(i).getEmployees().size() ;j++) {
				if (entreprise.getDepartments().get(i).getEmployees().get(j).getUUID()==employeeTable.getValueAt(0, employeeTable.getSelectedRow()));{
					this.SCH=entreprise.getDepartments().get(i).getEmployees().get(j).getSCH();
				}
			}
		}
		for(int i=0;i<5;i++) {
			//adding a new row to the tables
			model.addRow(new Object[] {
					days[i],
					SCH.getSCH().get(i).getL(),
					SCH.getSCH().get(i).getR()
			}

					);
		}


		ScheduleView v=new ScheduleView(table);
		v.setVisible(true);
	}
	}
}
