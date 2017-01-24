<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script data-require="jquery@*" data-semver="3.1.1"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/view.css" />
<script src="script/wishlistscript.js"></script>
<title>Wish List</title>
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
		String msg = (String) session.getAttribute("returnmessagetowishlist");
	%>
	<header>
	<h1 id="title">Wish List</h1>
	<input class='navCls' id='btn_home' type='button' value='Home' /> <%
 	if (islogin) {
 		out.print("<span class='navCls displaycname'>" + cname + " </span>"
 				+ "<span class='navCls displaycname'>Hi, </span>");
 	} else {
 		out.print("<p>Error, not login yet</p>");
 	}
 %> </header>
	<div id="main">
		<%
			if (islogin) {
				out.print("<input class='navCls' id='btn_cart' type='button' value='cart' />");
			}
		%>
		<div id="wishList">
			<table class="table table-bordered table-striped table-hover"
				id="productTable">
				<tr class="warning">
					<th>Item</th>
					<th>Price</th>
					<th>Option</th>
				</tr>
				<%
					if (islogin) {
						out.print(list.showWishList(cname));
					} else {
						out.print("<tr><td colspan='2'>not login<td></tr>");
					}
				%>
			</table>
		</div>
	</div>
	<%
		if (msg != null) {
			out.print("<p style='color:red'>" + msg + "</p>");
			session.setAttribute("returnmessagetowishlist", null);
		}
	%>
</body>
</html>