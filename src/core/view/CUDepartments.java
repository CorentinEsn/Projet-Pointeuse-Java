/*
 * @author Thomas Blumstein
 * Frame to add or update a department
 */
package core.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import core.controller.ButtonModDepartment;
import core.controller.ButtonNewDepartment;
import environnementEntreprise.Company;
import environnementEntreprise.Department;

/**
 * The Class CUDepartments.
 */

public class CUDepartments extends JFrame {
	
	/** The name label. */
	JLabel nameLabel = new JLabel("Nom : ");
	
	/** The name field. */
	JTextField nameField; 
	
	/** The old department. */
	Department oldDepartment;
	
	/** The description label. */
	JLabel descriptionLabel = new JLabel("Description : ");
	
	/** The description area. */
	JTextArea descriptionArea ; 
	
	/** The model. */
	private DefaultTableModel model;

	/** The entreprise. */
	Company entreprise;

	/**
	 * Instantiates a new CU departments.
	 *
	 * @param entreprise the Company
	 * @param model the DefaultTableModel
	 * @param nameField a Textfield
	 * @param descriptionArea a JtextArea
	 * @param status the status to konw if it's a creation of modification
	 * @param selectedline the selected line of the table
	 */

	public CUDepartments(Company entreprise,DefaultTableModel model,JTextField nameField, JTextArea descriptionArea, int status,int selectedline) {
		super("Département");
		this.model=model;
		if (status==1) {//if update, set the olddepartment
			this.oldDepartment=entreprise.getDepartments().get(selectedline);
		}
		this.nameField=nameField;
		this.descriptionArea=descriptionArea;
		this.entreprise=entreprise;

		//action at the exit of the frame
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				setVisible(false);
				CUDepartments.this.nameField.setText("");
				CUDepartments.this.descriptionArea.setText("");

			}
		};


		setSize(500, 400);

		//adding the differents items
		setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.insets=new Insets(3,3,3,3);
		grid.weightx = 5;
		grid.gridy = 0;
		grid.gridx = 0;
		add(nameLabel,grid);//label "name"
		grid.gridx=1;
		grid.weightx = 0.0;
		grid.gridwidth=2;
		add(nameField,grid);//field of the name

		grid.gridy = 1;
		grid.gridx = 0;
		grid.weightx = 1.0;
		grid.gridwidth=1;
		add(descriptionLabel,grid);//label "Description"
		grid.gridx=1;
		grid.gridwidth=2;
		//cretion of a border for the textarea
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		descriptionArea.setBorder(BorderFactory.createCompoundBorder(border, //textarea of the description
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));;
				add(descriptionArea,grid);



				grid.gridy=grid.gridy+1;
				grid.gridx=1;
				grid.gridwidth=1;
				grid.fill = GridBagConstraints.NONE;
				//if add, button add
				if (status==0) {
					JButton addButton=new JButton("Ajouter");
					addButton.addActionListener(new ButtonNewDepartment(entreprise,nameField,descriptionArea,model));
					addButton.addActionListener(e->this.dispose());
					add(addButton,grid);
				}
				//if update, button update
				else {
					JButton modButton=new JButton("Modifier");
					modButton.addActionListener(new ButtonModDepartment(entreprise,nameField,descriptionArea,model,oldDepartment));
					modButton.addActionListener(e->this.dispose());
					add(modButton,grid);
				}

	}

}
