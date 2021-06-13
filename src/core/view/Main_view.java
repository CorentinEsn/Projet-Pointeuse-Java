/*
 * @author Thomas Blumstein
 * Create all the principal views of the app
 */
package core.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.controller.*;
import environnementEntreprise.*;
import pointeuse.ThreadReadPointeuseData;
import saving.Serializer;



/**
 * The Class Main_view.
 */
//**********MAIN FRAME with all view in it***********//
public class Main_view extends JFrame {

	/** The Company. */
	Company entreprise; //all the data of the company (departments, employees...)

	/** The employeetable. */
	JTable employeetable;

	/** The port's textfield. */
	JTextField portTextField;

	/** The DefaultTablemodel employee. */
	DefaultTableModel modelEmployee;

	/** The DefaultTablemodel department. */
	DefaultTableModel modelDepartment;

	/** The selection button for Time View. */
	JButton selectionButton=new JButton("Valider");

	/**
	 * Gets the selection button.
	 *
	 * @return the selection button
	 */
	public JButton getSelectionButton() {
		return selectionButton;
	}


	/**
	 * Sets the selection button.
	 *
	 * @param selectionButton the new selection button
	 */
	public void setSelectionButton(JButton selectionButton) {
		this.selectionButton = selectionButton;
	}


	/**
	 * Gets the DefaultTablemodel employee.
	 *
	 * @return the DefaultTablemodel employee
	 */
	public DefaultTableModel getModelEmployee() {
		return modelEmployee;
	}


	/**
	 * Sets the DefaultTablemodel employee.
	 *
	 * @param modelEmployee the new DefaultTablemodel employee
	 */
	public void setModelEmployee(DefaultTableModel modelEmployee) {
		this.modelEmployee = modelEmployee;
	}


	/**
	 * Gets the DefaultTablemodel department.
	 *
	 * @return the DefaultTablemodel department
	 */
	public DefaultTableModel getModelDepartment() {
		return modelDepartment;
	}


	/**
	 * Sets the DefaultTablemodel department.
	 *
	 * @param modelDepartment the new DefaultTablemodel department
	 */
	public void setModelDepartment(DefaultTableModel modelDepartment) {
		this.modelDepartment = modelDepartment;
	}


	/**
	 * Gets the employeetable.
	 *
	 * @return the employeetable
	 */
	public JTable getEmployeetable() {
		return employeetable;
	}


