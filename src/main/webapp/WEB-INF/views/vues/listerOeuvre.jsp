<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
	<div class="jumbotron text-center">
		<h1>Listing des oeuvres</h1>
	</div>

	<div class="container">
		<a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
		<h2>Tableau des Oeuvres</h2>
		<div class="container">
			<h3>Liste des Oeuvres</h3>
			<table class="table table-hover">
				<tr>
					<th class="col-md-1">Numero</th>
					<th class="col-md-4">Titre</th>
					<th class="col-md-2">Prix</th>
					<th class="col-md-2">Numéro propriétaire</th>

				</tr>

				<c:forEach items="${mesOeuvres}" var="item">
					<tr>
						<td>${item.idOeuvrevente}</td>
						<td>${item.titreOeuvrevente}</td>
						<td>${item.prixOeuvrevente}</td>
						<td>${item.idProprietaire}</td>
						<td><a class="btn btn-info" href="modifierOeuvre.htm?id=${item.idOeuvrevente}" role="button"><span
								class="glyphicon glyphicon-pencil"></span> Modifier</a>
							<a class="btn btn-danger" href="reserverOeuvre.htm?id=${item.idOeuvrevente}" role="button"><span
									class="glyphicon glyphicon-remove-circle"></span> Supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
    </div>
<%@include file="footer.jsp"%>
</body>

</html>