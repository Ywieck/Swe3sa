package test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "SelectArtikel", query = "Select a from ArtikelDaten a")
@Entity
@Table(name="ARTIKEL")
public class ArtikelDaten implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4366862049274421981L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Artikel_ID", nullable = false, unique = true)
	private Integer positionsID;
	
	@Column(name = "Bezeichnung")
	private String bezeichnung;
	
	@Column(name = "Hersteller")
	private String hersteller;
	
	@Column(name = "Preis")
	private double preis;
	
	@Column(name = "Beschreibung")
	private String beschreibung;
	
	@Column(name = "Photo")
	private String photo;
	
	@Column(name = "Anzahl")
	private int anzahl = 1;
	
	private double gesamt;
	private double gesamtprice;
	private int gesamtanzahl;
	private int nummer;


	public ArtikelDaten() {

	}
	
	public ArtikelDaten( String bezeichnung, String hersteller,
			String beschreibung, double preis, String photo) {
		
		this.bezeichnung = bezeichnung;
		this.hersteller = hersteller;
		this.beschreibung = beschreibung;
		this.preis = preis;

		this.photo = photo;
	}

	public Integer getPositionsID() {
		return positionsID;
	}

	public void setPositionsID(Integer positionsID) {
		this.positionsID = positionsID;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public double getGesamt() {
		return gesamt;
	}

	public void setGesamt(double gesamt) {
		this.gesamt = gesamt;
	}

	public double getGesamtprice() {
		return gesamtprice;
	}

	public void setGesamtprice(double gesamtprice) {
		this.gesamtprice = gesamtprice;
	}

	public int getGesamtanzahl() {
		return gesamtanzahl;
	}

	public void setGesamtanzahl(int gesamtanzahl) {
		this.gesamtanzahl = gesamtanzahl;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	
}
