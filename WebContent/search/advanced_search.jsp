<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advanced Search</title>
<link rel=stylesheet href="../css/container.css">
<script src="../js/back.js"></script>
</head>
<body>
<center>
<form action="../ControlServlet" method="get">
	<table><tr>
		<td><h1>Advanced Search</h1></td>
		<td><input type="submit" value="Shopping Cart"/></td>
		<td><input type="hidden" name="action" value="shopping_cart"/></td>
	</tr></table>
</form>
</center>
<form action="../ControlServlet" method="get">
	<table>
		<tr><td><input type="hidden" name="search_type" value="advanced_search"/>
				<input type="hidden" name="action" value="search"/></td></tr>
		<tr><td>Publication-type or venues:</td><td><input type="text" name="Publication-type"/></td></tr>
		<tr><td>Authors:</td><td><input type="text" name="Authors"/></td></tr>
		<tr><td>Editors:</td><td><input type="text" name="Editors"/></td></tr>
		<tr><td>Title:</td><td><input type="text" name="Title"/></td></tr>
		<tr><td>Year:</td><td><input type="text" name="Year"/></td></tr>
		<tr><td>Journal:</td><td><input type="text" name="Journal"/></td></tr>
		<tr><td>Isbn:</td><td><input type="text" name="Isbn"/></td></tr>
		</table>
		<table>
		<tr><td><input type="submit" value="Search"/></td>
		    <td><input type="button" value="Back to Homepage" onclick="back_home()"></td></tr>
	</table>
</form>
</body>
</html>