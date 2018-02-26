package Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
import Model.Product;
import Model.Salutation;

public class ProductHandler {

	@PersistenceContext
	private EntityManager em;
	@Resource
	private UserTransaction utx;

	private DataModel<Product> product;
	private Product rememberProduct = new Product();

	@PostConstruct
	public void init() {
		try {
			utx.begin();
			em.persist(new Product("Hose", 34, "stinkt"));

			product = new ListDataModel<Product>();
			product.setWrappedData(em.createNamedQuery("SelectProduct").getResultList());
			utx.commit();

		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
	}

	public String newProduct() {
		rememberProduct = new Product();
		return "newProduct";
	}

	public String saveProduct() {
		try {
			utx.begin();
			rememberProduct = em.merge(rememberProduct);
			em.persist(rememberProduct);
			product.setWrappedData(em.createNamedQuery("SelectProduct").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
		return "allProducts";
	}

	public String deleteProduct() {
		// -> Transaktionsbeginn
		try {
			utx.begin();
			rememberProduct = product.getRowData();
			rememberProduct = em.merge(rememberProduct);
			em.remove(rememberProduct);
			product.setWrappedData(em.createNamedQuery("SelectProduct").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
			// -> Transaktionsende
		}
		return "allProducts";
	}

	public String editProduct() {
		rememberProduct = product.getRowData();
		return "newProduct";
	}

	public String cancel() {
		return "index";
	}

	public DataModel<Product> getProduct() {
		return product;
	}

	public void setProduct(DataModel<Product> product) {
		this.product = product;
	}

	public Product getRememberProdukt() {
		return rememberProduct;
	}

	public void setRememberProduct(Product rememberProduct) {
		this.rememberProduct = rememberProduct;
	}

}