	/**
	 * Instantiates a new main view.
	 *
	 * @param Entreprise the entreprise
	 */
	public Main_view(Company Entreprise) {
		super(Entreprise.getName());//creating the frame with the name of the company (need to be the first line, dont move it)
		this.entreprise=Entreprise;
		//setDefaultCloseOperation(EXIT_ON_CLOSE);//setting the exit when clicking on the X
		WindowListener closeListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				Serializer serializer = new Serializer();
				serializer.serializeCompany(entreprise);
				serializer.serializeWriteCoreConfigData(Integer.parseInt(portTextField.getText()));
				System.exit(0);
			}
		};
		addWindowListener(closeListener);
		setSize(800, 500);//size of the frame
		JTabbedPane tabbedPane = new JTabbedPane();//creation of the container for the differents tabs
		JPanel card1 = new JPanel();//creation of the main container for the employee
		EmployeeView(card1);

		JPanel card2 = new JPanel();//creation of the main container for the departments
		DepartmentView(card2);

		JPanel card3 = new JPanel();//creation of the main container for the schedules
		TimeView(card3);

		JPanel card4 = new JPanel();//creation of the main container for the configuration
		ConfigView(card4);



		//adding all the tabs
		tabbedPane.addTab("Employés", card1);
		tabbedPane.addTab("Départements", card2);
		tabbedPane.addTab("Horaires", card3);
		tabbedPane.addTab("Config", card4);
		//adding the tabs panel
		add(tabbedPane, BorderLayout.CENTER);
	}


	/**
	 * Employee view.
	 *
	 * @param card the JPanel
	 */
	//**********Vue des Employés***********//
	public void EmployeeView(JPanel card) {
		card.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		String[] columns = { "ID", "Nom", "Prénom", "Département", "Heures supplémentaires", "Présent?" };
		modelEmployee = new DefaultTableModel(columns, 0){
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};//creation of the model for JTable

		int numberOfEmployees=0;
		for(int i=0;i<entreprise.getDepartments().size();i++) {//check if there are employees stocked
			if(entreprise.getDepartments().get(i).getEmployees().size()>0) {
				numberOfEmployees++;
			}
		}
		if (numberOfEmployees==0) {
			modelEmployee.addRow(//if there are no employees stocked, create an empty table
					new Object[]{
							"","","","","","",""		                         
					});
		}
		else{ //if there are departments in stock, use them to create the table
			for(int i=0;i<entreprise.getDepartments().size();i++) {
				for(int j=0;j<entreprise.getDepartments().get(i).getEmployees().size();j++) {
					Employee temp=entreprise.getDepartments().get(i).getEmployees().get(j);
					String checkString="Absent";
					if(temp.isCheckedIn()) {
						checkString="Présent";
					}
					modelEmployee.addRow(
							new Object[] {
									temp.getUUID(),
									temp.getName(),
									temp.getFirstname(),
									entreprise.getDepartments().get(i).getName(),
									temp.getovertimeFormatted(),
									checkString

							});
				}
			}		
		}



		employeetable = new JTable(modelEmployee);
		employeetable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		addButton.addActionListener(new ButtonAUeEmployee(entreprise,modelEmployee));
		buttons.add(addButton);
		JButton modButton = new JButton("Modifier");
		modButton.addActionListener(new ButtonAUeEmployee(entreprise,modelEmployee,employeetable,1));
		buttons.add(modButton);
		grid.gridx = 6;
		JButton delButton = new JButton("Supprimer");	
		delButton.addActionListener(new ButtonDel(entreprise,modelEmployee,employeetable,1));
		buttons.add(delButton);
		card.add(buttons, grid);
		grid.gridx = 7;
		JButton schButton = new JButton("Voir l'EDT");	
		schButton.addActionListener(new ScheduleController(entreprise,employeetable));
		buttons.add(schButton);
		card.add(buttons, grid);


	}


	/**
	 * Department view.
	 *
	 * @param card the JPanel
	 */
	//**********Department View***********//
	public void DepartmentView(JPanel card) {
		card.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();//used for placing the differents items
		String[] columns = { "Nom", "Description" };//names of the tabs columns
		modelDepartment = new DefaultTableModel(columns, 0){
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};
		if(entreprise.getDepartments().size()==0) {//if there are no departments stocked, create an empty table
			modelDepartment.addRow(
					new Object[]{
							"",
							""
					});
		}
		else{ //if there are departments in stock, use them to create the table
			for(int i=0;i<entreprise.getDepartments().size();i++) {
				modelDepartment.addRow(
						new Object[]{
								entreprise.getDepartments().get(i).getName(),
								entreprise.getDepartments().get(i).getDescription()
						});
			}
		}		

		JTable departmenttable = new JTable(modelDepartment);//creation of the table itself
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
		addButton.addActionListener(new ButtonAUDepartment(entreprise,modelDepartment));
		buttons.add(addButton);

		//Modifying button
		JButton modButton = new JButton("Modifier");	
		modButton.addActionListener(new ButtonAUDepartment(entreprise,modelDepartment,departmenttable));
		buttons.add(modButton);
		grid.gridx = 6;


		//Deleting button
		JButton delButton = new JButton("Supprimer");	
		delButton.addActionListener(new ButtonDel(entreprise,modelDepartment,departmenttable,0));
		buttons.add(delButton);
		card.add(buttons, grid);
	}

	/**
	 * Time view.
	 *
	 * @param card the JPanel
	 */
	public void TimeView(JPanel card) {
		card.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		JPanel date = new JPanel();//

		Integer[] daysList=new Integer[32];
		for (Integer i=1;i<32;i++) 
		{
			daysList[i]=i;
		}
		String months[] = {"","Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre"};
		LocalDateTime today=LocalDateTime.now();
		Integer[] yearsList = new Integer[today.getYear()-1969];
		for (int i=0;i<today.getYear()-1970;i++) {
			yearsList[i+1]=today.getYear()-i;
		}

		JComboBox<Integer> dayComboBox  = new JComboBox<Integer>(daysList); 
		dayComboBox.setSelectedIndex(today.getDayOfMonth());
		JComboBox<String> monthComboBox =new JComboBox<String>(months);
		monthComboBox.setSelectedIndex(today.getMonthValue());
		JComboBox<Integer> yearComboBox  = new JComboBox<Integer>(yearsList); 
		yearComboBox.setSelectedIndex(1);
		selectionButton=new JButton("Valider");

		JButton allButton= new JButton("Tout Visualiser");

		String[] columns = { "Date","ID", "Nom", "Prénom", "Heure d'arrivée", "Heure théorique d'arrivée", "Heure de départ",
		"Heure théorique de départ" };//names of the table columns
		DefaultTableModel modelTime = new DefaultTableModel(columns, 10){
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};
		selectionButton.addActionListener(new SelectionTimeListener(entreprise, modelTime, dayComboBox,monthComboBox,yearComboBox));
		allButton.addActionListener(new AllVisualisation(entreprise, modelTime));
		date.add(dayComboBox);
		date.add(monthComboBox);
		date.add(yearComboBox);
		date.add(selectionButton);
		date.add(allButton);
		card.add(date, grid);

		JTable timeTable = new JTable(modelTime);//creation of the table itself
		selectionButton.doClick();
		//add evrything
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.gridy = 1;
		grid.gridwidth = 50;
		grid.gridx = 0;
		grid.weightx = 400;
		card.add(timeTable.getTableHeader(), grid);
		grid.gridy = 2;
		card.add(timeTable, grid);
		grid.gridx=1;


	}


	/**
	 * Config view of the app.
	 *
	 * @param card the Panel
	 */
	private void ConfigView(JPanel card) {

		JPanel port = new JPanel();//panel for the port selection


		JLabel coreDataPath = new JLabel(	"Company Data is stored in : "+
				System.getProperty("user.dir")+
				"CoreData"+File.separator+"CompanyFile.dat");
		JLabel configDataPath = new JLabel(	"Configuration Data is stored in : "+
				System.getProperty("user.dir")+
				"CoreData"+File.separator+"config.dat    ");
		

		JLabel Portlabel= new JLabel("Port : "); 

		portTextField= new JTextField("8080");	

		JLabel Infolabel= new JLabel("Le changement ne sera effectif quu'après redémarrage de l'application"); 
		
		JPanel companyPanel = new JPanel();//panel for the company name
		
		JLabel companyJLabel =new JLabel("Nom de Votre Entreprise :");
		
		JTextField companyField =new JTextField(10);
		companyField.setText(entreprise.getName());
		
		//add everything
		companyPanel.add(companyJLabel);
		companyPanel.add(companyField);
		port.add(Portlabel);
		port.add(portTextField);
		card.add(coreDataPath);
		card.add(configDataPath);
		port.add(Infolabel);
		card.add(companyPanel);
		card.add(port);
		

	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// Assemble all the pieces of the MVC
		Serializer serializer = new Serializer();
		Company Entreprise = serializer.unserialiseCompagny();
		int port = serializer.serializeReadCoreConfigData();


		Main_view v;

		v = new Main_view(Entreprise);
		Thread t = new Thread(new ThreadReadPointeuseData(Entreprise, port,v));
		t.start();
		v.setVisible(true);

	}
}
