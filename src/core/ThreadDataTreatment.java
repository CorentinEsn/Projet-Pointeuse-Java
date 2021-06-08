package core;

import environnementEntreprise.Company;
import pointeuse.SerialPointeuse;

public class ThreadDataTreatment implements Runnable{

	private SerialPointeuse dataToTreat;
	private Company company;
	
	
	public ThreadDataTreatment(SerialPointeuse data, Company company) {
		if(data == null || company == null) {
			throw new IllegalArgumentException("Error, one of the variable where set to null in ThreadDataTreatment");
		}
		this.dataToTreat = data;
		this.company = company;
	}
	
	@Override
	public void run() {
		System.out.println("Treating Received data");
		
		//You got the data, which contains the UUID, 
		//a string (which you should not need) and a localdateTime of when the user checked in
		
		//gotta unpack it using getters, then putting in in your company how you wish
		//don't forget that the UUID could belong to no one
		
		
		
		System.out.println("Data has been treated");
	}

}
