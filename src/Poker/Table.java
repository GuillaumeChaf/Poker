package Poker;
import java.util.Random;

// la table à la fin d'une manche composé des 5 cartes en sous classes 
public class Table {
	
	Random rd = new Random();
	Carte[] tabcarte = new Carte[21];
	Joueur[] tabjoueur = new Joueur[8];
	int nbjoueur;
	public Table(Joueur[] tabjoueur){
			
	for(int i = 0; i < 21; i++){
		
		this.tabcarte[i] = null;
	}
	
	for (int i = 0; i < tabjoueur.length; i++){
		
		this.tabjoueur[i] = tabjoueur[i]; 
		}
	
	for (int i = 0; i < tabjoueur.length ; i++){
		
		if(tabjoueur[i] != null){
			this.nbjoueur++;
			}
		}
	}
	
	
	public String toString(){
		
		return ("Flop : " + this.tabcarte[16] + ", " + this.tabcarte[17] + ", " + this.tabcarte[18]  + "\n" + "Turn : " + this.tabcarte[19] + "\n" + "River : "  + this.tabcarte[20]);
	}
	
	public Carte randcarte(){//créé une carte aléatoire
		Carte C = new Carte(rd.nextInt(4),rd.nextInt(13));
		return (C);
	}	
	
	public void distribution(int nbjoueur){//chaque joueur recoit 2 cartes aleatoires equiprobable, les 5 cartes de la table sont egalement distribuées, une carte n'est idstribué qu'une seule fois
		
		int compt = 0;
		Carte allcarte[] = new Carte[52];
		
		for (int i = 0; i < 4; i++){
			for(int j = 0; j < 13; j++){
			Carte C = new Carte(i,j);
				allcarte[compt] =  C;
				compt++;
			}
		}
		compt = 0;
		while (compt < nbjoueur*2){
			int alea = rd.nextInt(52-compt);
			this.tabcarte[compt] = allcarte[alea];
			allcarte[alea] = allcarte[51-compt];
			compt++;
		}
		compt = 16;
		while(compt < 21){
			int alea = rd.nextInt(52-compt);
			this.tabcarte[compt] = allcarte[alea];
			allcarte[alea] = allcarte[51-compt];
			compt++;
		}
		compt = 0;
		while (compt < nbjoueur*2){
			this.tabjoueur[compt/2].C1 = this.tabcarte[compt];
			this.tabjoueur[compt/2].C2 = this.tabcarte[compt+1];
			compt += +2;
		}
	}
	
	
}