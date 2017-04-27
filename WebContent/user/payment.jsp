<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList"%> 
<%@ page import = "com.book.store.OrdProcessBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<link rel=stylesheet href="../css/container.css"></link>
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
<%
if (session.getAttribute("customerbean") != null) {
%>
<h1>Payment</h1>
<br>
<%			
int total = 0;
ArrayList<OrdProcessBean> cartList = null;
cartList = (ArrayList<OrdProcessBean>) session.getAttribute("cart");
for(int i = 0; i < cartList.size(); i++){
	String goodsName = cartList.get(i).getBb().getTitle();
	int price = cartList.get(i).getPrice();
	total=total+price;
%>
<%=i %>.<%=goodsName %>       <%=price %><br>
<%} %>
<br>
<h1>total price:<%=total %></h1>
<center>
<form action="../ControlServlet" method="get">
<input type="submit" value="Pay"/>
<input type="hidden" name="totalprice" value="<%=total%>"/>
<input type="hidden" name="action" value="pay"/>
</form>
</center>
<%}%>
</body>
</html>