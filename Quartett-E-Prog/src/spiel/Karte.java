package spiel;

public class Karte {
	private int Attribut1;
	private int Attribut2;
	private int Attribut3;
	private String Name;

	public Karte(String N, int A1, int A2, int A3) {

		Attribut1 = A1;
		Attribut2 = A2;
		Attribut3 = A3;
		Name = N;

	}
	// set methoden um auf die Attribute zuzugreifen

	int getAttribut1() {
		return Attribut1;
	}

	int getAttribut2() {
		return Attribut2;
	}

	int getAttribut3() {
		return Attribut3;
	}

	String getName() {
		return Name;

	}

}
