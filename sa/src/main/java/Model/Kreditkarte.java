package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

@NamedQuery(name = "SelectKreditkarte", query = "Select k from Kreditkarte k")
@Entity
@Table(name = "KREDITKARTE")
public class Kreditkarte implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7884095993538530098L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	private String kkTyp;
	private String kkNummer;
	
	@Temporal(TemporalType.DATE)
	@Future
	private Date kkGueltigBis;
	
	private String kkInhaber;
	
	public Kreditkarte() {
	}

	public Kreditkarte(String kkTyp, String kkNummer, Date kkGueltigBis, String kkInhaber) {
		this.kkTyp = kkTyp;
		this.kkNummer = kkNummer;
		this.kkGueltigBis = kkGueltigBis;
		this.kkInhaber = kkInhaber;
	}
	
	
	//Getter und Setter

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKkTyp() {
		return kkTyp;
	}

	public void setKkTyp(String kkTyp) {
		this.kkTyp = kkTyp;
	}

	public String getKkNummer() {
		return kkNummer;
	}

	public void setKkNummer(String kkNummer) {
		this.kkNummer = kkNummer;
	}

	public Date getKkGueltigBis() {
		return kkGueltigBis;
	}

	public void setKkGueltigBis(Date kkGueltigBis) {
		this.kkGueltigBis = kkGueltigBis;
	}

	public String getKkInhaber() {
		return kkInhaber;
	}

	public void setKkInhaber(String kkInhaber) {
		this.kkInhaber = kkInhaber;
	}
}
