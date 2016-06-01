package Poker;

public class MainPoker {

	public static void main(String[] args) {
		
		Carte C1 = new Carte(0, 0);
		Carte C2 = new Carte(0, 8);
		Carte C3 = new Carte(1, 3);
		Carte C4 = new Carte(2, 2);
		Carte C5 = new Carte(0, 2);
		Carte C6 = new Carte(0,5);
		Carte C7 = new Carte(1,9);
		//System.out.println(C1);
		//System.out.println(C2);

		Joueur J1 = new Joueur("Pit","Smith",45);
		Joueur J2 = new Joueur("Jack","Bishop",1);
		Joueur J3 = new Joueur("Jason","Cornett",25);
		Joueur J4 = new Joueur("Billy","Mcbones",46);
		Joueur J5 = new Joueur("Charlie","Adams",54);
		Joueur J6 = new Joueur("William","Grant",106);
		Joueur J7 = new Joueur("Jessy","Stuart",244);
		Joueur J8 = new Joueur("Scott","Collins",24);
		
		Joueur tabJoueur[] = {J1,J2,J3,J4,J5,J6,J7,J8};
		Table T1 = new Table(tabJoueur);
		Combinaison Co1 = new Combinaison(J1,T1);
		Combinaison Co2 = new Combinaison(J2,T1);
		Combinaison Co3 = new Combinaison(J3,T1);
		Combinaison Co4 = new Combinaison(J4,T1);
		Combinaison Co5 = new Combinaison(J5,T1);
		Combinaison Co6 = new Combinaison(J6,T1);
		Combinaison Co7 = new Combinaison(J7,T1);
		Combinaison Co8 = new Combinaison(J8,T1);
		T1.distribution(8);
		for(int i = 0; i < 16; i++){
			System.out.println(T1.tabcarte[i]);
			if(i%2 == 1){
				System.out.println("-------------------------------");
			}
		}
		for(int i = 16; i < 21; i++){
			System.out.println(T1.tabcarte[i]);
		}
		
		System.out.println("-------------------------------");
		System.out.print(Co1.combo() + " | ");
		System.out.println(Co1);
		System.out.print(Co2.combo() + " | ");
		System.out.println(Co2);
		System.out.print(Co3.combo() + " | ");
		System.out.println(Co3);
		System.out.print(Co4.combo() + " | ");
		System.out.println(Co4);
		System.out.print(Co5.combo() + " | ");
		System.out.println(Co5);
		System.out.print(Co6.combo() + " | ");
		System.out.println(Co6);
		System.out.print(Co7.combo() + " | ");
		System.out.println(Co7);
		System.out.print(Co8.combo() + " | ");
		System.out.println(Co8);
	}
}