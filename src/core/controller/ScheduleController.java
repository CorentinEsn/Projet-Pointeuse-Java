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

public class ScheduleController implements ActionListener{
	private ArrayList<ArrayList<LocalTime>> scheduleArrayList =new ArrayList<>();
	private Schedule SCH;
	private JTable table;
	private DefaultTableModel model;
	private String[]columns= {"Jour","Arrivée","Départ"};
	private String[]days= {"Lundi","Mardi","Mercredi","Jeudi","Vendredi"};
	private Company entreprise;
	private JTable employeeTable;


	public ScheduleController(Company entreprise,JTable employeeTable) {

		model=new DefaultTableModel(columns,0);
		table=new JTable(model);
		this.entreprise=entreprise;
		this.employeeTable=employeeTable;

	}


	@Override
	public void actionPerformed(ActionEvent ae) {

		if (employeeTable.getSelectedRow()==-1) {
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
