<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="dao" class="com.shoppingcart.DAO.ShoppingDAO" />
	<jsp:useBean id="record" class="com.shoppingcart.model.RecordBean" />
	<jsp:setProperty name="record" property="*" />
	<%
		String cname = (String) session.getAttribute("cname");
		String cpwd = (String) session.getAttribute("cpwd");
		Boolean islogin = false;
		if (cname != null) {
			islogin = true;
		}
		if (islogin) {
			if (dao.delete(cname, record.getPname())) {
				response.sendRedirect("cartview.jsp");
			} else {
				session.setAttribute("returnmessagetocart", "Failed to delete product from cart!");
				response.sendRedirect("cartview.jsp");
			}
		}
	%>
</body>
</html>