// la table à la fin d'une manche composé des 5 cartes en sous classes 
public class Table {
	
	Carte C1;
	Carte C2;
	Carte C3;
	Carte C4;
	Carte C5;
	
	public Table(Carte C1, Carte C2, Carte C3, Carte C4, Carte C5){
			
		this.C1 = C1;
		this.C2 = C2;
		this.C3 = C3;
		this.C4 = C4;
		this.C5 = C5;
			}
	
	public Table(Table T1){
		
		this(T1.C1,T1.C2,T1.C3,T1.C4,T1.C5);
	}

	public String toString(){
		
		return ("Flop : " + this.C1 + ", " + this.C2 + ", " + this.C3  + "\n" + "Turn : " + this.C4 + "\n" + "River : "  + this.C5);
	}
}
