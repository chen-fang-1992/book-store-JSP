<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Result</title>
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
<h1>${param.state}</h1>
<input type="button" value="Back to Homepage" onclick="back_home()"/>
</body>
</html>