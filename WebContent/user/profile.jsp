<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
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
<table>
	<tr><td width="400px">Username:</td><td>${customerbean.getName()}</td></tr>
	<tr><td>Email:</td><td>${customerbean.getEmail()}</td></tr>
	<tr><td>Firstname:</td><td>${customerbean.getFirstname()}</td></tr>
	<tr><td>Lastname:</td><td>${customerbean.getLastname()}</td></tr>
	<tr><td>Address:</td><td>${customerbean.getAddress()}</td></tr>
	<tr><td>Year of birth:</td><td>${customerbean.getBirthyear()}</td></tr>
	<tr><td>Credit card number:</td><td>${customerbean.getCredit()}</td></tr>
</table>
<form action="../user/profile_change.jsp">
	<input type = "submit" value="Change"/>
	<input type = "hidden" name="action" value="change_profile">
</form>
<form>
    <input type="button" value="Back to Homepage" onclick="back_home()"/>
</form>
</body>
</html>