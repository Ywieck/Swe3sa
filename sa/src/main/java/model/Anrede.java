package model;

public enum Anrede {
	HERR("Herr"), FRAU("Frau"), FIRMA("Firma");
	private final String label;

	// privater Konstruktor
	private Anrede(String label) {
		this.label = label;
	}
	// hier: Getter fuer label

	public String getLabel() {
		return label;
	}
	
}