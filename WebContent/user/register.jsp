<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
<script src="../js/register.js"></script>
<script>
function state() {
	var state = "<%=session.getAttribute("state")%>";
	if (state != "null") {
		alert(state);
	}
}
</script>
<%request.getSession().removeAttribute("state"); %>
</head>
<body onload="state()">
<form name="registerForm" method="post" action="../ControlServlet">
    <table>
        <tr><td><font>Username:</font></td>
            <td><input type="text" name="username"/></td></tr>
        <tr><td><font>Password:</font></td>
            <td><input type="text" name="password"/></td></tr>
        <tr><td><font>Retype the Password:</font></td>
            <td><input type="text" name="retype"/></td></tr>
        <tr><td><font>Email:</font></td>
            <td><input type="text" name="email"/></td></tr>
        <tr><td><font>Firstname:</font></td>
            <td><input type="text" name="firstname"/></td></tr>
        <tr><td><font>Lastname:</font></td>
            <td><input type="text" name="lastname"/></td></tr>
        <tr><td><font>Address:</font></td>
            <td><input type="text" name="address"/></td></tr>
        <tr><td><font>Year of Birth:</font></td>
            <td><input type="text" name="birthyear"/></td></tr>
        <tr><td><font>credit card number:</font></td>
            <td><input type="text" name="creditcard"/></td></tr>
    </table>
    <table>
        <tr><td><input type="button" value="Register" onclick="register()"/></td>
            <td><input type="button" value="Back to Homepage" onclick="back_home()"/></td>
            <td><input type="hidden" name="action" value="register"></td></tr>
    </table>
</form>
</body>
</html>