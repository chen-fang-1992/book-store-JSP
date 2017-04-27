<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin management</title>
<link rel=stylesheet href="../css/container.css"></link>
<script src="../js/back.js"></script>
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
<h1>Hello ${param.administrator}</h1>
<form>
    <table>
        <tr><td><a href="../admin/item_manage.jsp?page=0">Item management</a></td></tr>
        <tr><td><a href="../admin/customer_manage.jsp?page=0">Customer management</a></td></tr>
        <tr><td><input type = "button" value="Back to Homepage" onclick="back_home()"/></td></tr>
    </table>
</form>
</body>
</html>