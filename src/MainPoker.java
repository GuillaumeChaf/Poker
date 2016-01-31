
public class MainPoker {

	public static void main(String[] args) {
		
		Carte C1 = new Carte(3, 3);
		Carte C2 = new Carte(0, 12);
		Carte C3 = new Carte(2, 11);
		Carte C4 = new Carte(0, 6);
		Carte C5 = new Carte(2, 0);
		Carte C6 = new Carte(1,4);
		Carte C7 = new Carte(1,2);
		//System.out.println(C1);
		//System.out.println(C2);

		Joueur J1 = new Joueur("Pit","Smith",45,C1,C2);
		Joueur J2 = new Joueur("Jack","Bishop",1,C6,C7);
		
		Table T1 = new Table(C1,C2,C3,C4,C5);
		
		Combinaison Co1 = new Combinaison(J2,T1);
	
		
		System.out.println(Co1.paire());
		System.out.println(Co1.brelan());
	
}
}