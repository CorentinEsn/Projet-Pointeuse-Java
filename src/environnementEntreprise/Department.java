package environnementEntreprise;

public class Departement {
	//attributs
	private String nom;
	private Employe[] employes;
	
	//methodes
	public Employe[] getEmployes() {
		return employes;
	}

	public void setEmployes(Employe[] employes) {
		this.employes = employes;
	}
	

	public String getNom() {
		return nom;
	}

	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
