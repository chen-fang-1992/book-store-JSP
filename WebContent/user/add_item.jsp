<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Item</title>
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
<center>
	<form action="../ControlServlet" method="get">
		<table><tr>
			<td><h1>Add new item</h1></td>
		</tr></table>
	</form>
</center>

<form action="../ControlServlet" method="post" enctype="multipart/form-data">
	<table>
		<tr><td>Publication-type or venues:</td><td><input type="text" name="Publication-type"/></td></tr>
		<tr><td>Authors:</td><td><input type="text" name="Authors"/></td></tr>
		<tr><td>Editors:</td><td><input type="text" name="Editors"/></td></tr>
		<tr><td>Address:</td><td><input type="text" name="Address"/></td></tr>
		<tr><td>Title:</td><td><input type="text" name="Title"/></td></tr>
		<tr><td>Booktitle:</td><td><input type="text" name="Booktitle"/></td></tr>
		<tr><td>Pages:</td><td><input type="text" name="Pages"/></td></tr>
		<tr><td>Year:</td><td><input type="text" name="Year"/></td></tr>
		<tr><td>Journal:</td><td><input type="text" name="Journal"/></td></tr>
		<tr><td>Volume:</td><td><input type="text" name="Volume"/></td></tr>
		<tr><td>Number:</td><td><input type="text" name="Number"/></td></tr>
		<tr><td>Month:</td><td><input type="text" name="Month"/></td></tr>
		<tr><td>Url:</td><td><input type="text" name="Url"/></td></tr>
		<tr><td>Ee:</td><td><input type="text" name="Ee"/></td></tr>
		<tr><td>Cite:</td><td><input type="text" name="Cite"/></td></tr>
		<tr><td>School:</td><td><input type="text" name="School"/></td></tr>
		<tr><td>Publisher:</td><td><input type="text" name="Publisher"/></td></tr>
		<tr><td>Note:</td><td><input type="text" name="Note"/></td></tr>
		<tr><td>Cdrom:</td><td><input type="text" name="Cdrom"/></td></tr>
		<tr><td>Crossref:</td><td><input type="text" name="Crossref"/></td></tr>
		<tr><td>Isbn:</td><td><input type="text" name="Isbn"/></td></tr>
		<tr><td>Chapter:</td><td><input type="text" name="Chapter"/></td></tr>
		<tr><td>Series:</td><td><input type="text" name="Series"/></td></tr>
		<tr><td>Price:</td><td><input type="text" name="price"/></td></tr>
		<tr><td>Picture:</td><td><input type="file" name="picture"/></td></tr>
    </table>
    <table>
		<tr><td><input type="hidden" name="action" value="add_new_item"/></td></tr>
		<tr><td><input type="submit" value="Add New Item"/></td></tr>
	</table>
</form>
</body>
</html>