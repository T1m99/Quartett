package spiel;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Spiel {
	private static int PunkteA;
	private static int PunkteB;
	private static int Runden;
	private static int Auswahl; //Nummer vom ausgewählten Attribut
	private static boolean Anfangen = true; // true = Spieler begint
	private static Karte[] Deck = new Karte[10];// Dimension Anpassen;
	private static Karte[] DeckA = new Karte[Deck.length / 2];
	private static Karte[] DeckB = new Karte[Deck.length / 2];
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

// Karten erstellen und hinzufügen in das Hauptarray
		Deck[0] = new Karte("Supersaurus", 30, 75, 17);
		Deck[1] = new Karte("Argentinosaurus", 40, 100, 12);
		Deck[2] = new Karte("Seismosaurus", 37, 80, 14);
		Deck[3] = new Karte("Ultrasaurus", 27, 60, 24);
		Deck[4] = new Karte("Diploducos", 19, 110, 5);
		Deck[5] = new Karte("Brachlosaurus", 25, 85, 21);
		Deck[6] = new Karte("Grogodaus", 12, 51, 29);
		Deck[7] = new Karte("Tiranosaurus", 40, 90, 16);
		Deck[8] = new Karte("Sinorus", 11, 32, 38);
		Deck[9] = new Karte("Triceratops", 8, 5, 33);
		
		// Mitspieler erstellen
		Spieler MitSpieler1 = new Spieler("Computer", DeckB);

		// Das eigentliche "Spiel"
		RundenAbfrage();
		austeilen(Deck, MitSpieler1);
		// consoleClear();

		for (int i = 0; i < (Runden ); i++) {

			RundeSpielen(i, MitSpieler1);
		}

		GewinnerRausposaunen(MitSpieler1);

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
		System.out.println("Sie haben sich für " + Runden + " Runden entschieden, viel Spaß. \n");
	}

	// Das KartenSpiel wird auf beide Spieler aufgeteilt
	private static void austeilen(Karte SpielKarten[], Spieler Mitspieler) {
		// Umwandeln in arryList um shuffle Methode nutzen zu können. <Karten> nicht
		// zwingend notwenig
		ArrayList<Karte> arrayList = new ArrayList<Karte>(Arrays.asList(SpielKarten));
		Collections.shuffle(arrayList);

		for (int i = 0; i < Deck.length; i++) {

			if (i % 2 == 0) {

				DeckA[i / 2] = arrayList.get(0);

			}
		}
		for (int v = 0; v < Deck.length; v++) {

			if (v % 2 != 0) {

				Mitspieler.setAktuelleKarte(arrayList.get(v), (v / 2));

			}
		}

	}

	// Das Array wird gemischt ( wie ein KartenSpiel)

	// Die ausgewählten Attribute der ausgespielten Karten werden verglichen
	// der Punktestand wird angepasst
	// es wird festgelegt wer die nächste Runde beginnt
	private static void vergleichen(int wertSpielerA, int wertSpielerB) {
		if (wertSpielerA < wertSpielerB) {
			PunkteB++;
			Anfangen = false;
			System.out.println("Der Computer hat die Runde Gewonnen");
		} else if (wertSpielerA > wertSpielerB) {
			PunkteA++;
			Anfangen = true;
			System.out.println("Sie haben die Runde Gewonnen");
		} else
			System.out.println("klassisches Unentschieden");

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
		System.out.println("\n" + "Sie können einen Wert durch eingeben der Nummer auswählen");
		Auswahl = scanner.nextInt();
		return passenderWert(oben, Auswahl);
	}

	// Die Auswahl wird in der Console ausgegeben
	private static void auswertung(int Nummer, Karte oben, Spieler Gegner) {
		if (Anfangen == true) {
			System.out.println("Das ausgewählte Attribut war " + passenderName(oben, Auswahl) + " mit einem Wert von: "
					+ passenderWert(oben, Auswahl) + "\n");
		} else {
			System.out.println("Der Computer hat " + Gegner.getAktuelleKarte(Runden).getName() + " mit einem Wert von: "
					+ Gegner.getPassenderWert(Runden, Gegner.getAuswahl()) + "ausgewählt.\n");
		}

	}
// Attribut Namen einf�gen und sch�ner ausgeben.

	private static void karteAnzeigen(Karte aktuell) {
		System.out.println("Ihre Karte:" + aktuell.getName() + "\n");
		System.out.println("Ihre Karte hat folgende Werte:");
		System.out.println("1-A1:" + aktuell.getAttribut1());
		System.out.println("2-A2:" + aktuell.getAttribut2());
		System.out.println("3-A3:" + aktuell.getAttribut3());

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
			auswertung(RundenNummer, DeckA[RundenNummer], Gegner);
		} else if (Anfangen == false) {
			// karteAnzeigen(DeckA[RundenNummer]);
			Gegner.selectWert(Gegner.getAktuelleKarte(RundenNummer));
			System.out.println("DerComputer hat Attribut: " + Gegner.getAuswahl() + "Mit einem Wert von "
					+ Gegner.getPassenderWert(RundenNummer, Gegner.getAuswahl()) + "ausgewählt.");
			vergleichen(Gegner.getPassenderWert(RundenNummer, Gegner.getAuswahl()),
					passenderWert(DeckA[RundenNummer], Gegner.getAuswahl()));
		}

	}

	// Da keine Methode bekannt ist die Console zu leeren
	// müssen wir uns mit leerzeilen behelfen
	private static void consoleClear() {
		for (int i = 0; i < 20; i++)
			System.out.println("\n");
	}

	private static String GewinnerRausposaunen(Spieler Gegner) {
		String Auswertung = null;
		if (PunkteA > PunkteB) {
			Auswertung = "Sie gewinnen mit " + PunkteA + ": " + PunkteB + ". Herzlichen Glückwunsch.";
		} else if (PunkteB > PunkteA) {
			Auswertung = Gegner.getName() + "gewinnt mit " + PunkteA + ": " + PunkteB + ".";

		} else {
			Auswertung = "Das Spiel endet Unentschieden mit" + PunkteA + ": " + PunkteB + ".";
		}

		return Auswertung;

	}
}