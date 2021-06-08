package core.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableColumn;

import environnementEntreprise.Company;

public class CUEmployee extends JFrame {
	Company entreprise;
	
	Integer[] hoursList= {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
	Integer[] minutesList= {0,15,30,45};
	JLabel nameLabel = new JLabel("Nom : ");
    JTextField nameField = new JTextField(30); // accepts up to 30 characters (French longest name is 27 character)
    
	JLabel firstnameLabel = new JLabel("Prénom : ");
    JTextField firstnameField;
    
    JLabel departmentLabel = new JLabel("Département : ");
    JTextField departmentField=new JTextField();
    
    JLabel scheduleLabel=new JLabel("Emploi du temps :");
    JLabel arrivalLabel=new JLabel("Arrivée :");
    JLabel departureLabel =new JLabel("Départ :");
    JLabel[] daysLabels= {new JLabel("Lundi : "),new JLabel("Mardi : "),new JLabel("Mercredi : "),new JLabel("Jeudi : "),new JLabel("Vendredi : ")};
    ArrayList<ArrayList<JComboBox<Integer>>> tabBoxs=new ArrayList<>(5);
    
    public void putInTabBoxs() {
    	for (int i=0;i<5;i++) {
    		tabBoxs.add(new ArrayList<JComboBox<Integer>>(4));}
    	for(int i=0;i<5;i++) {
    		tabBoxs.get(i).add(new JComboBox<Integer>(hoursList)); 
    		tabBoxs.get(i).add(new JComboBox<Integer>(minutesList)); 
    		tabBoxs.get(i).add(new JComboBox<Integer>(hoursList)); 
    		tabBoxs.get(i).add(new JComboBox<Integer>(minutesList)); 
    	}
    }
    
    
	public CUEmployee(Company entreprise, JTextField nameField,
			JTextField firstnameField) {
		super("Nouvel Employé");
		
	      WindowListener l = new WindowAdapter() {
	          public void windowClosing(WindowEvent e){
	             setVisible(false);
	            
	          }
	       };
		setSize(500, 400);
		this.entreprise=entreprise;
		this.nameField=nameField;
		this.firstnameField=firstnameField;
		
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
		//add(departmentField,grid);
		
		grid.gridy = 3;
		grid.gridx = 0;
		grid.gridwidth=1;
		add(scheduleLabel,grid);
		grid.gridx=1;
		add(arrivalLabel,grid);
		grid.gridx=3;
		add(departureLabel,grid);
		
		putInTabBoxs();
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
		add(new JButton("Ajouter"),grid);
	}
	
}
