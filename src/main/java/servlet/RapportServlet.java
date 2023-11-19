package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.Gestionnaire;
import pojo.FicheAutomate;

@WebServlet("/rapport")
public class RapportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("envoyer".equalsIgnoreCase(request.getParameter("mode"))) {
			FicheAutomate fiche = Gestionnaire.getInstance().getFicheAutomateDao().findById(request.getParameter("idFiche"));
			fiche.envoyerRapport();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String[]> modelMap = request.getParameterMap();
		for(String key: modelMap.keySet()){
			System.out.print(key);
            System.out.print(" : ");
            System.out.print(modelMap.get(key));
            System.out.println();
        }
		
	}
}
