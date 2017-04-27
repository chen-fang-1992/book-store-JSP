<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
<script src="../js/login.js"></script>
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
<h1>Admin login</h1>
<form name="adminForm" action="../ControlServlet" method="post">
    <table>
        <tr><td><font>Administrator:</font></td>
            <td><input type="text" name="administrator"/></td></tr>
        <tr><td><font>Password:</font></td>
            <td><input type="text" name="password"/></td></tr>
    </table>
    <table>
        <tr><td><input type="button" value="AdminLogin" onclick="check_admin()"/></td>
            <td><input type="button" value="Back to Homepage" onclick="back_home()"/></td>
            <td><input type="hidden" name="action" value="adminlogin"/></td></tr>
    </table>
</form>
</body>
</html>