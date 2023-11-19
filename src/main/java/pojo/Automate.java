package pojo;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Automate {
	@Id
	private String numeroSerie;				// Numéro de série
	@ManyToOne
	private ModuleTelecom module_;			// Module de télécommunication
	@ElementCollection
	private Map<Produit, Integer> produits;	// Produits et leur quantité
	private double montantVentes;			// Montant des ventes
	
	private StatutFonctionnement statutFonctionnement;
	private EtatAppareil etatActuel;
	private EtatMonnayeur etatMonnayeur;
	private EtatCarte etatCartePuce;
	private EtatCarte etatSansContact;
	
	public String getNumeroSerie() { return numeroSerie; }
	public StatutFonctionnement getStatutFonctionnement() { return statutFonctionnement; }
    public EtatAppareil getEtatAppareil() { return etatActuel; }
    public EtatMonnayeur getEtatMonnayeur() { return etatMonnayeur; }
    public EtatCarte getEtatCartePuce() { return etatCartePuce; }
    public EtatCarte getEtatCarteSansContact() { return etatSansContact; }
    public ModuleTelecom getModule() { return module_; }
    public double getMontantVentes() { return montantVentes; }
	
    public void setMontantVentes(double montant) { this.montantVentes = montant; }    
	public void setStatutFonctionnement(StatutFonctionnement statut) { statutFonctionnement = statut; }
    public void setEtatAppareil(EtatAppareil etat) { etatActuel = etat; }
    public void setEtatMonnayeur(EtatMonnayeur etat) { etatMonnayeur = etat; }
    public void setEtatCartePuce(EtatCarte etat) { etatCartePuce = etat; }
    public void setEtatCarteSansContact(EtatCarte etat) { etatSansContact = etat; }

    public Map<Produit, Integer> getProduits() { return produits; }
	
    public Automate() {}
    public Automate(String numeroSerie, ModuleTelecom module, Map<Produit, Integer> produits) {
    	this.numeroSerie = numeroSerie;
    	this.module_ = module;
    	if (produits != null)
    		this.produits = produits;
    	else
    		produits = new HashMap<Produit, Integer>();
    	this.montantVentes = 0;
    	this.statutFonctionnement = StatutFonctionnement.EN_SERVICE;
		this.etatActuel = EtatAppareil.OK;
		this.etatMonnayeur = EtatMonnayeur.NORMAL;
		this.etatCartePuce = EtatCarte.NORMAL;
		this.etatSansContact = EtatCarte.NORMAL;
    }
    
    public void ajouterProduit(Produit produit, int quantite) {
        this.produits.put(produit, quantite);
    }

    public void retirerProduit(Produit produit) {
        this.produits.remove(produit);
    }
    
    public void modifierQuantite(Produit produit, int quantite) {
    	this.produits.remove(produit);
    	this.produits.put(produit, quantite);
    }
    
	public int getSeuilReapprovisionnement(double temperature, TypeContenu type) {
		// Récupérer température et faire *3
		if (temperature < 5 && type == TypeContenu.BOISSON_CHAUDE)
			return 10*3;
		else if (temperature >= 25 && type == TypeContenu.BOISSON_FROIDE)
			return 10*3;
		else
			return 10;
	}
	
}