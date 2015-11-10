<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Login</title>
<style type="text/css">
/* Override some defaults */
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #fff;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
	background-color: #f7f7f7;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.form-signin img {
	margin: 0 25% 0 25%;
}

.heading {
	font-size: 25px;
}

.heading-desc {
	font-size: 18px;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-radius: 0;
}

.help {
	margin-top: 10px;
}

.account-creation {
	display: block;
	margin-top: 10px;
}
</style>


</head>
<body>

	<div class="container">


		<form class="form-signin" action="j_spring_security_check" method="post" >
			
				 <input type="text" class="form-control"
				placeholder="Username" autofocus name="j_username"/> 
				<input
				type="password" class="form-control" placeholder="Password"
				name="j_password"/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			<input type="checkbox" name="_spring_security_remember_me"  />Remember me


		</form>

	</div>

</body>
</html>