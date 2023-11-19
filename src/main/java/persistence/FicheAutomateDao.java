package persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpa.JpaManager;
import pojo.Automate;
import pojo.FicheAutomate;

public class FicheAutomateDao {

    private EntityManagerFactory factory = JpaManager.getInstance();

    public FicheAutomateDao() {}
    
    /**
     * Sauvegarder une fiche d'automate dans la base
     * @param automateFiche Fiche de l'automate à enregistrer
     */
    public void save(FicheAutomate automateFiche) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
		em.persist(automateFiche);
		em.getTransaction().commit();
		em.close();
    }
    
    /**
     * Modifier une fiche d'automate
     * @param automateFiche
     */
    public void update(FicheAutomate automateFiche) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(automateFiche);
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Supprimer une fiche d'automate si le numéro est valide
     * @param automateFiche
     */
    public void delete(String id) {
    	EntityManager em = factory.createEntityManager();
    	FicheAutomate f = this.findById(id);
    	if (f != null) {
	    	em.getTransaction().begin();
	    	em.remove(em.merge(f));
			em.getTransaction().commit();
			em.close();
    	}
    }
    
    /**
     * Trouver une fiche d'automate par son numéro de série
     * @param id le numéro de série
     * @return une fiche d'automate
     */
    public FicheAutomate findById(String id) {
        EntityManager em = factory.createEntityManager();
        FicheAutomate automateFiche = em.find(FicheAutomate.class, id);
        return automateFiche;
    }

    public List<FicheAutomate> findAll() {
    	EntityManager em = factory.createEntityManager();
        List<FicheAutomate> resultList = em.createQuery("SELECT a FROM FicheAutomate a", FicheAutomate.class).getResultList();;
        em.close();
        return resultList;
    }

	public FicheAutomate findByAutomate(Automate automate) throws NoResultException {
		EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<FicheAutomate> query = builder.createQuery(FicheAutomate.class);
        Root<FicheAutomate> fiche = query.from(FicheAutomate.class);
        query.select(fiche);
        query.where(builder.equal(fiche.get("automate"), automate));
		FicheAutomate result = em.createQuery(query).getSingleResult();
        em.close();
		return result;
	}

}
