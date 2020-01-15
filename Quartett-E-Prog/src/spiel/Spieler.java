package spiel;

import java.util.Random;

public class Spieler {
	private int letzteWahl;
	private Karte[] DeckB;
	private int Auswahl;

	public Spieler(String Name, Karte[] KartenSet) {

		this.DeckB = KartenSet;
	}

	// der CPU wählt durch eine Zufallszahl das zu spielende Attribut aus
	public int selectWert(Karte oben) {
		Random random = new Random();
		int wert = random.nextInt(3);
		int x = 0;
		switch (wert) {
		case 1:
			x = oben.getAttribut1();
			Auswahl = 1;
			break;
		case 2:
			x = oben.getAttribut2();
			Auswahl = 2;
			break;
		case 3:
			x = oben.getAttribut3();
			Auswahl = 3;
			break;
		}
		letzteWahl = wert;
		return x;

	}

	// getter und setter für Attribute

	public int getLetzteWahl() {
		return letzteWahl;
	}

	public Karte getAktuelleKarte(int rundenZahl) {
		return DeckB[rundenZahl];

	}

	public void setAktuelleKarte(Karte Set, int Nummer) {
		DeckB[Nummer] = Set;
	}

	// der Wert des zu Spielenden Attributes wird zurückgegeben
	public int getPassenderWert(int Rundenzahl, int Auswahl) {
		int x = 0;
		switch (Auswahl) {
		case 1:
			x = getAktuelleKarte(Rundenzahl).getAttribut1();
			break;
		case 2:
			x = getAktuelleKarte(Rundenzahl).getAttribut2();
			break;
		case 3:
			x = getAktuelleKarte(Rundenzahl).getAttribut3();
			break;
		}
		return x;
	}

	// die Nummer des durch den CPU ausgewhählten Attributes wird zurückgegeben
	public int getAuswahl() {

		return Auswahl;
	}
}
