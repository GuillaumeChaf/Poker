package Poker;
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
		if(this.conversion(0,this.combo()) == 8){
			return "quint flush au/à la " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo())));
		}
		else if(this.conversion(0,this.combo()) == 7){
			return "carré aux " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo()))) + " avec un/une " + this.joueur.C1.getVal((this.conversion(3,this.combo())*10) + (this.conversion(4,this.combo())));
		}
		else if(this.conversion(0,this.combo())  == 6){
			return "full aux " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo()))) + " par les " + this.joueur.C1.getVal((this.conversion(3,this.combo())*10) + (this.conversion(4,this.combo())));
		}
		else if(this.conversion(0,this.combo())  == 5){
			return "couleur au/à la " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo())));
		}
		else if(this.conversion(0,this.combo()) == 4){
			return "quinte au/à la " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo())));
		}
		else if(this.conversion(0,this.combo()) == 3){
			return "brelan de " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo()))) + " avec un/une " + this.joueur.C1.getVal((this.conversion(3,this.combo())*10) + (this.conversion(4,this.combo())))
			+ " et un/une " + this.joueur.C1.getVal((this.conversion(5,this.combo())*10) + (this.conversion(6,this.combo())));
		}
		else if(this.conversion(0,this.combo()) == 2){
			return "double paire de " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo()))) + " et de " + this.joueur.C1.getVal((this.conversion(3,this.combo())*10) + (this.conversion(4,this.combo())))
			 + " avec un/une " + this.joueur.C1.getVal((this.conversion(5,this.combo())*10) + (this.conversion(6,this.combo())));
		}
		else if(this.conversion(0,this.combo()) == 1 && this.combo() > 1000000){
			return "paire de " + this.joueur.C1.getVal((this.conversion(1,this.combo())*10) + (this.conversion(2,this.combo())));
		}
		else{
			if (this.combo()>100000){
			return "carte haute au/à la " + this.joueur.C1.getVal((this.conversion(0,this.combo())*10) + (this.conversion(1,this.combo())));
		}
			else{
				return "carte haute au/à la " + this.joueur.C1.getVal(this.conversion(0,this.combo()));
			}
		}
	}
	
	//nous donne une fréquence des symboles qu'il a dans un tableau pratique pour evaluer si il y a couleur.
	
	public int[] [] tabcarte(){
		
		int tabcarte[][]= new int[4][13];
		
		for (int i = 0; i < 4; i++){
			for (int j = 0 ; j < 13 ; j++){
				tabcarte[i][j] = 0;
			}
		}
			tabcarte[this.joueur.C1.numsymb][this.joueur.C1.numval] = tabcarte[this.joueur.C1.numsymb][this.joueur.C1.numval] +1;
			tabcarte[this.joueur.C2.numsymb][this.joueur.C2.numval] = tabcarte[this.joueur.C2.numsymb][this.joueur.C2.numval] +1;
			tabcarte[this.table.tabcarte[16].numsymb][this.table.tabcarte[16].numval] = tabcarte[this.table.tabcarte[16].numsymb][this.table.tabcarte[16].numval] +1;
			tabcarte[this.table.tabcarte[17].numsymb][this.table.tabcarte[17].numval] = tabcarte[this.table.tabcarte[17].numsymb][this.table.tabcarte[17].numval] +1;
			tabcarte[this.table.tabcarte[18].numsymb][this.table.tabcarte[18].numval] = tabcarte[this.table.tabcarte[18].numsymb][this.table.tabcarte[18].numval] +1;
			tabcarte[this.table.tabcarte[19].numsymb][this.table.tabcarte[19].numval] = tabcarte[this.table.tabcarte[19].numsymb][this.table.tabcarte[19].numval] +1;
			tabcarte[this.table.tabcarte[20].numsymb][this.table.tabcarte[20].numval] = tabcarte[this.table.tabcarte[20].numsymb][this.table.tabcarte[20].numval] +1;
			
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
					resultat = x * 10000 + 8000000.0000;
			}
				//cas particulier pour une suite as,2,3,4,5 car l'as passe de la plus grande à la plus petite valeur
				else if (combinaison == false && this.tabcarte()[i][3] > 0  && this.tabcarte()[i][2] > 0 && this.tabcarte()[i][1] > 0 && this.tabcarte()[i][0] > 0 && this.tabcarte()[i][12] > 0){
					combinaison = true;
					resultat = 8030000.0000;
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
					resultat = 5000000.0000;
					double diviseur = 10000.0000;
					int compt = 12;
					while(diviseur > 0.00001){
						if(this.tabcarte()[k][compt] > 0){
							resultat += compt * diviseur;
							diviseur = diviseur/100;
						}
						compt--;
					}
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
					resultat = n * 10000 + 4000000.0000;
				}
				n--;
			}
			if (combinaison == false && tabsuite[3] > 0  && tabsuite[2] > 0 && tabsuite[1] > 0 && tabsuite[0] > 0 && tabsuite[12] > 0){
				combinaison = true;
				resultat = 4030000.0000;
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
	
	public int paire(){//detecteur du nombre de paire
		
		int resultat = 0;
		for (int i = 0 ; i < 7; i++){
			if (classementval()[1][i] == 2){
				resultat++;
			}
		}
		return resultat;
	}
	
public boolean brelan(){//detecteur de brelan
		
		boolean resultat = false;
		for (int i = 0 ; i < 7; i++){
			if (classementval()[1][i] == 3){
				resultat = true;
			}
		}
		return resultat;
	}

public boolean carre(){//detecteur de carré
	
	boolean resultat = false;
	for (int i = 0 ; i < 7; i++){
		if (classementval()[1][i] == 4){
			resultat = true;
		}
	}
	return resultat;
}
	
	//A partir des 7 cartes du joueur, la methode detecte toutes les combinaisons possible
	//renvoit un code chiffré de 11 caractères qui correspond exactement à la combinaison possédé;
	public double combo(){
		
	double resultat;
	int compteur = 0;
	
		//code chiffré deja créé de la quint flush
	if(this.conversion(0,this.suitecouleur()) == 8){
		resultat = this.suitecouleur();
	}
		//code chiffré du carré
	else if(this.carre() == true){
		resultat = 7000000.0000;
		int nbseule = 0;
		while (compteur < 4){
			if(this.classementval()[1][compteur] == 4){
				resultat += this.classementval()[0][compteur] * 10000;
			}
			else if(nbseule == 0){
				resultat += this.classementval()[0][compteur] * 100;
				nbseule++;
			}
			compteur++;
		}
	}
	
	else if(this.brelan() == true && this.paire() > 0){//code chiffré du full
		resultat = 6000000.0000;
		while (compteur < 5){
			if(this.classementval()[1][compteur] == 3){
				resultat = resultat + classementval()[0][compteur] * 10000;
			}
			if(this.classementval()[1][compteur] == 2){
					resultat = resultat + classementval()[0][compteur] * 100;
			}
		compteur++;
		}
	}
	
	else if(this.conversion(0,this.suitecouleur()) == 5){
		resultat = suitecouleur();
	}

	else if(this.conversion(0,this.suitecouleur()) == 4){
		resultat = suitecouleur();
	}

	else if(this.brelan() == true){
		resultat = 3000000.0000;
		boolean brelan = false;
			if(this.classementval()[1][0] == 3){
				resultat = resultat + this.classementval()[0][0]*10000;
				brelan = true;
			}
			else{
				resultat = resultat + this.classementval()[0][0]*100;
			}

			if(this.classementval()[1][1] == 3 && brelan == false){
				resultat = resultat + this.classementval()[0][1]*10000;
			}
			else if(brelan == true){
				resultat = resultat + this.classementval()[0][1]*100;
			}
			else{
				resultat = resultat + this.classementval()[0][1];
			}
			
			if(this.classementval()[1][2] == 3 && brelan == false){
				resultat = resultat + this.classementval()[0][2]*10000;
				brelan = true;
			}	
			else if(brelan == true){
				resultat = resultat + this.classementval()[0][2];
			}
			
			if(this.classementval()[1][3] == 3 && brelan == false){
				resultat = resultat + this.classementval()[0][3]*10000;
				brelan = true;
			}
			if(brelan == false){
				resultat = resultat + this.classementval()[0][4]*10000;
			}
		}
		//pour au moins une paire
	else if(this.paire() > 0){
		
		int nbpaire = 0;
		compteur = 0;
		while (compteur < 6 && nbpaire < 2){
			if(this.classementval()[1][compteur] == 2){
				nbpaire++;
			}
			compteur++;
		}
		if (nbpaire >= 2){
			int nbseule = 0;
			nbpaire = 0;
			compteur = 0;
			resultat = 2000000.0000;
			while(compteur < 5){
				if(this.classementval()[1][compteur] == 2){
					if(nbpaire == 0){
						resultat = resultat + this.classementval()[0][compteur] * 10000;
					}
					else if (nbpaire == 1){
						resultat += this.classementval()[0][compteur] * 100;
					}
					nbpaire++;
				}
				else if(nbseule == 0){
					resultat += this.classementval()[0][compteur];
					nbseule++;
				}
				compteur++;
			}
		}
		else{
			resultat = 1000000.0000;
			int nbseule = 0;
			nbpaire = 0;
			compteur = 0;
			while (nbpaire < 1 || nbseule < 3){
				if(this.classementval()[1][compteur] == 2){
					resultat += this.classementval()[0][compteur] * 10000;
					nbpaire++;
				}
				else{
					if(nbseule == 0){
						resultat += this.classementval()[0][compteur] * 100;
					}
					else if(nbseule == 1){
						resultat += this.classementval()[0][compteur];
					}
					else if(nbseule == 2){
						resultat += this.classementval()[0][compteur] * 0.01;
					}
					nbseule++;
				}
				compteur++;
			}	
		}
		} 
	//carte haute
	else{
			resultat = this.classementval()[0][0] * 10000 + this.classementval()[0][1] * 100 + this.classementval()[0][2] + this.classementval()[0][3] * 0.01 + this.classementval()[0][4] * 0.0001;
		}
	
	resultat  *=  100000; // petite convertion s'impose car on retrouve des nombres qui finissent par 99999999 ou 00000000000001
	resultat = Math.round(resultat);
	resultat /= 100000;
	
	return resultat;
	}
	
	public int conversion(int nb, double str){//renvois le n ième caractere d'un double entré en argument grace a 4 conversion OMG 
		
		String s  = String.valueOf(str);
		char c = s.charAt(nb);
		s = Character.toString(c); 
		int i = Integer.parseInt(s); 
		return (i);	
	}
}
