package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name = "SelectedProduct", query = "Select p from Product p")
@Entity
public class Product {
	private String name;
	private float price;
	private String kategorie;
	private String geschlecht;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public Product() {

	}

	public Product(String name, float price, String kategorie, String geschlecht) {
		this.name = name;
		this.price = price;
		this.kategorie = kategorie;
		this.geschlecht = geschlecht;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

}
