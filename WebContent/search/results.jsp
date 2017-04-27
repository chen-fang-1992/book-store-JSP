<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*,
				java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String pagenum = request.getParameter("page");
int pageNum = Integer.parseInt(pagenum);
String countnum = request.getServletContext().getAttribute("showbookscount").toString();
int countNum = Integer.parseInt(countnum);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
<script src="../js/results.js"></script>
</head>
<body>
<div class="container">
	<div class="header">
		<%@ include file="../header.jsp"%>
	</div>
	<div class="content">
		<table align="center"><tr><td>
			<h1>List of results</h1>
		</td></tr></table>
		<center>
			<table class="tableFormat">
			<tr>
				<td>Title</td>
				<td>Picture</td>
				<td>Authors</td>
				<td>Price</td>
				<td>Action</td>
			</tr>
			<c:forEach var="book" items="${showbooks}" begin="<%=pageNum*10%>" end="<%=pageNum*10+9%>"><tr>
				<td><a href="../search/detail.jsp?bookid=${book.bookId}&type=${book.type}&authors=${book.authors}&editors=${book.editors}
				&address=${book.address}&title=${book.title}&booktitle=${book.bookTitle}&pages=${book.pages}
				&year=${book.year}&journal=${book.journal}&volume=${book.volume}&number=${book.number}
				&month=${book.month}&url=${book.url}&ee=${book.ee}&cite=${book.cite}&school=${book.school}
				&publisher=${book.publisher}&note=${book.note}&cdrom=${book.cdrom}&crossref=${book.crossref}
				&isbn=${book.isbn}&chapter=${book.chapter}&series=${book.series}&sid=${book.sid}&page=<%=pagenum%>
				&source=results">${book.title}</a></td>
				<td><img src="../${book.picture}" width="80px" height="60px" alt="no picture"></td>
				<td>${book.authors}</td>
				<td>${book.price}</td>
				<td><form action="../ControlServlet" method="get">
				<input type="submit" value="Add to Cart"/>
				<input type="hidden" name="sid" value="${book.sid}"/>
				<input type="hidden" name="action" value="add_cart"/>
				</form></td>
			</tr></c:forEach>
			<c:if test="${showbooks.size()==0}">Sorry, no matching datasets found!</c:if>
			</table>
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
			</tr></table>
			</form>
		</center>
	</div>
</div>
</body>
</html>