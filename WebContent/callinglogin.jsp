<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="customer" class="com.shoppingcart.model.CustomerBean" />
	<jsp:setProperty name="customer" property="*" />
	<jsp:useBean id="dao" class="com.shoppingcart.DAO.ShoppingDAO" />
	<%
		if (dao.login(customer)) {
			session.setAttribute("cname", customer.getCname());
			session.setAttribute("cpwd", customer.getCpwd());
			response.sendRedirect("view.jsp");
		} else {
			session.setAttribute("loginmessage", "Failed to login!");
			response.sendRedirect("login.jsp");
		}
	%>

</body>
</html>