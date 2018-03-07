package test;

public enum Rolle {
	ADMIN("Administrator"), KUNDE("Kunde");

	private final String label;

	private Rolle(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
