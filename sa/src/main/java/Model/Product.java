package Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

public class Product {

	@Size(min = 3, max = 30)
	private String beschreibung;

	@Size(min = 3, max = 30)
	private String name;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer preis;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Product() {
		super();
	}

	public Product(String name, Integer preis, String beschreibung) {
		this();
		this.name = name;
		this.preis = preis;
		this.beschreibung = beschreibung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPreis() {
		return preis;
	}

	public void setPreis(Integer preis) {
		this.preis = preis;
	}
	
	

}
