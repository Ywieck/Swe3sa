package Model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import de.hsb.app.kv.controller.Anschrift;
//import de.hsb.app.kv.controller.Kreditkarte;

@NamedQuery(name = "SelectCustomer", query = "Select c from Customer c")
public class Customer {
	private String salutation;

	@Size(min = 3, max = 30)
	private String firstName;
	@Size(min = 3, max = 30)
	private String surname;

	@Past
	@Temporal(TemporalType.DATE)
	private Date birthday;

	// @OneToOne(cascade = CascadeType.ALL)
	// private Kreditkarte kreditkarte;

	@OneToOne(cascade = CascadeType.ALL)
	private Adress adress;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Customer() {
		super();
	}

	public Customer(String salutation, String firstName, String surname, Date birthday,
			/* Kreditkarte kreditkarte, */ Adress adress) {
		this();
		this.salutation = salutation;
		this.firstName = firstName;
		this.surname = surname;
		this.birthday = birthday;
		// this.kreditkarte = kreditkarte;
		this.adress = adress;

	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

//	public Kreditkarte getKreditkarte() {
//		return kreditkarte;
//	}
//
//	public void setKreditkarte(Kreditkarte kreditkarte) {
//		this.kreditkarte = kreditkarte;
//	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

}
