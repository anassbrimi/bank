<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>
<head>
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
<title>Opérations bancaires</title>
</head>
<body>
<!-- Inclusion de la navigation -->
    <c:import url="../include/menu.jsp"></c:import>
	<!--  Message confirmation d'action -->
	<p class="message">
		<c:out value="${message}" />
	</p>

	<!-- Choix du compte bancaire -->
	<form:form method="get" modelAttribute="account"
		action="${pageContext.request.contextPath}/operation/changeAccount.html">
		<form:label path="id">Choix du compte bancaire : </form:label>
		<form:select path="id" items="${accounts}" itemValue="id"
			itemLabel="reference" onChange="submit()" />
	</form:form>

	<hr />

	<!--  Formulaire d'ajout/modification -->
	<form:form modelAttribute="entity" method="post"
		action="${pageContext.request.contextPath}/operation/${formAction}.html">
		<form:hidden path="id" />
		<div>
			<form:label path="description">Description</form:label>
			<form:input path="description" />
			<br />
		</div>
		<div>
			<form:label path="amount">Montant</form:label>
			<form:input path="amount" />
			<br />
		</div>
		<div>
			<form:label path="date">Date</form:label>
			<form:input path="date" />
			<br />
		</div>

		<input type="submit" />
	</form:form>
	<hr />

	<!-- Liste des opérations bancaires -->
	<table>
		<thead>
			<tr>
				<th>Description</th>
				<th>Montant</th>
				<th>Date</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${entities}" var="entity">
				<tr>
					<td><c:out value="${entity.description}" /></td>
					<td><c:out value="${entity.amount}" /> $</td>
					<td><c:out value="${entity.date}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/operation/read/<c:out value="${entity.id}"/>.html">Modifier</a></td>
					<td><a
						href="${pageContext.request.contextPath}/operation/delete/<c:out value="${entity.id}"/>.html">Supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>