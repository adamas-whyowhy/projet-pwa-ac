package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.Gestionnaire;
import pojo.Automate;
import pojo.EtatAppareil;
import pojo.FicheAutomate;
import pojo.ModuleTelecom;
import pojo.ModuleTelecomT1;
import pojo.ModuleTelecomT2;
import pojo.Produit;
import pojo.StatutFonctionnement;
import pojo.TypeContenu;

@WebServlet("/")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccueilServlet() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// Remplissage de la base par défaut si ce n'est pas déjà fait
			ModuleTelecom module1 = new ModuleTelecomT1();
			ModuleTelecom module2 = new ModuleTelecomT2();
			HashMap<Produit, Integer> h1 = new HashMap<>();
			HashMap<Produit, Integer> h2 = new HashMap<>();
			HashMap<Produit, Integer> h3 = new HashMap<>();
			HashMap<Produit, Integer> h4 = new HashMap<>();
			HashMap<Produit, Integer> h5 = new HashMap<>();
			Produit p1 = new Produit("cafe", TypeContenu.BOISSON_CHAUDE);
			Produit p2 = new Produit("the", TypeContenu.BOISSON_CHAUDE);
			Produit p3 = new Produit("chocolat chaud", TypeContenu.BOISSON_CHAUDE);
			Produit p4 = new Produit("eau", TypeContenu.BOISSON_FROIDE);
			Produit p5 = new Produit("soda", TypeContenu.BOISSON_FROIDE);
			Produit p6 = new Produit("kinder", TypeContenu.ENCAS);
			Produit p7 = new Produit("cereales", TypeContenu.ENCAS);
			h1.put(p1, 5); h1.put(p2, 11); h1.put(p3, 3);
			h2.put(p1, 20); h2.put(p2, 2); h2.put(p3, 11);
			h3.put(p1, 50); h3.put(p2, 20); h3.put(p3, 27);
			h4.put(p4, 7); h4.put(p5, 12);
			h5.put(p6, 11); h5.put(p7, 9);
			Automate a1 = new Automate("AUTO_CHAUD_1", module1, h2);
			Automate a2 = new Automate("AUTO_CHAUD_2", module2, h1);
			Automate a3 = new Automate("AUTOTEMPBASSE", module2, h3);
			Automate a4 = new Automate("AUTO_FROID_1", module1, h4);
			Automate a5 = new Automate("AUTO_ENCAS_1", module1, h5);
			a1.setStatutFonctionnement(StatutFonctionnement.HORS_SERVICE);
			a4.setEtatAppareil(EtatAppareil.ALARME);
			a1.setMontantVentes(875.50);
			a2.setMontantVentes(40.99);
			a3.setMontantVentes(42.35);
			a4.setMontantVentes(81.54);
			a5.setMontantVentes(11.12);
			
			try {
				
				Gestionnaire.getInstance().getProduitDao().save(p1);
				Gestionnaire.getInstance().getProduitDao().save(p2);
				Gestionnaire.getInstance().getProduitDao().save(p3);
				Gestionnaire.getInstance().getProduitDao().save(p4);
				Gestionnaire.getInstance().getProduitDao().save(p5);
				Gestionnaire.getInstance().getProduitDao().save(p6);
				Gestionnaire.getInstance().getProduitDao().save(p7);
				
	
				
				Gestionnaire.getInstance().getModuleTelecomDao().save(module1);
				Gestionnaire.getInstance().getModuleTelecomDao().save(module2);
				Gestionnaire.getInstance().getAutomateDao().save(a1);
				Gestionnaire.getInstance().getAutomateDao().save(a2);
				Gestionnaire.getInstance().getAutomateDao().save(a3);
				Gestionnaire.getInstance().getAutomateDao().save(a4);
				Gestionnaire.getInstance().getAutomateDao().save(a5);
				Gestionnaire.getInstance().getFicheAutomateDao().save(new FicheAutomate(a1, TypeContenu.BOISSON_CHAUDE, "http://galilee.fr",
						"1 rue de La Ruelle", "2e etage", "38.722252 -9.139337", new Date(), ""));
				Gestionnaire.getInstance().getFicheAutomateDao().save(new FicheAutomate(a2, TypeContenu.BOISSON_CHAUDE, "http://galilee.fr",
						"2 rue de La Ruelle", "3e etage", "38.722252 -9.139337", new Date(), ""));
				Gestionnaire.getInstance().getFicheAutomateDao().save(new FicheAutomate(a3, TypeContenu.BOISSON_CHAUDE, "http://galilee.fr",
						"30 avenue du Froid", "RDC", "61.5231 105.1000", new Date(), "Une belle machine"));
			} catch (Exception e) {
				// Ne rien faire : la base est sûrement déjà créée
			}
			List<FicheAutomate> results = Gestionnaire.getInstance().getFicheAutomateDao().findAll();
			request.setAttribute("results", results);
			request.getRequestDispatcher("/views/gestion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
