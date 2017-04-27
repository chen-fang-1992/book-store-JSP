<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*"%>
<%
HttpServletRequest req = (HttpServletRequest) request;
req.getSession().setAttribute("url",req.getRequestURI()+"?"+req.getQueryString());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel=stylesheet href="css/header.css"></link>
<script src="js/header.js"></script>
<script src="js/login.js"></script>
</head>
<body>
<%
if (session.getAttribute("customerbean") != null) {
	CustomerBean cb = (CustomerBean) session.getAttribute("customerbean");
	String username = cb.getName();
%>
<table align="right">
    <tr><td>
        <a onMouseOver="show_activity()"><font>Hello <%=username %></font></a>
    </td></tr>
    <tr><td>
        <div id="divActivity" style="display:none;" onMouseOver="show_activity()" onMouseOut="hide_activity()">
            <center><div>
                <table>
					<tr><td>login time:</td><td>${logintime}</td></tr>
					<tr>
						<td>
							<form action="ControlServlet" method="get">
							<input type="submit" value="Shopping Cart"/>
							<input type="hidden" name="action" value="shopping_cart"/>
							</form>
						</td>
						<td>
							<form action="ControlServlet" method="get">
							<input type="submit" value="Show Selling"/>
							<input type="hidden" name="action" value="selling"/>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<form action="ControlServlet" method="post">
							<input type="submit" value="Check Profile"/>
							<input type="hidden" name="action" value="profile"/>
							</form>
						</td>
						<td>
							<form action="ControlServlet" method="post">
							<input type="submit" value="Log out"/>
							<input type="hidden" name="action" value="log_out"/>
							</form>
						</td>
					</tr>
				</table>
			</div></center>
		</div>
    </td></tr>
</table>
<%} else {%>
<form name="loginForm" action="ControlServlet" method="post">
    <table align="right">
        <tr><td>
            <a href="#" onMouseOver="show_login()"><font>UserLogin</font></a>
        </td></tr>
        <tr><td>
            <div id="divLogin" style="display:none;" onMouseOver="show_login()" onMouseOut="hide_login()">
				<center><div>
					<table>
						<tr>
							<td>Username:</td>
							<td><input type="text" id="text" name="username"/></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" id="text" name="password"/></td>
						</tr>
						<tr>
							<td><input type="button" value="Login" onclick="check_login()"></td>
							<td><input type="button" value="Cancel" onclick="hide_login()"/></td>
							<td><input type="hidden" name="action" value="login"/></td>
						</tr>
					</table>
                </div></center>
            </div>
        </td></tr>
    </table>
</form>
<form name="adminForm" action="ControlServlet" method="post">
	<table align="right">
        <tr><td>
            <a href="#" onMouseOver="show_admin()"><font>AdminLogin</font></a>
        </td></tr>
        <tr><td>
			<div id="divAdmin" style="display:none;" onMouseOver="show_admin()" onMouseOut="hide_admin()">
				<center><div>
					<table>
						<tr>
							<td>Admin:</td>
							<td><input type="text" id="text" name="administrator"/></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="text" id="text" name="password"/></td>
						</tr>
						<tr>
                            <td><input type="button" value="AdminLogin" onclick="check_admin()"></td>
                            <td><input type="button" value="Cancel" onclick="hide_admin()"/></td>
							<td><input type="hidden" name="action" value="adminlogin"/></td>
						</tr>
					</table>
				</div></center>
			</div>
        </td></tr>
    </table>
</form>
<form><table align="right"><tr><td>
	<a href="user/register.jsp"><font>Register</font></a>
</td></tr></table></form>
<%}%>
</body>