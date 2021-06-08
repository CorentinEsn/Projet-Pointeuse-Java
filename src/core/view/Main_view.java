package core.view;

import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.controller.*;
import environnementEntreprise.*;

public class Main_view extends JFrame {
	Company entreprise;
	Object[][] tableData;
	public Main_view(Company Entreprise) {
		super(Entreprise.getName());
		this.entreprise=Entreprise;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel card1 = new JPanel();
		EmployeeView(card1);

		JPanel card2 = new JPanel();
		DepartmentView(card2);
		JPanel card3 = new JPanel();
		TimeView(card3);

		tabbedPane.addTab("Employés", card1);
		tabbedPane.addTab("Départements", card2);
		tabbedPane.addTab("Horaires", card3);
		add(tabbedPane, BorderLayout.CENTER);
	}

	public void EmployeeView(JPanel card) {
		card.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		String[] names = { "ID", "Nom", "Prénom", "Département", "Heures supplémentaires", "Présent?",
				"Emploi du Temps" };
		if(entreprise.getDepartments().size()==0) {
			tableData = new Object[1][7];
		}
		else{
			tableData = new Object[entreprise.getDepartments().size()][7];
		}
		JTable employeetable = new JTable(tableData, names);
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
		addButton.addActionListener(new ButtonAUeEmployee(entreprise));
		buttons.add(addButton);
		JButton modButton = new JButton("Modifier");
		modButton.addActionListener(new ButtonAUeEmployee(entreprise));
		buttons.add(modButton);
		grid.gridx = 6;
		buttons.add(new JButton("Supprimer"));
		card.add(buttons, grid);

	}

	public void createDepartmentTable() {
		
	}
	public void DepartmentView(JPanel card) {
		card.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		String[] names = { "Nom", "Description" };
		if(entreprise.getDepartments().size()==0) {
			tableData = new Object[1][2];
		}
		else{
			tableData = new Object[entreprise.getDepartments().size()][2];
		}
		for(int i=0;i<entreprise.getDepartments().size();i++) {
			tableData[i][0]=entreprise.getDepartments().get(i).getName();
			tableData[i][1]=entreprise.getDepartments().get(i).getDescription();
		}
		DefaultTableModel model = new DefaultTableModel(names, 0);
		for(int i=0;i<entreprise.getDepartments().size();i++) {
			model.addRow(
	                   new Object[]{
	                         entreprise.getDepartments().get(entreprise.getDepartments().size()-1).getName(),
	                         entreprise.getDepartments().get(entreprise.getDepartments().size()-1).getDescription()
	                   });
		}
		JTable departmenttable = new JTable(model);
		departmenttable.getColumnModel().getColumn(0).setPreferredWidth(50);
		departmenttable.getColumnModel().getColumn(1).setPreferredWidth(350);

		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.gridy = 0;
		grid.gridwidth = 50;
		grid.gridx = 0;
		grid.weightx = 400;
		card.add(departmenttable.getTableHeader(), grid);
		grid.gridy = 1;
		card.add(departmenttable, grid);
		grid.gridy = 2;
		grid.gridx = 4;
		grid.weightx = 10;
		JPanel buttons = new JPanel();

		JButton addButton = new JButton("Ajouter");
		addButton.addActionListener(new ButtonAUDepartment(entreprise,model));
		buttons.add(addButton);
		JButton modButton = new JButton("Modifier");	
		modButton.addActionListener(new ButtonAUDepartment(entreprise,model,departmenttable));
		buttons.add(modButton);
		grid.gridx = 6;
		buttons.add(new JButton("Supprimer"));
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
		Main_view v = new Main_view(Entreprise);
		v.setVisible(true);

	}
}
