package persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpa.Gestionnaire;
import jpa.JpaManager;
import pojo.Automate;
import pojo.FicheAutomate;

public class AutomateDao {
    private EntityManagerFactory factory = JpaManager.getInstance();

    public AutomateDao() {}
    
    /**
     * Sauvegarder automate dans la base
     * @param automate L'automate à enregistrer
     */
    public void save(Automate automate) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
		em.persist(automate);
		em.getTransaction().commit();
		em.close();
    }
    
    /**
     * Modifier un automate
     * @param automate
     */
    public void update(Automate automate) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(automate);
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Supprimer une fiche d'automate si le numéro est valide
     * @param automateFiche
     */
    public void delete(String id) {
    	EntityManager em = factory.createEntityManager();
    	Automate a = this.findById(id);
    	if (a != null) {
	    	em.getTransaction().begin();
	    	em.remove(em.merge(a));
			em.getTransaction().commit();
			em.close();
    	}
    }
    
    /**
     * Trouver une fiche d'automate par son numéro de série
     * @param id le numéro de série
     * @return une fiche d'automate
     */
    public Automate findById(String id) {
        EntityManager em = factory.createEntityManager();
        Automate automate = em.find(Automate.class, id);
        return automate;
    }
    
    /**
     * Récupérer tous les automates qui n'ont pas encore de fiche
     * @return une liste d'automates qui n'ont pas encore de fiche
     */
    public List<Automate> findAllHasNotFiche() {
    	EntityManager em = factory.createEntityManager();
    	List<Automate> exclusion = new ArrayList<Automate>();
    	List<FicheAutomate> fiches = Gestionnaire.getInstance().getFicheAutomateDao().findAll();
    	// Liste des automates à exclure
    	for (FicheAutomate f : fiches)
    		exclusion.add(f.getAutomate());
    	// Utilisation d'un CriteriaBuilder
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery <Automate> query = builder.createQuery(Automate.class);
    	Root<Automate> p = query.from(Automate.class);
    	query.select(p);
    	query.where(builder.not(p.in(exclusion)));
    	// Retour du résultat
    	return em.createQuery(query).getResultList();
    }
    
    /**
     * Récupérer tous les automates
     * @return une liste d'automates
     */
    public List<Automate> findAll() {
    	EntityManager em = factory.createEntityManager();
        List<Automate> resultList = em.createQuery("SELECT a FROM Automate a", Automate.class).getResultList();
        em.close();
        return resultList;
    }
    
    /**
     * Récupérer tous les automates selon un ordre décroissant des ventes
     * @return une liste triée par ordre décroissant des ventes
     */
    public List<Automate> findAllOrderBy() {
    	EntityManager em = factory.createEntityManager();
        List<Automate> resultList = em.createQuery("SELECT a FROM Automate a ORDER BY a.montantVentes DESC", Automate.class).getResultList();
        em.close();
        return resultList;
    }
    
}
