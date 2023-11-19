package pojo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.json.JSONTokener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class FicheAutomate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int idFiche;
	@OneToOne
	private Automate automate;
	
	private String url;						// URL
	private TypeContenu typeContenu; 		// Type du contenu

	@Column(length=150)
	private String adresse;					// Adresse
	@Column(length=50)
	private String emplacement;				// Emplacement de l'automate
	@Column(length=50)
	private String coordonneesGPS;			// Coordonnées GPS de l'automate
	private Date derniereIntervention;		// Date de dernière intervention
	@Column(length=150)
	private String commentaires;			// Commentaires
	
	

	// Getters et setters
	public int getIdFiche() { return idFiche; }
	public String getNumeroSerie() { return automate.getNumeroSerie(); }
	public TypeContenu getType() { return typeContenu; }
	public String getAdresse() { return adresse; }
	public String getEmplacement() { return emplacement; }
	public Date getDerniereIntervention() { return derniereIntervention; }
	public String getCoordonneesGPS() { return coordonneesGPS; }
	public String getCommentaires() { return commentaires; }
	public double getMontantVentes() { return automate.getMontantVentes(); }
	public Automate getAutomate() { return automate; }
	public String getUrl() { return url; }
    public Map<Produit, Integer> getProduits() { return automate.getProduits(); }
    public StatutFonctionnement getStatutFonctionnement() { return automate.getStatutFonctionnement(); }
    public EtatAppareil getEtatAppareil() { return automate.getEtatAppareil(); }
    public EtatMonnayeur getEtatMonnayeur() { return automate.getEtatMonnayeur(); }
    public EtatCarte getEtatCartePuce() { return automate.getEtatCartePuce(); }
    public EtatCarte getEtatCarteSansContact() { return automate.getEtatCarteSansContact(); }
	
    public void setAutomate(Automate automate) { this.automate = automate; }
	public void setType(TypeContenu type) { this.typeContenu = type; }
	public void setAdresse(String adresse) { this.adresse = adresse; }
	public void setEmplacement(String emplacement) { this.emplacement = emplacement; }
	public void setCoordonneesGPS(String coordonneesGPS) { this.coordonneesGPS = coordonneesGPS; }
	public void setDerniereIntervention(Date derniereIntervention) { this.derniereIntervention = derniereIntervention; }
	public void setCommentaires(String commentaires) { this.commentaires = commentaires; }
	public void setUrl(String url) { this.url = url; }
	
	public FicheAutomate() {}
	
	public FicheAutomate(Automate automate, TypeContenu type, String url, String adresse, String emplacement, String coordonneesGPS, Date derniereIntervention, String commentaires) {
		this.automate = automate;
		this.url = url;
		this.typeContenu = type;
		this.adresse = adresse;
		this.emplacement = emplacement;
		this.coordonneesGPS = coordonneesGPS;
		this.derniereIntervention = derniereIntervention;
		this.commentaires = commentaires;
	}
    
    

	public void envoyerRapport() {
	    String message;
		try {
			message = genererRapport();
		
		    Automate automate = getAutomate();
		    ModuleTelecom module = automate.getModule();
		    int statusCode = 0;
		    do {
		        statusCode = module.envoyerMessage(message);
		        if (statusCode != 200) {
		            try {
		                TimeUnit.MINUTES.sleep(5);
		            } catch (InterruptedException e) {
		            	System.err.println("Une erreur s'est produite lors de l'attente de 5 minutes : " + e.getMessage());
		            	e.printStackTrace();
		            }
		        }
		    } while (statusCode != 200);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("Erreur lors de l'envoi du rapport : " + e1.getMessage());
		}
	}
	
	public double getTemperature() throws MalformedURLException, IOException {
		// Recuperation de la temperature
		String[] coords = this.getCoordonneesGPS().split(" ");
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?units=metric&lang=fr"
				+ "&appid=0ec1f169aa0bd928c48011d06b3bca2b"
				+ "&lat=" + coords[0]
				+ "&lon=" + coords[1]);
		URLConnection urlConn = url.openConnection();
		JSONObject json = new JSONObject(new JSONTokener(urlConn.getInputStream()));
		return json.getJSONObject("main").getDouble("temp_min");
	}
	

	private String genererRapport() throws MalformedURLException, IOException {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Numéro de série de l’automate de vente: ").append(getNumeroSerie()).append("\n");
	    sb.append("Date et heure de génération du rapport: ").append(new Date().toString()).append("\n");
	    sb.append("Statut de fonctionnement actuel: ").append(getStatutFonctionnement()).append("\n");
	    sb.append("Etat actuel: ").append(getEtatAppareil()).append("\n");
	    sb.append("Température en °C: ").append(getTemperature()).append("\n");
	    sb.append("Etat des systèmes de paiement - Monnayeur pièces: ").append(getEtatMonnayeur()).append("\n");
	    sb.append("Etat des systèmes de paiement - Cartes à puce: ").append(getEtatCartePuce()).append("\n");
	    sb.append("Etat des systèmes de paiement - Cartes sans contact: ").append(getEtatCarteSansContact()).append("\n");
	    //sb.append("Liste chronologique des erreurs: ").append(listeErreurs).append("\n");
	    sb.append("Contenu de l’appareil: ").append(getProduits()).append("\n");
	    sb.append("Montant des ventes réalisées depuis le précédent rapport: ").append(getMontantVentes()).append("\n");
	    automate.setMontantVentes(0); // réinitialisation du montant des ventes
	    return sb.toString();
	}
	
	

}
