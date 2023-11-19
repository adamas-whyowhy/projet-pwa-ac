package persistence;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jpa.JpaManager;
import pojo.Produit;

public class ProduitDao {
	private EntityManagerFactory factory = JpaManager.getInstance();
	
	public ProduitDao() {}
	
	public void save(Produit produit) throws SQLIntegrityConstraintViolationException {
		EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(produit);
	    em.getTransaction().commit();
		em.close();
	}
	
    public Produit findById(String id) {
        EntityManager em = factory.createEntityManager();
        Produit produit = em.find(Produit.class, id);
        return produit;
    }
    
}
