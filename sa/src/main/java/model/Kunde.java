package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@NamedQuery(name = "SelectKunden", query = "Select k from Kunde k")
@Entity
@Table(name = "KUNDE")
public class Kunde implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3916812801668152725L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Kunden_Nr", nullable = false, unique = true)
	private Integer Kundennr;

	@Column(name = "Anrede")
	Anrede anrede;

	@Size(min = 3, max = 20)
	@Column(name = "Vorname")
	String vorname;

	@Size(min = 3, max = 20)
	@Column(name = "Nachname")
	String nachname;

	@Temporal(TemporalType.DATE)
	@Past
	@Column(name = "Geburtsdatum")
	private Date geburtsdatum;

	@Column(name = "Rolle")
	private Rolle rolle;

	@Column(name = "Username")
	private String username;

	@Column(name = "Passwort")
	private String passwort;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ArtikelDaten> artikelDaten;

	@Size(min = 2, max = 30)
	@Column(name = "Strasse")
	private String strasse;

	@Column(name = "PLZ")
	private String plz;

	@Size(min = 3, max = 30)
	@Column(name = "Ort")
	private String ort;

	@Size(min = 3, max = 30)
	@Column(name = "Email")
	private String email;

	@JoinColumn(name = "FK_ARTIKEL_ID", foreignKey = @ForeignKey(name = "FK_ARTIKEL_ID"))
	private ArtikelDaten daten;

//	@OneToOne(cascade = CascadeType.ALL)
	@Column(name = "Kreditkarte")
	private Kreditkarte kreditkarte;

	public Kunde(Anrede anrede, String vorname, String nachname, Date geburtsdatum, String strasse, String plz,
			String ort, String email, String username, String passwort, Rolle rolle) {
		this.anrede = anrede;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		this.email = email;
		this.username = username;
		this.passwort = passwort;
		this.rolle = rolle;
	}

	public Kunde() {
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Anrede getAnrede() {
		return anrede;
	}

	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}

	public Integer getKundennr() {
		return Kundennr;
	}

	public void setKundennr(Integer Kundennr) {
		this.Kundennr = Kundennr;
	}

	public Rolle getRolle() {
		return rolle;
	}

	public void setRolle(Rolle rolle) {
		this.rolle = rolle;
	}

	public ArtikelDaten getDaten() {
		return daten;
	}

	public void setDaten(ArtikelDaten daten) {
		this.daten = daten;
	}

	public List<ArtikelDaten> getArtikelDaten() {
		return artikelDaten;
	}

	public void setArtikelDaten(List<ArtikelDaten> artikelDaten) {
		this.artikelDaten = artikelDaten;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Kreditkarte getKreditkarte() {
		return kreditkarte;
	}

	public void setKreditkarte(Kreditkarte kreditkarte) {
		this.kreditkarte = kreditkarte;
	}

}
