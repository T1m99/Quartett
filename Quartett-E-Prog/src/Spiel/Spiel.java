package Spiel;

import java.util.Scanner;
import java.util.Random;

public class Spiel {
	private static int PunkteA;
	private static int PunkteB;
	private static int Runden;
	private static int Auswahl;
	private static boolean Anfangen = true;
	private static Karte[] Deck = new Karte[12];// Dimension Anpassen;
	private static Karte[] DeckA = new Karte[Deck.length / 2];
	private static Karte[] DeckB = new Karte[Deck.length / 2];
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
		
		for(int i = 0; i< Runden; i++) {
			
			RundeSpielen(i, MitSpieler1);
		}
		
		

	} 

	private static void vergleichen(int wertSpielerA, int wertSpielerB) {
		if (wertSpielerA < wertSpielerB) {
			PunkteB++;
			Anfangen= false;
			System.out.println("Spieler B gewinnt");
		} else if (wertSpielerA > wertSpielerB) {
			PunkteA++;
			Anfangen = true;
			System.out.println("Spieler A Gewinnt");
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
		Karte[] Gemischt = SpielKarten;
		for (int i = 0; i < Deck.length; i++) {
			if (i % 2 == 0) {
				if (i<2) {DeckA[i] = Gemischt[i];
				}
			}			//Logik Fehler drinnen
		}
		for (int i =0 ; i <Deck.length; i++) {
			if (i % 2 != 0) {
				Mitspieler.setAktuelleKarte(Gemischt[i], i/2);
			}
		}

	}

	private static Karte[] arrayMix(Karte[] zahlen) {
		Random r = new Random();
		for (int i = 0; i < zahlen.length; i++) {
			int	rand = r.nextInt(zahlen.length);
			Karte tmp = zahlen[rand ];
			zahlen[rand] = zahlen[i];
			zahlen[i] = tmp;
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
	
	private static void RundeSpielen(int RundenNummer, Spieler Gegner) {
		if(Anfangen == true) {
			System.out.println("Sie sind an der Reihe");
			//karteAnzeigen(DeckA[RundenNummer]);
			vergleichen(auswÃ¤hlen(DeckA[RundenNummer]),Gegner.getPassenderWert(RundenNummer, Auswahl));			
		} else if(Anfangen == false) {
			//karteAnzeigen(DeckA[RundenNummer]);
			Gegner.selectWert(Gegner.getAktuelleKarte(RundenNummer));
			System.out.println("DerComputer hat Attribut: " + Gegner.getAuswahl() + "Mit einem Wert von " + Gegner.getPassenderWert(RundenNummer, Gegner.getAuswahl()) + "ausgewählt.");
			vergleichen(Gegner.getPassenderWert(RundenNummer, Gegner.getAuswahl()), passenderWert(DeckA[RundenNummer], Gegner.getAuswahl()));
			}
		
	}
}