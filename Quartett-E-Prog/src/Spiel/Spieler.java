package Spiel;

import java.util.Random;

public class Spieler {
	private boolean letzteRundeGewonnen = false;
	private int letzteWahl;
	private Karte[] DeckB;
	private int Auswahl;
	public Spieler(String Name, Karte[] KartenSet) {
		
		this.DeckB=KartenSet;
	}
	
	public boolean getStatus() {
		
		return letzteRundeGewonnen;
	}
	
	public void changeStatus() {
		
		if(letzteRundeGewonnen == true) {
			letzteRundeGewonnen = false;			
		} else {
			letzteRundeGewonnen = true;
		}
	}
	
	public int selectWert(Karte oben) {
		Random random = new Random();	
		int wert = random.nextInt(3);
		int x = 0;
 			switch(wert) {
			case 1 : 
				x = oben.getAttribut1();
				Auswahl =1;
				break;
			case 2 :
				x = oben.getAttribut2();
				Auswahl=2;
				break;
			case 3: 
				x = oben.getAttribut3();
				Auswahl=3;
				break;
				}
 			letzteWahl=wert;
 			return x;
		
	}
	
	public int getLetzteWahl() {
		return letzteWahl;		
	}
	
	public Karte getAktuelleKarte(int rundenZahl) {
		return DeckB[rundenZahl];
		
	}
	
	public void setAktuelleKarte(Karte Set, int Nummer) {
		DeckB[Nummer] = Set;
	}
	
	public int getPassenderWert(int Rundenzahl, int Auswahl) {
		int x = 0;
		switch(Auswahl) {
		case 1 : 
			x = getAktuelleKarte(Rundenzahl).getAttribut1();
			break;
		case 2 :
			x = getAktuelleKarte(Rundenzahl).getAttribut2();
			break;
		case 3: 
			x = getAktuelleKarte(Rundenzahl).getAttribut3();
			break;
			}
		return x;
	}
	public int getAuswahl() {
		
		return Auswahl;
	}
}
