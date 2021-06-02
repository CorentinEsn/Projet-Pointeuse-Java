package coreView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

public class CUDepartments extends JFrame {
	
	Integer[] hoursList= {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
	Integer[] minutesList= {0,15,30,45};
	JLabel nameLabel = new JLabel("Nom : ");
    JTextField nameField = new JTextField(30); // accepts up to 30 characters (French longest name is 27 character)
    
	JLabel descriptionLabel = new JLabel("Description : ");
    JTextArea descriptionArea = new JTextArea(3,10); 
    
    
	public CUDepartments() {
		super("Nouveau Département");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		
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
		add(new JButton("Ajouter"),grid);
	}
	
	public static void main(String[] args) {
		// Assemble all the pieces of the MVC
		CUDepartments v = new CUDepartments();
		v.setVisible(true);
		}
}
