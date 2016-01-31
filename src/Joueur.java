// classe joueur définis par nom, prenom, nombre de jetons, et ses 2 cartes en sous classe
public class Joueur {
	
	String nom;
	String prenom;
	int nbjetons;
	Carte C1;
	Carte C2;
	
	public Joueur(String prenom, String nom, int nbjetons, Carte C1, Carte C2){
		
		this.nom = nom;
		this.prenom = prenom;
		this.nbjetons = nbjetons;
		this.C1 = C1;
		this.C2 = C2;
	}
	
	public Joueur(Joueur J1){
		
		this(J1.prenom,J1.nom,J1.nbjetons,J1.C1,J1.C2);
	}

	public String toString(){
		
		if  (this.nbjetons <=2){
		return (this.prenom + " " + this.nom + " possède " + this.nbjetons + " jeton." + "\n" 
		+ "Ses cartes sont le " + this.C1 + " et le " + this.C2 + ".");
	}
		else{
			return (this.prenom + " " + this.nom + " possède " + this.nbjetons + " jetons." + "\n" 
					+ "Ses cartes sont le " + this.C1 + " et le " + this.C2 + ".");
		}
	}	
}
