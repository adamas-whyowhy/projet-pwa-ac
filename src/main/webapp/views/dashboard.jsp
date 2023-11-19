<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.Map.Entry, pojo.Automate, pojo.Produit" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tableau de bord</title>
</head>

<body>
	<div>
	<h4>Tableau de bord</h4>
		<% List<Automate> automates = (List<Automate>)request.getAttribute("automates");
		List<Automate> automates_hs = (List<Automate>)request.getAttribute("automates_hs");
		List<Automate> automates_attention = (List<Automate>)request.getAttribute("automates_attention");
		Map<Automate, List<Produit>> automates_a_reapprovisionner = (Map<Automate, List<Produit>>)request.getAttribute("automates_a_reapprovisionner");
		%>
		<div>
			<h4>AUTOMATES HORS SERVICE</h4>
			<p>
			<% if (automates_hs != null) { %>
				<ul>
			  	<%for (Automate a : automates_hs) { %>
					<li>L'automate <%= a.getNumeroSerie() %> est hors service.</li>
				<% } %>
				</ul>
			<% } %>
			</p>
		</div>
		<div>
			<h4>AUTOMATES NÉCESSITANT UNE ATTENTION PARTICULIÈRE</h4>
			<p>
			<% if (automates_attention != null) { %>
				<ul>
			  	<%for (Automate a : automates_attention) { %>
					<li>L'automate <%= a.getNumeroSerie() %> est en service et nécessite une attention particulière.</li>
				<% } %>
				</ul>
			<% } %>
			</p>
		</div>
		<div>
			<h4>AUTOMATES À RÉAPPROVISIONNER</h4>
			<p>
			<% if (automates_a_reapprovisionner != null) { %>
				<ul>
			  	<%for (Entry<Automate, List<Produit>> automate_set : automates_a_reapprovisionner.entrySet()) { %>
			  		<%for (Produit produit : automate_set.getValue()) { %>
			  			<li>Le produit <%=produit.getNom()%> de l'automate <%= automate_set.getKey().getNumeroSerie() %> doit être réapprovisionné.</li>
					<% } %>
				<% } %>
				</ul>
			<% } %>
			</p>
		</div>
		<div>
			<h4>AUTOMATES PAR ORDRE DÉCROISSANT DU MONTANT DES VENTES</h4>
			<p>
			<% if (automates != null) { %>
				<ul>
			  	<%for (Automate a : automates) { %>
					<li>L'automate <%= a.getNumeroSerie() %> a vendu pour <%= a.getMontantVentes() %> € de marchandise.</li>
				<% } %>
				</ul>
			<% } %>
			</p>
		</div>
		<div>
	</div>
</body>
</html>