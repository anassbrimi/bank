<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<html>
<head>
<title>Comptes bancaires</title>
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

	<!--  Message confirmation d'action -->
	<p class="message">
		<c:out value="${message}" />
	</p>

	<!--  Formulaire d'ajout/modification -->
	<form:form modelAttribute="entity" method="post"
		action="${pageContext.request.contextPath}/account/${formAction}.html">
		<form:hidden path="id" />
		<div>
			<form:label path="user">Proprietaire</form:label>
			<form:select path="user" items="${users}" var="u" itemValue="id"
				itemLabel="firstName" />
			<br />
		</div>
		<div>
			<form:label path="reference">Reference</form:label>
			<form:input path="reference" />
			<br />
		</div>
		<div>
			<form:label path="balance">Solde initial</form:label>
			<form:input path="balance" />
			<br />
		</div>
		<input type="submit" />

	</form:form>
	<hr />

	<!-- Liste des comptes bancaires -->
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Proprietaire</th>
				<th>Solde</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${entities}" var="entity">
				<tr>
					<td><c:out value="${entity.user.firstName}" /></td>
					<td><c:out value="${entity.balance}" /> $</td>
					<td><a
						href="${pageContext.request.contextPath}/account/read/<c:out value="${entity.id}"/>.html">Modifier</a></td>
					<td><a
						href="${pageContext.request.contextPath}/account/delete/<c:out value="${entity.id}"/>.html">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>