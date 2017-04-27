<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Change</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
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
<h1>Profile</h1>
<form method="post" action="../ControlServlet">
	<table>
		<tr><td width="400px">Password:</td><td><input type="text" name="password"/></td></tr>
		<tr><td>Email:</td><td><input type="text" name="email"/></td></tr>
		<tr><td>Firstname:</td><td><input type="text" name="firstname"/></td></tr>
		<tr><td>Lastname:</td><td><input type="text" name="lastname"/></td></tr>
		<tr><td>Address:</td><td><input type="text" name="address"/></td></tr>
		<tr><td>Year of birth:</td><td><input type="text" name="birthyear"/></td></tr>
		<tr><td>Credit card number:</td><td><input type="text" name="creditcard"/></td></tr>
		<tr><td><input type="submit" value="Change"/>
		<input type="hidden" name="action" value="change_profile"></td></tr>
	</table>
</form>
<form>
    <input type="button" value="Back to Homepage" onclick="back_home()"/>
</form>
</body>
</html>