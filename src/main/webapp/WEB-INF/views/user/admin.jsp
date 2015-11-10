<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<html>
<head>
<title>Utilisateurs</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>\resources\css\bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>\resources\css\style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>\resources\css\animate.css">
<script
	src="<%=request.getContextPath()%>\resources\js\jquery-1.11.3.min.js"></script>
<script
	src="<%=request.getContextPath()%>\resources\js\bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<!-- Inclusion de la navigation -->
	<c:import url="../include/menu.jsp"></c:import>

	<!-- Message confirmation d'action -->
	<p class="message">
		<c:out value="${message}" />
	</p>

	<!-- Formulaire d'ajout/modification -->
	<form:form modelAttribute="entity" method="post"
		action="${pageContext.request.contextPath}/user/${formAction}.html">
		<form:hidden path="id" />
		<div>
			<form:label path="firstName">Prénom</form:label>
			<form:input path="firstName" />
			<form:errors path="firstName"></form:errors>
			<br />
		</div>
		<div>
			<form:label path="lastName">Nom</form:label>
			<form:input path="lastName" />
			<br />
		</div>
		<div>
			<form:label path="userName">Identifiant</form:label>
			<form:input path="userName" />
			<br />
		</div>
		<div>
			<form:label path="password">Password</form:label>
			<form:password path="password" />
			<br />
		</div>
		<div>
			<form:label path="administrator">Administrateur</form:label>
			Oui
			<form:radiobutton value="true" path="administrator" />
			Non
			<form:radiobutton value="false" path="administrator" />
		</div>

		<input type="submit" />
	</form:form>
	<hr />

	<!-- Liste des Utilisateurs -->
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Pr&eacute;nom</th>
				<th>Nom</th>
				<th>Identifiant</th>
				<th>Administrateur</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${entities}" var="user">
				<tr>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.lastName}" /></td>
					<td><c:out value="${user.userName}" /></td>
					<td><c:out value="${user.administrator}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/user/read/<c:out value="${user.id}"/>.html">Modifier</a></td>
					<td><a
						href="${pageContext.request.contextPath}/user/delete/<c:out value="${user.id}"/>.html">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>