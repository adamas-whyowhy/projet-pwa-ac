package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.Gestionnaire;
import pojo.Automate;
import pojo.FicheAutomate;
import pojo.TypeContenu;

@WebServlet("/gestion")
public class GestionFicheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GestionFicheServlet() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("afficher".equalsIgnoreCase(request.getParameter("mode"))) {
			FicheAutomate fiche = Gestionnaire.getInstance().getFicheAutomateDao().findById(request.getParameter("idFiche"));
			request.setAttribute("automate", fiche);
			request.getRequestDispatcher("/views/fiche.jsp").forward(request, response);
		} else if ("modifier".equalsIgnoreCase(request.getParameter("mode"))) {
			FicheAutomate fiche = Gestionnaire.getInstance().getFicheAutomateDao().findById(request.getParameter("idFiche"));
			request.setAttribute("automate", fiche);
			request.getRequestDispatcher("/views/fiche_modif_formulaire.jsp").forward(request, response);
		} else if ("ajouter".equalsIgnoreCase(request.getParameter("mode"))) {
			List<Automate> results = Gestionnaire.getInstance().getAutomateDao().findAllHasNotFiche();
			request.setAttribute("results", results);
			request.getRequestDispatcher("/views/fiche_ajout_formulaire.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("val_suppression".equalsIgnoreCase(request.getParameter("mode"))) {
			Gestionnaire.getInstance().getFicheAutomateDao().delete(request.getParameter("idFiche"));
			response.sendRedirect(request.getContextPath()+"/");
		} else if ("val_modification".equalsIgnoreCase(request.getParameter("mode"))) {
			FicheAutomate fiche = Gestionnaire.getInstance().getFicheAutomateDao().findById(request.getParameter("idFiche"));
			try {
				fiche.setType((TypeContenu.valueOf(request.getParameter("typeContenu"))));
				fiche.setEmplacement(request.getParameter("emplacement"));
				fiche.setCoordonneesGPS(request.getParameter("coords_gps"));
				fiche.setAdresse(request.getParameter("adresse"));
				fiche.setCommentaires(request.getParameter("commentaires"));
				fiche.setUrl(request.getParameter("url"));
				fiche.setDerniereIntervention(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("derniere_intervention")));
				Gestionnaire.getInstance().getFicheAutomateDao().update(fiche);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/gestion?mode=afficher&idFiche="+fiche.getIdFiche());
		} else if ("val_ajout".equalsIgnoreCase(request.getParameter("mode"))) {
			Automate automate = Gestionnaire.getInstance().getAutomateDao().findById(request.getParameter("num_serie"));
			FicheAutomate fiche;
			try {
				fiche = new FicheAutomate(automate, TypeContenu.valueOf(request.getParameter("typeContenu")), request.getParameter("url"),
						request.getParameter("adresse"), request.getParameter("emplacement"), request.getParameter("coords_gps"),
						new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("derniere_intervention")), request.getParameter("commentaires"));
				Gestionnaire.getInstance().getFicheAutomateDao().save(fiche);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/");
		}
	}
}
