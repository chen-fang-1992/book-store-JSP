<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*,
				java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String userid = request.getParameter("userid");
String pagenum = request.getParameter("page");
int pageNum = Integer.parseInt(pagenum);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Detail</title>
<link rel=stylesheet href="../css/container.css"></link>
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
<center><table><tr><td><h1>Customer Details</h1></td></tr></table></center>
<center>
<form action="../ControlServlet" method="get">
	<input type="submit" value="get customer activity"/>
	<input type="hidden" name="userid" value="<%=userid%>"/>
	<input type="hidden" name="page" value="<%=pagenum%>"/>
	<input type="hidden" name="action" value="cus_activity"/>
</form>
</center>
<table>
    <tr>
		<td>Time</td>
		<td>Action</td>
		<td>Publication</td>
		<td>Seller</td>
		<td>Price</td>
	</tr>
	<c:forEach var="result" items="${resultcusacts}"><tr>
		<td>${result.getTime()}</td>
		<td>${result.getAction()}</td>
    	<td><a href="../search/detail.jsp?bookid=${result.getBb().bookId}&type=${result.getBb().type}
            &authors=${result.getBb().authors}&editors=${result.getBb().editors}&url=${result.getBb().url}
            &title=${result.getBb().title}&booktitle=${result.getBb().bookTitle}&pages=${result.getBb().pages}
            &year=${result.getBb().year}&journal=${result.getBb().journal}&volume=${result.getBb().volume}
            &number=${result.getBb().number}&month=${result.getBb().month}&address=${result.getBb().address}
            &ee=${result.getBb().ee}&cite=${result.getBb().cite}&school=${result.getBb().school}
            &publisher=${result.getBb().publisher}&note=${result.getBb().note}&cdrom=${result.getBb().cdrom}
            &crossref=${result.getBb().crossref}&isbn=${result.getBb().isbn}&chapter=${result.getBb().chapter}
            &series=${result.getBb().series}&sid=${result.getBb().sid}&page=<%=pagenum %>
            &source=customer_detail">${result.getBb().getTitle()}</a></td>
		<td>${result.getSellerName()}</td>
		<td>${result.getPrice()}</td>
	</tr></c:forEach>
	<c:if test="${resultcusacts.size()==0}">Sorry, no matching datasets found!</c:if>
</table>
<form action="../admin/customer_manage.jsp">
	<input type="submit" value="Back"/>
	<input type="hidden" name="page" value="<%=pageNum%>"/>
</form>
</body>
</html>