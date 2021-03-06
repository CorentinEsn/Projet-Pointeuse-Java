package core;


import pointeuse.SerialPointeuse;

import environnementEntreprise.Company;
import environnementEntreprise.Employee;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.swing.SwingUtilities;

import core.view.Main_view;

/**
 * Thread to treat a data received by the server
 * @author Lilian
 * @author Adam
 * @author Thomas
 *
 */
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
	
	/**
	 * Will unpack the data, search for the correct employee and check him in or out.
	 * if no employee is found, will just ignore the package.
	 */
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
		
		view.getModelEmployee().setRowCount(0);
		for (int i=0;i<company.getDepartments().size();i++) {

			for (int j=0;j<company.getDepartments().get(i).getEmployees().size();j++) {
				Employee temp =company.getDepartments().get(i).getEmployees().get(j);
				String check="Absent";
				if(temp.isCheckedIn()) {
					check="Pr?sent";
				}
			view.getModelEmployee().addRow(
					new Object[] {
							temp.getUUID(),
							temp.getName(),
							temp.getFirstname(),
							temp.getName(),
							temp.getovertimeFormatted(),
							check
							
					}
					);
			
		}
		}
		view.getSelectionButton().doClick();
		System.out.println("Data has been treated");
	}

}
