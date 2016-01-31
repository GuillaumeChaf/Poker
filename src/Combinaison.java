// la combinaison composé d'un joueur et d'une table les 2 en sous classe, il permet de voir les 7 cartes qu'a le joueur à la fin 
//(2 perso + 5 sur tables) ce qui permet de voir les combinaisons qu'il aura a la fin(paire, full, couleur) et d'évaluer si il gagne ou non.
public class Combinaison {
	
	Joueur joueur;
	Table table;
	
	public Combinaison(Joueur joueur, Table table){
		
	this.joueur = joueur;
	this.table = table;
	}
	
	public Combinaison(Combinaison C1){
		
		this(C1.joueur,C1.table);
	}
	
	public String toString(){
		return "x";
	}
	
	//nous donne une fréquence des symboles qu'il a dans un tableau pratique pour evaluer si il y a couleur.
	
	public int[] [] tabcarte(){
		
		int tabcarte[][]= new int[4][13];
			tabcarte[this.joueur.C1.numsymb][this.joueur.C1.numval] = tabcarte[this.joueur.C1.numsymb][this.joueur.C1.numval] +1;
			tabcarte[this.joueur.C2.numsymb][this.joueur.C2.numval] = tabcarte[this.joueur.C2.numsymb][this.joueur.C2.numval] +1;
			tabcarte[this.table.C1.numsymb][this.table.C1.numval] = tabcarte[this.table.C1.numsymb][this.table.C1.numval] +1;
			tabcarte[this.table.C2.numsymb][this.table.C2.numval] = tabcarte[this.table.C2.numsymb][this.table.C2.numval] +1;
			tabcarte[this.table.C3.numsymb][this.table.C3.numval] = tabcarte[this.table.C3.numsymb][this.table.C3.numval] +1;
			tabcarte[this.table.C4.numsymb][this.table.C4.numval] = tabcarte[this.table.C4.numsymb][this.table.C4.numval] +1;
			tabcarte[this.table.C5.numsymb][this.table.C5.numval] = tabcarte[this.table.C5.numsymb][this.table.C5.numval] +1;
			
			return tabcarte;
		
	}
	
	// détecteur de suite couleur et quinte flush car ce sont les combinaisons les plus  dur a évaluer pour ca on va s'aider du tableau ci dessus.
	//si le resultat ce finit par 0.8 c'est une quint flush ; 0.5 c'est une couleur et 0.4 c'est une suite.
	//le premier chiffre signifie si c'est une suite ou une quint flush la valeur de la carte la plus grande de la suite, si c'est une couleur il signifie la couleur (voir le tableau)
	public double suitecouleur(){
	
		boolean combinaison = false;
		double resultat = 0;
		int i = 0;
		while(i < 4){//détecteur de quinte flush
			int x = 12;// case As en colonne
			while(x > 3 && combinaison == false){
				if (this.tabcarte()[i][x] == 1  && this.tabcarte()[i][x-1] == 1 && this.tabcarte()[i][x-2] == 1 && this.tabcarte()[i][x-3] == 1 && this.tabcarte()[i][x-4] == 1){
					combinaison = true;
					resultat = x + 0.8;
			}
				//cas particulier pour une suite as,2,3,4,5 car l'as passe de la plus grande à la plus petite valeur
				else if (combinaison == false && this.tabcarte()[i][3] > 0  && this.tabcarte()[i][2] > 0 && this.tabcarte()[i][1] > 0 && this.tabcarte()[i][0] > 0 && this.tabcarte()[i][12] > 0){
					combinaison = true;
					resultat = 3.8;
				}
			else{
				x--;
				}
			}
			i++;
		}
			
			int k = 0;
			while( k < 4 && combinaison == false){ //détection de couleur
				int somme = 0;
				for (int j = 0; j <= 12; j++){
							somme = somme + this.tabcarte()[k][j];
			}
				if(somme > 4){
					combinaison = true;
					resultat = k + 0.5;
				}
				k++;
		}
			//detecteur de suite
			int tabsuite[] = new int[13];
			for (int m = 0; m <= 12; m++){
				tabsuite[m] = this.tabcarte()[0][m] + this.tabcarte()[1][m] + this.tabcarte()[2][m] + this.tabcarte()[3][m];
			}
			int n = 12;
			while(combinaison == false && n > 3){
				if(tabsuite[n] == 1 && tabsuite[n-1] == 1 && tabsuite[n-2] == 1 && tabsuite[n-3] == 1 && tabsuite[n-4] == 1){
					combinaison = true;
					resultat = n + 0.4;
				}
				n--;
			}
			if (combinaison == false && tabsuite[3] > 0  && tabsuite[2] > 0 && tabsuite[1] > 0 && tabsuite[0] > 0 && tabsuite[12] > 0){
				combinaison = true;
				resultat = 3.4;
			}
			return resultat;
	}
	
	// Un tableau qui renvoit dans la case i le nombre de fois que cette valeur de carte est presente dans la combinaison.
	public int[] nombrevaleur(){
	
		int compteur1 = 0;
		int compteur2 = 0;
		int tab[] = new int[13];
		while (compteur1 < 13){
			compteur2 = 0;
			while (compteur2 < 4){
				if(tabcarte()[compteur2][compteur1] > 0){
				tab[compteur1] = tab[compteur1] + 1;
			}
			compteur2++;	
		}
			compteur1++;
	}
		return tab;
}
	
	
	
	//tableau a 2 lignes et 7 colonnes la ligne 1 va classer les differentes valeurs des 7 cartes du joueur dans l'ordre décroissant de leur valeur et la 2eme le nombre de chacune d'elle.
	//Par exemple un full au roi par les 8 avec un 4 et un 6 en complément donnent:
	// 11 (roi) | 6(les 8) | 4(le 6) | 2(le 4) |
	//   3      |    2     |     1   |    1    |
	public int[] [] classementval(){
		int classement[] [] = new int [2] [7];
		int comptcase = 0;
		for (int i = 12; i >=0; i--){
			if(this.nombrevaleur()[i] > 0){
				classement[0] [comptcase] = i;
				classement[1] [comptcase] = this.nombrevaleur()[i];
				comptcase++;
			}
		}
		return classement;
	}
	
	public boolean paire(){
		
		boolean resultat = false;
		for (int i = 0 ; i < 7; i++){
			if (classementval()[1][i] == 2){
				resultat = true;
			}
		}
		return resultat;
	}
	
public boolean brelan(){
		
		boolean resultat = false;
		for (int i = 0 ; i < 7; i++){
			if (classementval()[1][i] == 3){
				resultat = true;
			}
		}
		return resultat;
	}
	
	//A partir des 7 cartes du joueur, la methode detecte les combinaison tels que paire double paire brelan full
	//revoit un code chiffré de 1 chiffre qui correspond à une combinaison
	//public int combo(){
		
	
			
//	} 
}
