package Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;
import javax.ws.rs.NotSupportedException;

import org.omg.CORBA.SystemException;

import Model.Adress;
import Model.Customer;
import Model.Salutation;

@ManagedBean
@ViewScoped
public class CustomerHandler {

	@PersistenceContext
	private EntityManager em;
	@Resource
	private UserTransaction utx;

	private DataModel<Customer> customer;
	private Customer rememberCustomer = new Customer();
	// private Kreditkarte merkeKreditkarte = new Kreditkarte();
	private Adress rememberAdress = new Adress(null, null, null);

	@PostConstruct
	public void init() {
		try {
			utx.begin();

			customer = new ListDataModel<Customer>();
			customer.setWrappedData(em.createNamedQuery("SelectCustomer").getResultList());
			utx.commit();

		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
	}

	public String newCustomer() {
		rememberCustomer = new Customer();
		return "newCustomer";
	}

	// public String neuKreditkarte() {
	// merkeKunde = kunden.getRowData();
	// merkeKreditkarte = merkeKunde.getKreditkarte();
	//
	// if (merkeKreditkarte == null)
	// merkeKreditkarte = new Kreditkarte();
	//
	// return "kreditKarte";
	// }

	public String saveCustomer() {
		try {
			utx.begin();
			rememberCustomer = em.merge(rememberCustomer);
			em.persist(rememberCustomer);
			customer.setWrappedData(em.createNamedQuery("SelectCustomer").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
		return "index";
	}

	public String deleteCustomer() {
		// -> Transaktionsbeginn
		try {
			utx.begin();
			rememberCustomer = customer.getRowData();
			rememberCustomer = em.merge(rememberCustomer);
			em.remove(rememberCustomer);
			customer.setWrappedData(em.createNamedQuery("SelectCustomer").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
			// -> Transaktionsende
		}
		return "alleKunden";
	}

	public String editCustomer() {
		rememberCustomer = customer.getRowData();
		return "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	}

	// public String kreditkarteSpeichern() {
	// try {
	// merkeKunde.setKreditkarte(merkeKreditkarte);
	// utx.begin();
	// merkeKunde = em.merge(merkeKunde);
	// merkeKreditkarte = em.merge(merkeKreditkarte);
	// em.persist(merkeKunde);
	// em.persist(merkeKreditkarte);
	// kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
	// utx.commit();
	// } catch (SecurityException | IllegalStateException | RollbackException |
	// HeuristicRollbackException
	// | SystemException | NotSupportedException | HeuristicMixedException e) {
	// e.printStackTrace();
	// }
	// return "alleKunden";
	// }

	public String saveAdress() {
		try {
			rememberCustomer.setAdress(rememberAdress);
			utx.begin();
			rememberCustomer = em.merge(rememberCustomer);
			rememberAdress = em.merge(rememberAdress);
			em.persist(rememberCustomer);
			em.persist(rememberAdress);
			customer.setWrappedData(em.createNamedQuery("SelectCustomer").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.RollbackException | javax.transaction.SystemException
				| javax.transaction.NotSupportedException e) {
			e.printStackTrace();
		}
		return "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	}

	public String newAdress() {
		rememberCustomer = customer.getRowData();
		rememberAdress = rememberCustomer.getAdress();

		if (rememberAdress == null)
			rememberAdress = new Adress(null, null, null);

		return "Adress";
	}

	public String cancel() {
		return "index";
	}

	public Salutation[] getSalutationValues() {
		return Salutation.values();
	}

	// public Kreditkartentyp[] getKreditkartentypValues() {
	// return Kreditkartentyp.values();
	// }

	public DataModel<Customer> getCustomers() {
		return customer;
	}

	public void setCustomer(DataModel<Customer> customers) {
		this.customer = customers;
	}

	public Customer getRememberCustomer() {
		return rememberCustomer;
	}

	public void setRememberCustomer(Customer rememberCustomer) {
		this.rememberCustomer = rememberCustomer;
	}

	// public Kreditkarte getMerkeKreditkarte() {
	// return merkeKreditkarte;
	// }
	//
	// public void setMerkeKreditkarte(Kreditkarte merkeKreditkarte) {
	// this.merkeKreditkarte = merkeKreditkarte;
	// }

	public Adress getRememberAdress() {
		return rememberAdress;
	}

	public void setRememberAdress(Adress rememberAdress) {
		this.rememberAdress = rememberAdress;
	}

}
