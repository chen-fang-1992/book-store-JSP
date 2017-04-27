<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String bookid = request.getParameter("bookid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Add Exist Item To Sell</title>
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
<form action="../ControlServlet" method="post" enctype="multipart/form-data">
    <table>
		<tr><td><input type="hidden" name="action" value="do_sell"/></td></tr>
		<tr><td>Price:</td><td><input type="text" name="price"/></td></tr>
		<tr><td>Picture:</td><td><input type="file" name="picture"/></td></tr>
		<tr><td>
			<input type="submit" value="Sell"/>
			<input type="hidden" name="bookid" value="<%=bookid%>"/>
		</td></tr>
	</table>
</form>
</body>
</html>