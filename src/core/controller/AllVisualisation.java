/*
 * @author Thomas Blumstein
 */
package core.controller;

import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import environnementEntreprise.*;


/**
 * The Class AllVisualisation, used to visualize every pointing
 */
public class AllVisualisation implements ActionListener {
	
	/** The Company. */
	private Company entreprise; 
	
	/** The DefaultTableModel. */
	private DefaultTableModel model;

	/**
	 * Instantiates a new Visualisation.
	 *
	 * @param entreprise the Company
	 * @param model the DefaultTableModel
	 */
	public AllVisualisation(Company entreprise, DefaultTableModel model){
		this.entreprise=entreprise;
		this.model=model;

	}

	/**
	 * Action performed.
	 *
	 * @param e the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		ArrayList<Employee> employees=entreprise.allEmployees();
		
		for (int i=0;i<employees.size();i++) {
			if(employees.size()==0) {//if there are no employees checked, create an empty table
				model.addRow(
						new Object[]{
								"",
								""
						});
			}
			else{ //if there are employees checked, use them to create the table
				//create items to display
				ArrayList<LocalTime> arrivalTheoric=new ArrayList<>();
				ArrayList<LocalTime> departureTheoric=new ArrayList<>();
				ArrayList<String> arrivalEffective=new ArrayList<>();
				ArrayList<String> departureEffective=new ArrayList<>();
				ArrayList<LocalDate> dates=new ArrayList<>();
				for(int j=0;j<employees.get(i).getHistory().size();j++) {//getting the pointings of employees
					
				if(!employees.get(i).getHistory().isEmpty()){//is not empty, create the table
					for (HashMap.Entry< LocalDate, Pair<LocalTime, LocalTime>> entry : employees.get(i).getHistory().entrySet())
					{
						DateTimeFormatter hourFormat=DateTimeFormatter.ofPattern("HH':'mm") ;//format the hour to display
						arrivalEffective.add(employees.get(i).getHistory().get(entry.getKey()).getL().format(hourFormat));
					  	arrivalTheoric.add(employees.get(i).getSCH().getSCH().get(employees.get(i).dayOfWeek(entry.getKey())).getL());
					  	if (employees.get(i).getHistory().get(entry.getKey()).getR().getNano()!=1) {//test if the LocalTime si set or is default
					  		departureEffective.add(employees.get(i).getHistory().get(entry.getKey()).getR().format(hourFormat));
					  	}
					  	else {//if deafult, then display nothing
					  		departureEffective.add("");
						}
					  	departureTheoric.add(employees.get(i).getSCH().getSCH().get(employees.get(i).dayOfWeek(entry.getKey())).getR());
						dates.add(entry.getKey());
					}

					model.addRow(//new row to display
						new Object[]{
								dates.get(j),
								employees.get(i).getUUID(),
								employees.get(i).getName(),
								employees.get(i).getFirstname(),
								arrivalEffective.get(j),
								arrivalTheoric.get(j),
								departureEffective.get(j),
								departureTheoric.get(j)

						});
				}
				}
				}
			}

		}
	}


