<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<span>
  Bonjour, <sec:authentication property="principal.firstName"/><br /> 
  <a href="${pageContext.request.contextPath}/logout.html">Déconnexion</a>
</span>
<ul>
  <sec:authorize access="hasRole('ADMINISTRATOR')"><li><a href="${pageContext.request.contextPath}/user/index.html">Utilisateurs</a></li></sec:authorize>
  <li><a href="${pageContext.request.contextPath}/account/index.html">Comptes</a></li>
  <li><a href="${pageContext.request.contextPath}/operation/index.html">Opérations</a></li>
</ul>