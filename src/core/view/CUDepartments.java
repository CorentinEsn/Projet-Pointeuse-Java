package core.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import core.controller.ButtonAUDepartment;
import core.controller.ButtonNewEmployeeDepartment;
import environnementEntreprise.Company;

public class CUDepartments extends JFrame {
	JLabel nameLabel = new JLabel("Nom : ");
    JTextField nameField;  // accepts up to 30 characters (French longest name is 27 character)
    
	JLabel descriptionLabel = new JLabel("Description : ");
    JTextArea descriptionArea ; 
    private DefaultTableModel model;
    
    Company entreprise;
    
	public CUDepartments(Company entreprise,DefaultTableModel model,JTextField nameField, JTextArea descriptionArea ) {
		super("Nouveau Département");
		this.model=model;
		this.nameField=nameField;
		this.descriptionArea=descriptionArea;
	      WindowListener l = new WindowAdapter() {
	          public void windowClosing(WindowEvent e){
	             setVisible(false);
	             CUDepartments.this.nameField.setText("");
	             CUDepartments.this.descriptionArea.setText("");
	             
	          }
	      };
		setSize(500, 400);
		this.entreprise=entreprise;
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
		add(descriptionLabel,grid);
		grid.gridx=1;
		grid.gridwidth=2;
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		descriptionArea.setBorder(BorderFactory.createCompoundBorder(border, 
			      BorderFactory.createEmptyBorder(10, 10, 10, 10)));;
		add(descriptionArea,grid);
		
		
		
		grid.gridy=grid.gridy+1;
		grid.gridx=1;
		grid.gridwidth=1;
		grid.fill = GridBagConstraints.NONE;
		JButton addButton=new JButton("Ajouter");
		addButton.addActionListener(new ButtonNewEmployeeDepartment(entreprise,nameField,descriptionArea,model));
		addButton.addActionListener(e->this.dispose());
		add(addButton,grid);
		
	}
	
}
