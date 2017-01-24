<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script data-require="jquery@*" data-semver="3.1.1"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<link rel="stylesheet" href="css/view.css" />
<script src="script/loginscript.js"></script>
<title>Login page</title>
</head>
<body>
	<header>
	<h1 id="title">Login</h1>
	<input class='navCls' id='btn_home' type='button' value='Home' /> </header>
	<%
		String msg = (String) session.getAttribute("loginmessage");
	%>
	<form method="get" action="callinglogin.jsp" style="clear: both">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="cname" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="cpwd" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="login" /></td>
				<td><input id="btn_register" type="button" value="register" /></td>
			</tr>
		</table>
	</form>
	<div>
		<p style="color: red">
			<%
				if (msg != null) {
					out.print(msg);
					session.setAttribute("loginmessage", null);
				}
			%>
		</p>
	</div>
</body>
</html>