<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<title>Selling</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
<script src="../js/selling.js"></script>
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
	String pagenum = request.getParameter("page");
	int pageNum = Integer.parseInt(pagenum);
	String countnum = request.getServletContext().getAttribute("allsellscount").toString();
	int countNum = Integer.parseInt(countnum);
%>
<form name="form2" action="../ControlServlet" method="post">
	<center><h1>Items you are selling</h1></center>
	<table>
		<c:forEach var="opb" items="${allsells}" begin="<%=pageNum*10%>" end="<%=pageNum*10+9%>"><tr>
			<td><a href="../search/detail_sell.jsp?bookid=${opb.getBb().bookId}&type=${opb.getBb().type}
		        &authors=${opb.getBb().authors}&editors=${opb.getBb().editors}&address=${opb.getBb().address}
		        &title=${opb.getBb().title}&booktitle=${opb.getBb().bookTitle}&pages=${opb.getBb().pages}
				&year=${opb.getBb().year}&journal=${opb.getBb().journal}&volume=${opb.getBb().volume}
				&number=${opb.getBb().number}&month=${opb.getBb().month}&url=${opb.getBb().url}
				&ee=${opb.getBb().ee}&cite=${opb.getBb().cite}&school=${opb.getBb().school}
				&publisher=${opb.getBb().publisher}&note=${opb.getBb().note}&cdrom=${opb.getBb().cdrom}
				&crossref=${opb.getBb().crossref}&isbn=${opb.getBb().isbn}&chapter=${opb.getBb().chapter}
				&series=${opb.getBb().series}&sid=${opb.getSellId()}&page=<%=pagenum%>
				&source=selling">${opb.getBb().title}</a></td>
			<td>${opb.getPrice()}</td>
			<td>${opb.getStatus()}</td>
			<c:if test="${opb.getStatus() == 'pending'}">
			<td><input type="checkbox" name="activate_checkbox" value="${opb.getSellId()}"/></td>
			</c:if>
			<c:if test="${opb.getStatus() == 'good'}">
			<td><input type="checkbox" name="unactivate_checkbox" value="${opb.getSellId()}"/></td>
			</c:if>
		</tr></c:forEach>
		</table>
		<table>
		<tr>
			<td><input type="submit" value="Activate" onclick="activate_sell()"></td>
			<td><input type="submit" value="UnActivate" onclick="unactivate_sell()"></td>
			<td><input type="hidden" name="action" value=""/></td>
		</tr>
	</table>
</form>
<form name="form1">
	<table><tr>
		<%if (pageNum > 0) {%>
		<td><input type="submit" value="Previous" onclick="previous()"/></td>
		<%}%>
		<%if (pageNum < countNum) {%>
		<td><input type="submit" value="Next" onclick="next()"/></td>
		<%}%>
		<td><input type="button" value="Back to Homepage" onclick="back_home()"></td>
		<td><input type="hidden" name="page" value="<%=pagenum%>"/></td>
		<td><input type="hidden" name="source" value="selling"/></td>
	</tr></table>
</form>
<form action="../ControlServlet" method="get">
	<table>
	<tr>
	<td><input type="hidden" name="search_type" value="sell_search"/>
		<input type="hidden" name="action" value="search"/></td>
	<td><input type="text" name="search_value"/></td>
	<td><select name="key">
		<option value="publication_type">publication_type</option>
		<option value="authors">authors</option>
		<option value="title">title</option>
	</select></td>
	<td><input type="submit" value="Search"/></td>
	</tr>
	</table>
</form>
<form action="../ControlServlet" method="get">
	<table><tr><td>
	<input type="submit" value="Create New Item"/>
	<input type="hidden" name="action" value="create"/>
	</td></tr></table>
</form>
<%}%>
</body>
</html>