package Spiel;

import java.util.Scanner;
import java.util.Random;

public class Spiel {
	private static int PunkteA;
	private static int PunkteB;
	private static int Runden;
	private static int Auswahl;
	private static Karte[] Deck = new Karte[7];// Dimension Anpassen;
	private static Karte[] DeckA = new Karte[Deck.length / 2];// Dimension Anpassen;
	private static Karte[] DeckB = new Karte[Deck.length / 2];// Dimension Anpassen;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

// Karten kÃ¶nnen als Objekte erzeugt werden.
		 Karte Karte1 = new Karte("Karte 1",1,3,7);
		 Karte Karte2 = new Karte("Karte 2",75,7,2);
		 Karte Karte3 = new Karte("Karte 3",6,435,87);
		 Karte Karte4 = new Karte("Karte 4",6,2,8);    
// Karten in Array einfügen		 
		 einfügen(Karte1);
		 einfügen(Karte2);
		 einfügen(Karte3);
		 einfügen(Karte4);
		 
		Spieler MitSpieler1 = new Spieler("Tim", DeckB);

		RundenAbfrage();
		austeilen(Deck, MitSpieler1);
		karteAnzeigen(DeckB[2]);
		vergleichen(auswÃ¤hlen(DeckB[2]), passenderWert(DeckA[2], 4));
		System.out.println("Sie haben " + PunkteA + " Punkte, der Computer hat " + PunkteB + " Punkte.");

	} 

	private static void vergleichen(int wertSpielerA, int wertSpielerB) {
		if (wertSpielerA < wertSpielerB) {
			PunkteB++;
		} else if (wertSpielerA > wertSpielerB) {
			PunkteA++;
		}

	}
// Runden anzahlen überdenken -> muss gerade Anzahl sein.
	private static void RundenAbfrage() 
	{
		System.out
				.println("Wollen Sie 2,4 oder 6 Runden Spielen. Bitte die Entsprechende Zahl in die Konsole Eingeben.");
		int runde = scanner.nextInt();
		switch (runde) {
		case 2:
			setRundenZahl(2);
			break;
		case 4:
			setRundenZahl(4);
			break;
		case 6:
			setRundenZahl(6);
			break;
		}
		System.out.println("Sie haben sich für " + Runden + " Runden entschieden, viel Spaß. \n");
	}

	private static void austeilen(Karte SpielKarten[], Spieler Mitspieler) {
		Karte[] temp = SpielKarten.clone();
		Karte[] Gemischt = arrayMix(temp);
		for (int i = 0; i < Deck.length / 2; i++) {
			if (i % 2 == 0) {
				DeckA[i] = Gemischt[i];
			}
		}
		for (int i = Deck.length / 2; i <Deck.length; i++) {
			if (i % 2 != 0) {
				Mitspieler.setAktuelleKarte(Gemischt[i], i - (Deck.length/2));
			}
		}

	}

	private static Karte[] arrayMix(Karte[] zahlen) {
		Karte tmp;
		int rand;
		Random r = new Random();
		for (int i = 0; i < zahlen.length; i++) {
			rand = r.nextInt(zahlen.length);
			tmp = zahlen[i];
			zahlen[i] = zahlen[rand];
			zahlen[rand] = tmp;
		}
		return zahlen;
	}

	private static int passenderWert(Karte oben, int Nummer) {
		int x = 0;
		switch (Nummer) {
		case 1:
			x = oben.getAttribut1();
			break;
		case 2:
			x = oben.getAttribut2();
			break;
		case 3:
			x = oben.getAttribut3();
			break;
		}
		return x;
	}
// Attribut Namen einfügen
	private static String passenderName(Karte oben, int Nummer) { 
		String x = "";
		switch (Nummer) {
		case 1:
			x = "Attribut 1";
			break;
		case 2:
			x = "Attribut 2";
			break;
		case 3:
			x = "Attribut 3";
			break;
		}
		return x;
	}

	private static int auswÃ¤hlen(Karte oben) {

		karteAnzeigen(oben);
		System.out.println("\n Sie können einen Wert durch eingeben der Nummer auswählen");
		Auswahl = scanner.nextInt();
		return passenderWert(oben, Auswahl);
	}

	private static void auswertung(int Nummer, Karte oben) {
		System.out.println("Das ausgewählte Attribut war" + passenderName(oben, Auswahl) + "mit einem Wert von"
				+ passenderWert(oben, Auswahl));

	}
// Attribut Namen einfügen und schöner ausgeben.
	private static void karteAnzeigen(Karte aktuell) { 
		System.out.println("Ihre Karte:" + aktuell.getName() + "\n");
		System.out.println("Ihre Karte hat folgende Werte:");
		System.out.println("1-A1:" + aktuell.getAttribut1());
		System.out.println("2-A2:" + aktuell.getAttribut2());
		System.out.println("3-A3:" + aktuell.getAttribut3());

	}

	
	private static void einfügen(Karte neu) {
		int i= 0;
		while(Deck[i]!= null) {
			i++;			
		}
		Deck[i]= neu;
	}
	
	private static void setRundenZahl(int Zahl){
		
		Runden = Zahl;
	}
}