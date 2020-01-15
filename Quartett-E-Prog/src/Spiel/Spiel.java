package Spiel;

import java.util.Scanner;
import java.util.Random;

public class Spiel {
	private static int PunkteA;
	private static int PunkteB;
	private static int Runden;
	private static int Auswahl;
	private static boolean Anfangen = true;
	private static Karte[] Deck = new Karte[4];// Dimension Anpassen;
	private static Karte[] DeckA = new Karte[Deck.length / 2];
	private static Karte[] DeckB = new Karte[Deck.length / 2];
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

// Karten erstellen.
		Karte Karte1 = new Karte("Karte 1", 1, 3, 7);
		Karte Karte2 = new Karte("Karte 2", 75, 7, 2);
		Karte Karte3 = new Karte("Karte 3", 6, 435, 87);
		Karte Karte4 = new Karte("Karte 4", 6, 2, 8);

		// Karten in Array einfügen
		einfügen(Karte1);
		einfügen(Karte2);
		einfügen(Karte3);
		einfügen(Karte4);

		// Mitspieler erstellen
		Spieler MitSpieler1 = new Spieler("Player1", DeckB);

		// Das eigentliche "Spiel"
		RundenAbfrage();
		austeilen(Deck, MitSpieler1);
		System.out.print("\033[H\033[2J");

		for (int i = 0; i < Runden; i++) {

			RundeSpielen(i, MitSpieler1);
		}

	}

	// Die ausgewählten Attribute der ausgespielten Karten werden verglichen
	// der Punktestand wird angepasst
	// es wird festgelegt wer die nächste Runde beginnt
	private static void vergleichen(int wertSpielerA, int wertSpielerB) {
		if (wertSpielerA < wertSpielerB) {
			PunkteB++;
			Anfangen = false;
			System.out.println("Spieler B gewinnt");
		} else if (wertSpielerA > wertSpielerB) {
			PunkteA++;
			Anfangen = true;
			System.out.println("Spieler A Gewinnt");
		}

	}

// Die Anzahl der zu spielenden runden kann ausgewählt werden
	private static void RundenAbfrage() {
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
		System.out.println("Sie haben sich für " + Runden + " Runden entschieden, viel Spa�. \n");
	}

	// Das KartenSpiel wird auf beide Spieler aufgeteilt
	private static void austeilen(Karte SpielKarten[], Spieler Mitspieler) {
		Karte[] Gemischt = SpielKarten;
		for (int i = 0; i < Deck.length; i++) {

			if (i % 2 == 0) {

				DeckA[i / 2] = Gemischt[i];

			}
			for (int v = 0; i < Deck.length; i++) {

				if (v % 2 != 0) {

					Mitspieler.setAktuelleKarte(Gemischt[v], (v - 1) / 2);

				}
			}

		}
	}

	// Das Array wird gemischt ( wie ein KartenSpiel)
	private static Karte[] arrayMix(Karte[] zahlen) {
		Random r = new Random();
		for (int i = 0; i < zahlen.length; i++) {
			int rand = r.nextInt(zahlen.length);
			Karte tmp = zahlen[rand];
			zahlen[rand] = zahlen[i];
			zahlen[i] = tmp;
		}
		return zahlen;
	}

	// Der Ausgewählte Wert kann abgefragt werden
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
// Attribut Namen einf�gen

	// Der Name des Ausgewählten Attributes kann abgefragt werden
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

	// Der Nutzer kann das zu vergleichen de Attribut in die Console eingeben
	// Der Passender wert wird rausgesucht
	private static int auswählen(Karte oben) {

		karteAnzeigen(oben);
		System.out.println("\n Sie k�nnen einen Wert durch eingeben der Nummer ausw�hlen");
		Auswahl = scanner.nextInt();
		return passenderWert(oben, Auswahl);
	}

	// Die Auswahl wird in der Console ausgegeben
	private static void auswertung(int Nummer, Karte oben) {
		System.out.println("Das ausgew�hlte Attribut war" + passenderName(oben, Auswahl) + "mit einem Wert von"
				+ passenderWert(oben, Auswahl));

	}
// Attribut Namen einf�gen und sch�ner ausgeben.

	private static void karteAnzeigen(Karte aktuell) {
		System.out.println("Ihre Karte:" + aktuell.getName() + "\n");
		System.out.println("Ihre Karte hat folgende Werte:");
		System.out.println("1-A1:" + aktuell.getAttribut1());
		System.out.println("2-A2:" + aktuell.getAttribut2());
		System.out.println("3-A3:" + aktuell.getAttribut3());

	}

	// neue Karten werden in das Hauptdeck eingefügt
	private static void einfügen(Karte neu) {
		int i = 0;
		while (Deck[i] != null) {
			i++;
		}
		Deck[i] = neu;
	}

	// Die Rundenzahl wird gespeichert
	private static void setRundenZahl(int Zahl) {

		Runden = Zahl;
	}

	private static void RundeSpielen(int RundenNummer, Spieler Gegner) {
		if (Anfangen == true) {
			System.out.println("Sie sind an der Reihe");
			// karteAnzeigen(DeckA[RundenNummer]);
			vergleichen(auswählen(DeckA[RundenNummer]), Gegner.getPassenderWert(RundenNummer, Auswahl));
		} else if (Anfangen == false) {
			// karteAnzeigen(DeckA[RundenNummer]);
			Gegner.selectWert(Gegner.getAktuelleKarte(RundenNummer));
			System.out.println("DerComputer hat Attribut: " + Gegner.getAuswahl() + "Mit einem Wert von "
					+ Gegner.getPassenderWert(RundenNummer, Gegner.getAuswahl()) + "ausgew�hlt.");
			vergleichen(Gegner.getPassenderWert(RundenNummer, Gegner.getAuswahl()),
					passenderWert(DeckA[RundenNummer], Gegner.getAuswahl()));
		}

	}

	// Da keine Methode bekannt ist die Console zu leeren
	// müssen wir uns mit leerzeilen behelfen
	private void consoleClear() {
		for (int i = 0; i < 20; i++)
			System.out.println("\n");
	}
}