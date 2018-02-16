package Model;

public enum Salutation {
	MR("Mister"), MRS("Misses"), Comp("Company");
	public final String label;

	// privater Konstruktor
	private Salutation(String label) {
		this.label = label;
	}

	// hier: Getter fuer label
	public String getLabel() {
		return label;
	}
}
