<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*,
				java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String pagenum = request.getParameter("page");
int pageNum = Integer.parseInt(pagenum);
String countnum = request.getServletContext().getAttribute("sellsearchbookscount").toString();
int countNum = Integer.parseInt(countnum);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sell Search Results</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
<script src="../js/sell_search_results.js"></script>
</head>
<body>
<center>
<form action="../ControlServlet" method="get">
	<table>
	<tr>
	<td><h1>List of results!</h1></td>
	</tr>
	</table>
</form>
</center>
<form name="form1">
<center>
	<table>
	<tr>
		<td>Title</td>
		<td>Authors</td>
		<td>Editors</td>
	</tr>
	<c:forEach var="book" items="${sellsearchbooks}" begin="<%=pageNum*10%>" end="<%=pageNum*10+9%>"><tr>
		<td><a href="../search/detail_sell.jsp?bookid=${book.bookId}&type=${book.type}&authors=${book.authors}&editors=${book.editors}
		&address=${book.address}&title=${book.title}&booktitle=${book.bookTitle}&pages=${book.pages}
		&year=${book.year}&journal=${book.journal}&volume=${book.volume}&number=${book.number}
		&month=${book.month}&url=${book.url}&ee=${book.ee}&cite=${book.cite}&school=${book.school}
		&publisher=${book.publisher}&note=${book.note}&cdrom=${book.cdrom}&crossref=${book.crossref}
		&isbn=${book.isbn}&chapter=${book.chapter}&series=${book.series}&page=<%=pagenum%>&source=sell_search">${book.title}</a></td>
		<td>${book.authors}</td>
		<td>${book.editors}</td>
	</tr></c:forEach>
	<c:if test="${sellsearchbooks.size()==0}">Sorry, no matching datasets found!</c:if>
	</table>
	<table><tr>
		<%if (pageNum > 0) {%>
		<td><input type="submit" value="Previous" onclick="previous()"/></td>
		<%}%>
		<%if (pageNum < countNum) {%>
		<td><input type="submit" value="Next" onclick="next()"/></td>
		<%}%>
		<td><input type="button" value="Back to Homepage" onclick="back_home()"></td>
		<td><input type="hidden" name="page" value="<%=pagenum%>"/></td>
		<td><input type="hidden" name="source" value="sell_search"/></td>
	</tr></table>
</center>
</form>
</body>
</html>