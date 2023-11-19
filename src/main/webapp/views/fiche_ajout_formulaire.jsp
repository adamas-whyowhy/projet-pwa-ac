<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, pojo.Automate, pojo.TypeContenu" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des fiches automate</title>
</head>

<body>
	<div>
		<h4>Ajout d'un fiche automate</h4>
		<p>
			<form action="/projet-pwa-ac/gestion" method="post">
			  <div>
			    <label for="num_serie">Numéro de série : </label>
			    <select id="num_serie" name="num_serie">
			    <% 
			    List<Automate> results = (List<Automate>)request.getAttribute("results");
			    for (Automate automate : results) { %>
				  <option value="<%=automate.getNumeroSerie() %>"><%=automate.getNumeroSerie() %></option>
				<% } %>
				</select>
			  </div>
			  <div>
			    <label for="typeContenu">Type : </label>
			    <select id="typeContenu" name="typeContenu">
				  <option value="<%=TypeContenu.BOISSON_CHAUDE %>">Boisson Chaude</option>
				  <option value="<%=TypeContenu.BOISSON_FROIDE %>">Boisson Froide</option>
				  <option value="<%=TypeContenu.ENCAS %>">Encas</option>
				</select>
			  </div>
			  <div>
			    <label for="adresse">Adresse d'installation : </label>
			    <input type="text" name="adresse" id="adresse" value="" required>
			  </div>
			  <div>
			    <label for="emplacement">Emplacement : </label>
			    <input type="text" name="emplacement" id="emplacement" value="" required>
			  </div>
			  <div>
			    <label for="coords_gps">Coordonnées GPS : </label>
			    <input type="text" name="coords_gps" id="coords_gps" pattern="-?[0-9]*\.[0-9]+\s-?[0-9]*\.[0-9]+" value="" required>
			  </div>
			  <div>
			    <label for="derniere_intervention">Date de dernière intervention : </label>
			    <input type="datetime-local" id="derniere_intervention" name="derniere_intervention" value="" required>
			  </div>
			  <div>
			    <label for="url">URL : </label>
			    <input type="text" name="url" id="url" value="" required>
			  </div>
			  <div>
			    <label for="commentaires">Commentaires : </label>
			  	<textarea id="commentaires" name="commentaires" rows="4" cols="50"></textarea>
			  </div>
			  <div>
			    <button name="mode" value="val_ajout" type="submit">Valider</button>
			  </div>
			</form>
		</p>
	</div>
</body>