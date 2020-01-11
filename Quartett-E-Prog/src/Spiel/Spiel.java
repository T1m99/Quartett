package Spiel;
import java.util.Scanner;
import java.util.Random;

public class Spiel {
	private static int PunkteA;
	private static int PunkteB;
	private static int Runden;
	private static int Auswahl;
	private static Karte[] Deck = new Karte[7];// Dimension Anpassen;
	private static Karte[] DeckA = new Karte[Deck.length/2];// Dimension Anpassen;
	private static Karte[] DeckB = new Karte[Deck.length/2];// Dimension Anpassen;
	static Scanner scanner = new Scanner (System.in);
	
	public static void main(String[] args) {
		
	// Karten können als Objekte erzeugt werden.	
	//	Karte Karte1 = new Karte("Name",1,3,7);
		
	RundenAbfrage();
	austeilen(Deck);
	
	for(int i= 0; i<Runden / 2; i++) {
	vergleichen(auswählen(DeckA[i]), passenderWert(DeckB[i], Auswahl));
	auswertung(Auswahl, DeckA[i]);
	auswertung(Auswahl, DeckB[i]);
	i++;
	vergleichen(CPU(DeckB[i]), passenderWert(DeckA[i], Auswahl));
	auswertung(Auswahl, DeckA[i]);
	auswertung(Auswahl, DeckB[i]);
		}	
	System.out.println("Sie haben"+ PunkteA +"Punkte, der Computer hat"+ PunkteB +"Punkte.");
	}
	
	
	
	private static void vergleichen(int wertSpielerA, int wertSpielerB)
	{if(wertSpielerA<wertSpielerB)
			{PunkteB ++;}
		else if(wertSpielerA>wertSpielerB)
			{PunkteA++;}
	
	}
	
	private static void RundenAbfrage()
	{System.out.println("Wollen Sie 2,4 oder 6 Runden Spielen. Bitte die Entsprechende Zahl in die Konsole Eingeben.");
	int runde = scanner.nextInt();
	switch(runde) {
		case 1 : 
			Runden = 2;
			break;
		case 3 :
			Runden = 4;
			break;
		case 5 : 
			Runden = 6;
			break;
			}
	System.out.println("Sie haben sich für" + Runden + "Runden entschieden, viel Spaß.");
		}
	
	private static void austeilen(Karte SpielKarten[]) {
		Karte[] temp = SpielKarten.clone();
		Karte[] Gemischt = arrayMix(temp);
		for(int i=0; i< Deck.length/2; i++)
		{
			if(i%2 == 0) {
				DeckA[i] = SpielKarten[i];
			}
		}
		for(int i=Deck.length/2; i<= Deck.length; i++)
		{
			if(i%2 == 0) {
				DeckB[i] = SpielKarten[i];
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
		switch(Nummer) {
		case 1 : 
			x = oben.getAttribut1();
			break;
		case 2 :
			x = oben.getAttribut2();
			break;
		case 3: 
			x = oben.getAttribut3();
			break;
			}
		return x;
	}
	
	private static String passenderName(Karte oben, int Nummer) {
		String x ="";
		switch(Nummer) {
		case 1 : 
			x = "Attribut 1";
			break;
		case 2 :
			x = "Attribut 2";
			break;
		case 3: 
			x = "Attribut 3";
			break;
			}
		return x;
	}
	
	
	private static int auswählen(Karte oben) {
		
		karteAnzeigen(oben);
		System.out.println("Sie können einen Wert durch eingeben der Nummer auswählen");
		Auswahl = scanner.nextInt();
		return passenderWert(oben, Auswahl);
	}
	
	private static int CPU (Karte oben) {
			Random random = new Random();	
		int wert = random.nextInt(3);
		int x = 0;
 			switch(wert) {
			case 1 : 
				x = oben.getAttribut1();
				break;
			case 2 :
				x = oben.getAttribut2();
				break;
			case 3: 
				x = oben.getAttribut3();
				break;
				}
			return x;
	
	}
	
	private static void auswertung(int Nummer, Karte oben) {
		System.out.println("Das ausgew�hlte Attribut war" + passenderName(oben, Auswahl) + "mit einem Wert von" + passenderWert(oben, Auswahl));
		
	}
	
	
	
	private static void karteAnzeigen(Karte aktuell) {
		System.out.println("Ihre Karte:"+ aktuell.getName());
		System.out.println("Ihre Karte hat folgende Werte:");
		System.out.println("1-A1:"+aktuell.getAttribut1());
		System.out.println("2-A2:"+aktuell.getAttribut2());
		System.out.println("3-A3:"+aktuell.getAttribut3());

	}
}

