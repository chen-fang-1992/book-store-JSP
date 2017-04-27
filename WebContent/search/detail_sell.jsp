<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String pagenum = request.getParameter("page");
int pageNum = Integer.parseInt(pagenum);
String source = request.getParameter("source");

String bookid = request.getParameter("bookid");
String type = request.getParameter("type");
String authors = request.getParameter("authors");
String editors = request.getParameter("editors");
String address = request.getParameter("address");
String title = request.getParameter("title");
String booktitle = request.getParameter("booktitle");
String pages = request.getParameter("pages");
String year = request.getParameter("year");
String journal = request.getParameter("journal");
String volume = request.getParameter("volume");
String number = request.getParameter("number");
String month = request.getParameter("month");
String url = request.getParameter("url");
String ee = request.getParameter("ee");
String cite = request.getParameter("cite");
String school = request.getParameter("school");
String publisher = request.getParameter("publisher");
String note = request.getParameter("note");
String cdrom = request.getParameter("cdrom");
String crossref = request.getParameter("crossref");
String isbn = request.getParameter("isbn");
String chapter = request.getParameter("chapter");
String series = request.getParameter("series");
String sid = request.getParameter("sid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail Sell</title>
<link rel=stylesheet href="../css/container.css"></link>
</head>
<body>
<center><table><tr><td><h1>Details</h1></td></tr></table></center>
<form action="../ControlServlet" method="post">
	<table>
	<%if (!type.equals("")){%><tr><td width="400px">publication-type or venues:</td><td><%out.println(type);}%></td></tr>
	<%if (!authors.equals("")){%><tr><td>authors:</td><td><%out.println(authors);}%></td></tr>
	<%if (!editors.equals("")){%><tr><td>editor:</td><td><%out.println(editors);}%></td></tr>
	<%if (!address.equals("")){%><tr><td>address:</td><td><%out.println(address);}%></td></tr>
	<%if (!title.equals("")){%><tr><td>title:</td><td><%out.println(title);}%></td></tr>
	<%if (!booktitle.equals("")){%><tr><td>booktitle:</td><td><%out.println(booktitle);}%></td></tr>
	<%if (!pages.equals("")){%><tr><td>pages:</td><td><%out.println(pages);}%></td></tr>
	<%if (!year.equals("")){%><tr><td>year:</td><td><%out.println(year);}%></td></tr>
	<%if (!journal.equals("")){%><tr><td>journal:</td><td><%out.println(journal);}%></td></tr>
	<%if (!volume.equals("")){%><tr><td>volume:</td><td><%out.println(volume);}%></td></tr>
	<%if (!number.equals("")){%><tr><td>number:</td><td><%out.println(number);}%></td></tr>
	<%if (!month.equals("")){%><tr><td>month:</td><td><%out.println(month);}%></td></tr>
	<%if (!url.equals("")){%><tr><td>url:</td><td><%out.println(url);}%></td></tr>
	<%if (!ee.equals("")){%><tr><td>ee:</td><td><%out.println(ee);}%></td></tr>
	<%if (!cite.equals("")){%><tr><td>cite:</td><td><%out.println(cite);}%></td></tr>
	<%if (!school.equals("")){%><tr><td>school:</td><td><%out.println(school);}%></td></tr>
	<%if (!publisher.equals("")){%><tr><td>publisher:</td><td><%out.println(publisher);}%></td></tr>
	<%if (!note.equals("")){%><tr><td>note:</td><td><%out.println(note);}%></td></tr>
	<%if (!cdrom.equals("")){%><tr><td>cdrom:</td><td><%out.println(cdrom);}%></td></tr>
	<%if (!crossref.equals("")){%><tr><td>crossref:</td><td><%out.println(crossref);}%></td></tr>
	<%if (!isbn.equals("")){%><tr><td>isbn:</td><td><%out.println(isbn);}%></td></tr>
	<%if (!chapter.equals("")){%><tr><td>chapter:</td><td><%out.println(chapter);}%></td></tr>
	<%if (!series.equals("")){%><tr><td>series:</td><td><%out.println(series);}%></td></tr>
	</table>
</form>
<center>
<form action="../ControlServlet" method="get">
	<input type="submit" value="Add to Sell"/>
	<input type="hidden" name="bookid" value="<%=bookid%>"/>
	<input type="hidden" name="action" value="add_sell"/>
</form>
<%if(source.equals("selling")){ %>
<form action="../user/selling.jsp">
	<input type="submit" value="Back"/>
	<input type="hidden" name="page" value="<%=pageNum%>"/>
</form>
<%} else if(source.equals("sell_search")){%>
<form action="../search/sell_search_results.jsp">
	<input type="submit" value="Back"/>
	<input type="hidden" name="page" value="<%=pageNum%>"/>
</form>
<% }%>
</center>
</body>
</html>