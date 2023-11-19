package servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.Gestionnaire;
import pojo.Automate;
import pojo.EtatAppareil;
import pojo.FicheAutomate;
import pojo.Produit;
import pojo.StatutFonctionnement;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, MalformedURLException, IOException {
		List<Automate> automates = Gestionnaire.getInstance().getAutomateDao().findAllOrderBy(); // Liste des automates par ordre décroissant des ventes
		List<Automate> automates_hs = new ArrayList<Automate>(); // Liste des automates HS
		List<Automate> automates_attention = new ArrayList<Automate>(); // Liste des automates nécessitant une attention particulière
		Map<Automate, List<Produit>> automates_a_reapprovisionner = new HashMap<Automate, List<Produit>>(); // Liste des automates a réapprovisionner et de leurs produits à réapprovisionner

		for (Automate automate : automates) {
			StatutFonctionnement statut = automate.getStatutFonctionnement();
			EtatAppareil etat = automate.getEtatAppareil();
			if (statut == StatutFonctionnement.HORS_SERVICE) {
				automates_hs.add(automate);
			}
			else if (etat == EtatAppareil.ATTENTION || etat == EtatAppareil.ERREUR || etat == EtatAppareil.ALARME) {
				automates_attention.add(automate);
			}
			else if (etat == EtatAppareil.OK) {
				// Recuperation de la fiche
				// Recuperation de la temperature, uniquement pour les automates dont les coordonnées sont spécifiées (càd ont une fiche)
				try {
					FicheAutomate fiche = Gestionnaire.getInstance().getFicheAutomateDao().findByAutomate(automate);
					if (fiche != null) {
						double temperature = fiche.getTemperature();
						// Recuperation des produits de l'automate
						Map<Produit, Integer> produits = automate.getProduits();
						for (Entry<Produit, Integer> produit_set : produits.entrySet()) {
							Produit produit = produit_set.getKey();
							int quantite = produit_set.getValue();
							// Verification de la quantite
							if (quantite <= automate.getSeuilReapprovisionnement(temperature, fiche.getType())) {
								if(automates_a_reapprovisionner.containsKey(automate))
									automates_a_reapprovisionner.get(automate).add(produit);
								else {
									automates_a_reapprovisionner.put(automate, new ArrayList<Produit>());
									automates_a_reapprovisionner.get(automate).add(produit);
								}
							}
						}
					}
				} catch (NoResultException e) { /* Ne rien faire si l'appareil n'a pas de fiche et donc pas de coordonnées GPS*/}
			}
		}
		
		request.setAttribute("automates", automates);
		request.setAttribute("automates_hs", automates_hs);
		request.setAttribute("automates_attention", automates_attention);
		request.setAttribute("automates_a_reapprovisionner", automates_a_reapprovisionner);
		request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

	}
}
