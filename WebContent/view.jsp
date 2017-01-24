<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script data-require="jquery@*" data-semver="3.1.1"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/view.css" />
<script src="script/viewscript.js"></script>
<title>Shopping Site</title>
</head>
<body>
	<jsp:useBean id="list" class="com.shoppingcart.DAO.ShoppingDAO" />
	<%
		String cname = (String) session.getAttribute("cname");
		String cpwd = (String) session.getAttribute("cpwd");
		Boolean islogin = false;
		if (cname != null) {
			islogin = true;
		}
		String msg = (String) session.getAttribute("returnmessagetoview");
	%>
	<header>
		<h1 id="title">Shopping Site</h1>
		<%
			if (islogin) {
				out.print("<input class='navCls' id='btn_logout' type='button' value='logout' />");
				out.print("<span class='navCls displaycname'>" + cname + " </span>"
						+ "<span class='navCls displaycname'>Hi, </span>");
			} else {
				out.print("<input class='navCls' id='btn_register' type='button' value='register' />"
						+ "<input class='navCls' id='btn_login' type='button' value='login' />");
			}
		%>
	</header>
	<div id="main">
		<%
			if (islogin) {
				out.print("<input class='navCls' id='btn_wishlist' type='button' value='wishlist' />");
			}
		%>
		<input class='navCls' id="btn_cart" type="button" value="cart" />

		<div id="showlist">
			<table class="table table-bordered table-striped table-hover"
				id="productTable">
				<tr class="warning">
					<th>Item</th>
					<th>Price</th>
					<th>Option</th>
				</tr>
				<%
					out.print(list.showList(islogin));
				%>
			</table>
		</div>
	</div>

	<%
		if (msg != null) {
			out.print("<p style='color:red'>" + msg + "</p>");
			session.setAttribute("returnmessagetoview", null);
		}
	%>
</body>
</html>



