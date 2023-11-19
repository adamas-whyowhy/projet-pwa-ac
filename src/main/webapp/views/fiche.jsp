<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, pojo.FicheAutomate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des fiches automate</title>
</head>
<body>
	<h4>Fiche automate</h4>
	<p>
		<% FicheAutomate fiche = (FicheAutomate)request.getAttribute("automate");
		if(fiche==null) { %>
		Aucun automate correspondant à l'identifiant spécifié n'a été trouvé.
		<% } else { %>
		 <table>
		   <thead>
				<tr>
					<th colspan="8">FICHE DE L'AUTOMATE</th>
		     	</tr>
		   </thead>
		   <tbody>
		     <tr>
				<th colspan="3">Numéro de série</th>
		       <td><%= fiche.getNumeroSerie() %></td>
		     </tr>
		     <tr>
				<th colspan="3">Type</th>
		       <td><%= fiche.getType() %></td>
		     </tr>
		     <tr>
		       <th colspan="3">Adresse d'installation</th>
		       <td><%= fiche.getAdresse() %></td>
		     </tr>
		     <tr>
		       <th colspan="3">Emplacement</th>
		       <td><%= fiche.getEmplacement() %></td>
		     </tr>
		     <tr>
		       <th colspan="3">Coordonnées GPS</th>
		       <td><%= fiche.getCoordonneesGPS() %></td>
		     </tr>
		     <tr>
		       <th colspan="3">Date de dernière intervention</th>
		       <td><%= fiche.getDerniereIntervention() %></td>
		     </tr>
		     <tr>
		       <th colspan="3">Commentaires</th>
		       <td><% if (fiche.getCommentaires() != null) {%> <%= fiche.getCommentaires() %><% } %></td>
		     </tr>
		   </tbody>        
		</table>
		<form method="post" action="/projet-pwa-ac/gestion"><input type="hidden" name="idFiche" value="<%=fiche.getIdFiche()%>"><button name="mode" value="val_suppression" type="submit">Supprimer la fiche</button></form>
		<form method="get" action="/projet-pwa-ac/gestion"><input type="hidden" name="idFiche" value="<%=fiche.getIdFiche()%>"><button name="mode" value="modifier" type="submit">Modifier la fiche</button></form>
		<% } %>
	</p>
</body>
</html>