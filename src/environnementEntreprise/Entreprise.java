package environnementEntreprise;

public class Entreprise {
	//attributs
	private String nom;
	private Departement[] departements;
	
	//methodes
	public Departement[] getDepartements() {
		return departements;
	}

	public void setDepartements(Departement[] departements) {
		this.departements = departements;
	}
	
	public String getNom() {
		return nom;
	}

	public void setName(String nom) {
		this.nom = nom;
	}

}
