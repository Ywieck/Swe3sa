package Controller;

import java.util.Locale;

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
	
	private DataModel<Product> filteredProducts;

	@PostConstruct
	public void init() throws SecurityException, IllegalStateException, RollbackException, HeuristicRollbackException,
			SystemException, NotSupportedException, HeuristicMixedException, javax.transaction.NotSupportedException,
			javax.transaction.SystemException, javax.transaction.RollbackException {
		try {
			utx.begin();

			products = new ListDataModel<Product>();

			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			em.persist(new Product("Enno", 100.99F, "katfasf", "uafafaisf"));
			em.persist(new Product("Yasar", 1000.99F, "jfafjsf", "jfhaojsfhaojf"));
			products.setWrappedData(em.createNamedQuery("SelectedProduct").getResultList());
			utx.commit();

		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
	}

//	public boolean filterByPrice(Object value, Object filter, Locale locale) {
//		String filterText = (filter == null) ? null : filter.toString().trim();
//		if (filterText == null || filterText.equals("")) {
//			return true;
//		}
//
//		if (value == null) {
//			return false;
//		}
//
//		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
//	}
	
	public DataModel<Product> getProducts() {
		return products;
	}

	public void setProducts(DataModel<Product> products) {
		this.products = products;
	}

	public DataModel<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(DataModel<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

}
