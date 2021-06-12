/*
 * @author Thomas Blumstein
 */
package core.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import core.controller.*;
import environnementEntreprise.*;


/**
 * The Class CUEmployee, Create a form to add or update an Employee
 */
public class CUEmployee extends JFrame {
	
	/** The Company. */
	Company entreprise;


	/** The name label. */
	JLabel nameLabel = new JLabel("Nom : ");
	
	/** The name TextField. */
	JTextField nameField = new JTextField(30); // accepts up to 30 characters (French longest name is 27 character)

	/** The firstname label. */
	JLabel firstnameLabel = new JLabel("Prénom : ");
	
	/** The firstname TextField. */
	JTextField firstnameField;

	/** The department label. */
	JLabel departmentLabel = new JLabel("Département : ");
	
	/** The department ComboBox. */
	JComboBox<String> departmentBox=new JComboBox<String>();

	/** The schedule label. */
	JLabel scheduleLabel=new JLabel("Emploi du temps :");
	
	/** The arrival label. */
	JLabel arrivalLabel=new JLabel("Arrivée :");
	
	/** The departure label. */
	JLabel departureLabel =new JLabel("Départ :");
	
	/** The days labels. */
	JLabel[] daysLabels= {new JLabel("Lundi : "),new JLabel("Mardi : "),new JLabel("Mercredi : "),new JLabel("Jeudi : "),new JLabel("Vendredi : ")};
	
	/** The ComboBoxs. */
	ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs;
	
	/** The DeafultTablemodel. */
	private DefaultTableModel model;  
	
	/** The old employee. */
	private Employee oldEmployee;
	
	/** The selectedline. */
	private int selectedline;
	
	/** The employee table. */
	private JTable employeeTable;

	/**
	 * Instantiates a new CU employee.
	 *
	 * @param entreprise the Company
	 * @param nameField the name TextField
	 * @param firstnameField the firstname TextField
	 * @param departmentBox the department ComboBox
	 * @param model the DefaultTableModel
	 * @param tabBoxs the ComboBoxs
	 * @param status the status to know if it's a modification or addition
	 * @param employeeTable the employee table
	 */
	public CUEmployee(Company entreprise, JTextField nameField,
			JTextField firstnameField,JComboBox<String> departmentBox,
			DefaultTableModel model,ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs,
			int status,JTable employeeTable) {
		super("Nouvel Employé");//creation of the frame

		//actions at the closing of the frame
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				setVisible(false);

			}
		};
		//settings the attributs
		setSize(500, 400);
		this.entreprise=entreprise;
		this.nameField=nameField;
		this.firstnameField=firstnameField;
		this.model=model;
		this.tabBoxs=tabBoxs;
		this.employeeTable=employeeTable;
		if (status==1) {
			this.selectedline=employeeTable.getSelectedRow();
		}
		if (status==1) {//if update, set the olddepartment
			
			for (int i=0;i<entreprise.getDepartments().size();i++) {
				for (int j=0;j<entreprise.getDepartments().get(i).getEmployees().size() ;j++) {
					if (entreprise.getDepartments().get(i).getEmployees().get(j).getUUID()==employeeTable.getValueAt(selectedline,0));{
						this.oldEmployee=entreprise.getDepartments().get(i).getEmployees().get(j);
					}
				}
			}
		}

		//adding all the items
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.insets=new Insets(3,3,3,3);
		grid.weightx = 5;
		grid.gridy = 0;
		grid.gridx = 0;
		add(nameLabel,grid);
		grid.gridx=1;
		grid.weightx = 0.0;
		grid.gridwidth=2;
		add(nameField,grid);

		grid.gridy = 1;
		grid.gridx = 0;
		grid.weightx = 1.0;
		grid.gridwidth=1;
		add(firstnameLabel,grid);
		grid.gridx=1;
		grid.gridwidth=2;
		add(firstnameField,grid);

		grid.gridy = 2;
		grid.gridx = 0;
		grid.gridwidth=1;
		add(departmentLabel,grid);
		grid.gridx=1;
		grid.gridwidth=2;
		add(departmentBox,grid);

		grid.gridy = 3;
		grid.gridx = 0;
		grid.gridwidth=1;
		add(scheduleLabel,grid);
		grid.gridx=1;
		add(arrivalLabel,grid);
		grid.gridx=3;
		add(departureLabel,grid);

		grid.gridy=4;

		for (int i=0;i<5;i++) {

			grid.gridy=i+4;
			grid.gridx=0;
			add(daysLabels[i],grid);
			grid.gridx=1;		
			for(int j=0;j<4;j++) {
				add(tabBoxs.get(i).get(j),grid);
				grid.gridx++;
			}
		}

		grid.gridy=grid.gridy+1;
		grid.gridx=0;
		grid.gridwidth=1;
		grid.fill = GridBagConstraints.NONE;
		//if add, button add
		if (status==0) {
			JButton addButton=new JButton("Ajouter");
			addButton.addActionListener(new ButtonNewEmployee(entreprise,nameField,firstnameField,departmentBox,tabBoxs,model,employeeTable));
			addButton.addActionListener(e->this.dispose());
			add(addButton,grid);
		}
		//if update, button update
		else {
			JButton modButton=new JButton("Modifier");
			modButton.addActionListener(new ButtonModEmployee(entreprise,nameField,firstnameField,departmentBox,tabBoxs,model,oldEmployee,selectedline));
			modButton.addActionListener(e->this.dispose());
			add(modButton,grid);
		}
	}

}
