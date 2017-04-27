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
Object OB = request.getServletContext().getAttribute("adminbookscount");
if(OB != null){
	String countnum = OB.toString();
	countNum = Integer.parseInt(countnum);}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Management</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/item_manage.js"></script>
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
	<td><input type="hidden" name="search_type" value="admin_search"/>
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

<form>
	
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
		<td>Title</td>
		<td>Picture</td>
		<td>Authors</td>
		<td>Price</td>
		<td>Status</td>
		<td>Sid</td>
	</tr>
	<c:forEach var="book" items="${adminbooks}" begin="<%=pageNum*10%>" end="<%=pageNum*10+9%>"><tr>
		<td><a href="../search/detail.jsp?bookid=${book.bookId}&type=${book.type}&authors=${book.authors}&editors=${book.editors}
		&address=${book.address}&title=${book.title}&booktitle=${book.bookTitle}&pages=${book.pages}
		&year=${book.year}&journal=${book.journal}&volume=${book.volume}&number=${book.number}
		&month=${book.month}&url=${book.url}&ee=${book.ee}&cite=${book.cite}&school=${book.school}
		&publisher=${book.publisher}&note=${book.note}&cdrom=${book.cdrom}&crossref=${book.crossref}
		&isbn=${book.isbn}&chapter=${book.chapter}&series=${book.series}&sid=${book.sid}&page=<%=pagenum%>&source=itemmanage">${book.title}</a></td>
		<td><img src="../${book.picture}" width="80px" height="60px" alt="no picture"></td>
		<td>${book.authors}</td>
		<td>${book.price}</td>
		<td>${book.status}</td>
		<td>${book.sid}</td>
		<c:if test="${book.status != 'good'}">
		<td><input type="checkbox" name="recovery_checkbox" value="${book.sid}"/></td>
		</c:if>
		<c:if test="${book.status == 'good'}">
		<td><input type="checkbox" name="remove_checkbox" value="${book.sid}"/></td>
		</c:if>
	</tr></c:forEach>
	<c:if test="${adminbooks.size()==0}">Sorry, no matching datasets found!</c:if>
	
<tr>
	<td><input type="submit" value="Remove" onclick="remove()"></td>
	<td><input type="submit" value="Recovery" onclick="recovery()"></td>
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