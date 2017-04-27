<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*,
				java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String value = request.getParameter("value");
String key= request.getParameter("key");
String pagenum = request.getParameter("page");
int pageNum = Integer.parseInt(pagenum);
int countNum = 0;
Object OB = request.getServletContext().getAttribute("adminuserscount");
if(OB != null){
	String countnum = OB.toString();
	countNum = Integer.parseInt(countnum);}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Management</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/customer_manage.js"></script>
<script>
function auth() {
    var admin = "<%=session.getAttribute("admin")%>";
    if (admin == "null") {
        alert("please login");
        window.location.href = "../welcome.jsp";
    }
}
</script>
</head>
<body onload="auth()">
<center>
<form action="../ControlServlet" method="get">
	<table>
	<tr>
	<td><input type="hidden" name="search_type" value="customer_search"/>
		<input type="hidden" name="action" value="search"/></td>
	<td><input type="text" name="search_value"/></td>
	<td><select name="key">
		<option value="username">username</option>
		<option value="uid">userid</option>
	</select></td>
	<td><input type="submit" value="Search"/></td>
	</tr>
	</table>
</form>
</center>
<center>
<form action="../ControlServlet" method="get">
	<table>
	<tr>
	<td><h1>List of results!</h1></td>
	</tr>
	</table>
</form>
</center>
<center>
<form name="form2" action="../ControlServlet" method="post">
${adminbooks.size() }
	<table>
	<tr>
		<td>User id</td>
		<td>Username</td>
		<td>Email</td>
		<td>Status</td>
	</tr>
	<c:forEach var="user" items="${adminusers}" begin="<%=pageNum*10%>" end="<%=pageNum*10+9%>"><tr>
    	<td>${user.getUid()}</td>
		<td><a href="../admin/customer_detail.jsp?userid=${user.getUid()}&page=<%=pagenum%>">${user.getName()}</a></td>
		<td>${user.email}</td>
		<td>${user.status}</td>
		<c:if test="${user.status != 'good'}">
		<td><input type="checkbox" name="unban_checkbox" value="${user.uid}"/></td>
		</c:if>
		<c:if test="${user.status == 'good'}">
		<td><input type="checkbox" name="ban_checkbox" value="${user.uid}"/></td>
		</c:if>
	</tr></c:forEach>
	<c:if test="${adminusers.size()==0}">Sorry, no matching datasets found!</c:if>
	<tr>
		<td><input type="submit" value="Ban" onclick="ban()"></td>
		<td><input type="submit" value="Unban" onclick="unban()"></td>
		<td><input type="hidden" name="value" value="<%=value%>"/></td>
		<td><input type="hidden" name="key" value="<%=key%>"/></td>
		<td><input type="hidden" name="page" value="<%=pagenum%>"/></td>
		<td><input type="hidden" name="action" value=""/></td>
	</tr>
	</table>
</form>
</center>
<center>	
<form name="form1">
	<table><tr>
		<%if (pageNum > 0) {%>
		<td><input type="submit" value="Previous" onclick="previous()"/></td>
		<%}%>
		<%if (pageNum < countNum) {%>
		<td><input type="submit" value="Next" onclick="next()"/></td>
		<%}%>
		<td><input type="submit" value="Quit" onclick="back_home()"></td>
  		<td><input type="hidden" name="value" value="<%=value%>"/></td>
		<td><input type="hidden" name="key" value="<%=key%>"/></td>	
		<td><input type="hidden" name="page" value="<%=pagenum%>"/></td>
	</tr></table>
</form>
</center>
</body>
</html>