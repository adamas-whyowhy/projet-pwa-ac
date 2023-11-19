<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, pojo.FicheAutomate, pojo.TypeContenu" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des fiches automate</title>
</head>

<body>
	<div>
		<h4>Modification d'un fiche automate</h4>
		<p>
			<% FicheAutomate fiche = (FicheAutomate)request.getAttribute("automate");
			if(fiche==null) { %>
			Aucun automate correspondant à l'identifiant spécifié n'a été trouvé.
			<% } else { %>
			<form action="/projet-pwa-ac/gestion" method="post">
			<input type="hidden" name="idFiche" value="<%=fiche.getIdFiche()%>">
			  <div>
			    <label for="num_serie">Numéro de série : </label>
			    <input type="text" name="num_serie" id="num_serie" value="<%= fiche.getNumeroSerie() %>" required disabled>
			    <label for="typeContenu">Type : </label>
			    <select id="typeContenu" name="typeContenu">
				  <option value="<%=TypeContenu.BOISSON_CHAUDE %>">Boisson Chaude</option>
				  <option value="<%=TypeContenu.BOISSON_FROIDE %>">Boisson Froide</option>
				  <option value="<%=TypeContenu.ENCAS %>">Encas</option>
				</select>
			  </div>
			  <div>
			    <label for="adresse">Adresse d'installation : </label>
			    <input type="text" name="adresse" id="adresse" value="<%= fiche.getAdresse() %>" required>
			  </div>
			  <div>
			    <label for="emplacement">Emplacement : </label>
			    <input type="text" name="emplacement" id="emplacement" value="<%= fiche.getEmplacement() %>" required>
			  </div>
			  <div>
			    <label for="coords_gps">Coordonnées GPS : </label>
			    <input type="text" name="coords_gps" id="coords_gps" pattern="-?[0-9]*\.[0-9]+\s-?[0-9]*\.[0-9]+" value="<%= fiche.getCoordonneesGPS() %>" required>
			  </div>
			  <div>
			    <label for="derniere_intervention">Date de dernière intervention : </label>
			    <input type="datetime-local" id="derniere_intervention" name="derniere_intervention" value="<%= fiche.getDerniereIntervention() %>">
			  </div>
			  <div>
			    <label for="url">URL : </label>
			    <input type="text" name="url" id="url" value="<%= fiche.getUrl() %>" required>
			  </div>
			  <div>
			    <label for="commentaires">Commentaires : </label>
			  	<textarea id="commentaires" name="commentaires" rows="4" cols="50"><%= fiche.getCommentaires() %></textarea>
			  </div>
			  <div>
			    <button name="mode" value="val_modification" type="submit">Valider</button>
			  </div>
			</form>
			<% } %>
		</p>
	</div>
</body>