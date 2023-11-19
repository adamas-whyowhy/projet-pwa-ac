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
	<div>
		<h4>Gestion des fiches automate</h4>
		<p>
			<form method="get" action="/projet-pwa-ac/gestion">
				<button name="mode" value="ajouter" type="submit">Ajouter une fiche</button>
			</form>
			<form method="get" action="/projet-pwa-ac/dashboard">
				<button type="submit">Tableau de bord</button>
			</form>
		</p>
		<p>
			 <table>
				  <tr>
				    <th>Numéro de série</th>
				    <th>Fiche</th>
				  </tr>
				  <%
					List<FicheAutomate> results = (List<FicheAutomate>)request.getAttribute("results");
					if (results != null) {
				  	for (FicheAutomate f : results) { %>
				  <tr>
					 <td><%=f.getNumeroSerie()%></td>
				     <td>
				          <a href="/projet-pwa-ac/gestion?mode=afficher&idFiche=<%=f.getIdFiche()%>"><button>Afficher</button></a>
				     </td>
				  </tr>
				  <% }}
				  %>
			</table>
		</p>
	</div>
</body>
</html>