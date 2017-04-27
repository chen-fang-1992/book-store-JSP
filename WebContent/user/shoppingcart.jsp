<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*,
				java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
<script src="../js/shoppingcart.js"></script>
<script>
function auth() {
    var cb = "<%=session.getAttribute("customerbean")%>";
    if (cb == "null") {
        alert("please login");
        window.location.href = "../welcome.jsp";
    }
}
</script>
</head>
<body onload="auth()">
<h1>Shopping Cart</h1>
<form action="../ControlServlet" method="get">
	<table>
	<tr>
		<td>Title</td>
	</tr>
	<c:forEach var="item" items="${cart}" ><tr>
        <td>${item.getBb().getTitle()}</td>
		<td><input type="checkbox" name="cart_checkbox" value="${item.getSellId()}"/></td>
	</tr></c:forEach>
	<c:if test="${cart.size()==0}">Sorry, no matching datasets found!</c:if>
	<tr>
		<td><input type="submit" value="Remove"></td>
		<td><input type="hidden" name="action" value="removefromcart"/></td>
	</tr>
	</table>
</form>
<table>
	<tr>
		<td><input type="button" value="Check out" onclick="payment()"/></td>
		<td><input type="button" value="Back to Homepage" onclick="back_home()"/></td>
	</tr>
</table>
</body>
</html>