package core;


import pointeuse.SerialPointeuse;

import environnementEntreprise.Company;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.swing.SwingUtilities;

import core.view.Main_view;

public class ThreadDataTreatment implements Runnable{

	private SerialPointeuse dataToTreat;
	private Company company;
	private Main_view view;
	
	
	public ThreadDataTreatment(SerialPointeuse data, Company company,Main_view view) {
		if(data == null || company == null) {
			throw new IllegalArgumentException("Error, one of the variable where set to null in ThreadDataTreatment");
		}
		this.view=view;
		this.dataToTreat = data;
		this.company = company;
	}
	
	@Override
	public void run() {
		System.out.println("Treating Received data");
		
		//You got the data, which contains the UUID, 
		//a string (which you should not need) and a localdateTime of when the user checked in
		
		//unpacking
		UUID id = dataToTreat.getUserUUID();
		LocalDateTime date = dataToTreat.getDate();
		
		//treating
		int nbDepartments = company.getDepartments().size();
		int nbEmployees ;
		
			//search for the employee attached to that UUID
		for(int i = 0 ; i < nbDepartments ; i++) {
			nbEmployees = company.getDepartments().get(i).getEmployees().size();
			for(int j=0 ; j < nbEmployees ; j++) {
				if (company.getDepartments().get(i).getEmployees().get(j).getUUID().equals(id)) {
					
					//that employee is now checked in/out at that date, their history is also updated 
					System.out.println("Employee found");
					company.getDepartments().get(i).getEmployees().get(j).checkIO(date);
				}
				
			}
			
		}
		//gotta unpack it using getters, then putting in in your company how you wish
		//don't forget that the UUID could belong to no one
		
		view.getModelEmployee().fireTableDataChanged();
		view.getModelDepartment().fireTableDataChanged();
		view.getSelectionButton().doClick();
		System.out.println("Data has been treated");
	}

}
