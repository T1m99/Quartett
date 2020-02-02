package spiel;

public class Karte {
	private int Länge;
	private int Gewicht;
	private int Geschwindigkeit;
	private String Name;

	public Karte(String N, int A1, int A2, int A3) {

		Länge = A1;
		Gewicht = A2;
		Geschwindigkeit = A3;
		Name = N;

	}
	// set methoden um auf die Attribute zuzugreifen

	int getAttribut1() {
		return Länge;
	}

	int getAttribut2() {
		return Gewicht;
	}

	int getAttribut3() {
		return Geschwindigkeit;
	}

	String getName() {
		return Name;

	}

}
