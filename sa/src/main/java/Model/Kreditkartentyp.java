package Model;

public enum Kreditkartentyp {
	MASTERCARD("Masterkard"), AMERICANEXPRESS("Americanexpress"), VISA("Visa");
	
	private Kreditkartentyp(String kkLabel){
		this.kkLabel = kkLabel;
	}
	public final String kkLabel;
	
	
	public String getKkLabel() {
		return kkLabel;
	}
	
}
