package persistence;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jpa.JpaManager;
import pojo.ModuleTelecom;

public class ModuleTelecomDao {
	private EntityManagerFactory factory = JpaManager.getInstance();
	
	public ModuleTelecomDao() {}
	
	public void save(ModuleTelecom module) throws SQLIntegrityConstraintViolationException {
		EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(module);
	    em.getTransaction().commit();
		em.close();
	}
	
}
