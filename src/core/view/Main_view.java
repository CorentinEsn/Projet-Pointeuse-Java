package core.view;

import java.awt.*;

import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.controller.*;
import environnementEntreprise.*;
import pointeuse.ThreadReadPointeuseData;


//**********MAIN FRAME with all view in it***********//
public class Main_view extends JFrame {
	Company entreprise; //all the data of the company (departments, employees...)
	public Main_view(Company Entreprise) {
		super(Entreprise.getName());//creating the frame with the name of the company (need to be the first line, dont move it)
		this.entreprise=Entreprise;
		setDefaultCloseOperation(EXIT_ON_CLOSE);//setting the exit when clicking on the X
		setSize(800, 500);//size of the frame
		JTabbedPane tabbedPane = new JTabbedPane();//creation of the container fo the differents tabs
		JPanel card1 = new JPanel();//creation of the main container for the employee
		EmployeeView(card1);

		JPanel card2 = new JPanel();//creation of the main container for the departments
		DepartmentView(card2);
		
		JPanel card3 = new JPanel();//creation of the main container for the schedules
		TimeView(card3);

		//adding all the tabs
		tabbedPane.addTab("Employés", card1);
		tabbedPane.addTab("Départements", card2);
		tabbedPane.addTab("Horaires", card3);
		//adding the tabs panel
		add(tabbedPane, BorderLayout.CENTER);
	}
	
	
	//**********Vue des Employés***********//
		public void EmployeeView(JPanel card) {
			card.setLayout(new GridBagLayout());
			GridBagConstraints grid = new GridBagConstraints();
			String[] columns = { "ID", "Nom", "Prénom", "Département", "Heures supplémentaires", "Présent?",
					"Emploi du Temps" };
			DefaultTableModel model = new DefaultTableModel(columns, 0);//creation of the model for JTable

			JTable employeetable = new JTable(model);
			grid.fill = GridBagConstraints.HORIZONTAL;
			grid.gridy = 0;
			grid.gridwidth = 50;
			grid.gridx = 0;
			grid.weightx = 400;
			card.add(employeetable.getTableHeader(), grid);
			grid.gridy = 1;
			card.add(employeetable, grid);
			grid.gridy = 2;
			grid.gridx = 4;
			grid.weightx = 10;
			grid.fill = GridBagConstraints.HORIZONTAL;
			JPanel buttons = new JPanel();

			
			JButton addButton = new JButton("Ajouter");
			addButton.addActionListener(new ButtonAUeEmployee(entreprise,model));
			buttons.add(addButton);
			JButton modButton = new JButton("Modifier");
			modButton.addActionListener(new ButtonAUeEmployee(entreprise,model));
			buttons.add(modButton);
			grid.gridx = 6;
			buttons.add(new JButton("Supprimer"));
			card.add(buttons, grid);

		}


		//**********Department View***********//
		public void DepartmentView(JPanel card) {
			card.setLayout(new GridBagLayout());
			GridBagConstraints grid = new GridBagConstraints();//used for placing the differents items
			String[] columns = { "Nom", "Description" };//names of the tabs columns
			DefaultTableModel model = new DefaultTableModel(columns, 0);//creation of the model for JTable
			if(entreprise.getDepartments().size()==0) {//if there are no departments stocked, create an empty table
				model.addRow(
		                   new Object[]{
		                         "",
		                         ""
		                   });
			}
			else{ //if there are departments in stock, use them to create the table
				for(int i=0;i<entreprise.getDepartments().size();i++) {
					model.addRow(
			                   new Object[]{
			                         entreprise.getDepartments().get(i).getName(),
			                         entreprise.getDepartments().get(i).getDescription()
			                   });
				}
			}		
			
			JTable departmenttable = new JTable(model);//creation of the table itself
			departmenttable.getColumnModel().getColumn(0).setPreferredWidth(50);//set the size of the first column
			departmenttable.getColumnModel().getColumn(1).setPreferredWidth(350);//set the size of second column

			
			//adding items on the department card
			grid.fill = GridBagConstraints.HORIZONTAL;
			grid.gridy = 0;
			grid.gridwidth = 50;
			grid.gridx = 0;
			grid.weightx = 400;
			card.add(departmenttable.getTableHeader(), grid);//header of the table (names of the columns)
			grid.gridy = 1;
			card.add(departmenttable, grid);//table itself
			grid.gridy = 2;
			grid.gridx = 4;
			grid.weightx = 10;
			JPanel buttons = new JPanel();//panel for all the buttons

			//Adding button
			JButton addButton = new JButton("Ajouter"); 
			addButton.addActionListener(new ButtonAUDepartment(entreprise,model));
			buttons.add(addButton);
			
			//Modifying button
			JButton modButton = new JButton("Modifier");	
			modButton.addActionListener(new ButtonAUDepartment(entreprise,model,departmenttable));
			buttons.add(modButton);
			grid.gridx = 6;
			
			
			//Deleting button
			JButton delButton = new JButton("Supprimer");	
			delButton.addActionListener(new ButtonDel(entreprise,model,departmenttable));
			buttons.add(delButton);
			card.add(buttons, grid);
		}

	public void TimeView(JPanel card) {
		card.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		JPanel date = new JPanel();

		Integer[] daysList=new Integer[32];
		for (Integer i=1;i<32;i++) 
		{
			daysList[i]=i;
		}
        String months[] = {"","Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre"};
        Integer[] yearsList = new Integer[LocalDateTime.now().getYear()-1969];
        for (int i=0;i<LocalDateTime.now().getYear()-1970;i++) {
        	yearsList[i+1]=LocalDateTime.now().getYear()-i;
        }
        
        JComboBox<Integer> dayComboBox  = new JComboBox<Integer>(daysList); 
        JComboBox<String> monthComboBox =new JComboBox<>(months);
        JComboBox<Integer> yearComboBox  = new JComboBox<Integer>(yearsList); 
		date.add(dayComboBox);
		date.add(monthComboBox);
		date.add(yearComboBox);
		card.add(date, grid);
		String[] names = { "ID", "Nom", "Prénom", "Heure d'arrivée", "Heure théorique d'arrivée", "Heure de départ",
				"Heure théorique de départ" };
		Object[][] tableData = new Object[20][7];
		JTable employeetable = new JTable(tableData, names);
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.gridy = 1;
		grid.gridwidth = 50;
		grid.gridx = 0;
		grid.weightx = 400;
		card.add(employeetable.getTableHeader(), grid);
		grid.gridy = 2;
		card.add(employeetable, grid);

	}

	public static void main(String[] args) {
		// Assemble all the pieces of the MVC
		Company Entreprise =new Company("Polytech") ;
		Department department=new Department("info","test");
		Entreprise.addDepartment(department);
		
		Thread t = new Thread(new ThreadReadPointeuseData(Entreprise, 8080));//NEED A WAY TO CHANGE THE PORT IN-APP
		t.start();
		
		Main_view v = new Main_view(Entreprise);
		v.setVisible(true);

	}
}
