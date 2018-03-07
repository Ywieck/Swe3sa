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

import Model.Product;

public class ProductHandler {

	@PersistenceContext
	private EntityManager em;
	@Resource
	private UserTransaction utx;

	private DataModel<Product> products;
	

	public DataModel<Product> getProducts() {
		return products;
	}


	public void setProducts(DataModel<Product> products) {
		this.products = products;
	}


	@PostConstruct
	public void init() throws SecurityException, IllegalStateException, RollbackException, HeuristicRollbackException, SystemException, NotSupportedException, HeuristicMixedException, javax.transaction.NotSupportedException, javax.transaction.SystemException
, javax.transaction.RollbackException {
		try {
			utx.begin();

			products = new ListDataModel<Product>();

			em.persist(new Product("Enno"));
			em.persist(new Product("Yasar"));
			products.setWrappedData(em.createNamedQuery("SelectedProduct").getResultList());
			utx.commit();

		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
	}

}
