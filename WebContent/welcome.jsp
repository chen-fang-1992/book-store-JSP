<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.book.store.*,
        java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link rel=stylesheet href="css/container.css">
<script src="js/welcome.js"></script>
</head>
<body>
<div class="container">
    <div class="header">
        <%@ include file="header.jsp"%>
    </div>
    <div class="content">
        <table align="center"><tr>
            <td><img src="pic/unsw.png" width="200px" height="80px"></td>
            <td><h1>Digital Bibliographic Store</h1></td>
        </tr></table>
        <table align="center"><tr>
            <td><form action="ControlServlet" method="get">
                <table><tr>
                    <td><input type="hidden" name="search_type" value="welcome_search"/></td>
                    <td><input type="text" name="search_value"/></td>
                    <td><select name="key">
                        <option value="publication_type">publication_type</option>
                        <option value="authors">authors</option>
                        <option value="title">title</option>
                    </select></td>
                    <td><input type="submit" value="Search"/></td>
                    <td><input type="hidden" name="action" value="search"/></td>
                </tr></table>
            </form></td>
            <td>
                <form action="ControlServlet" method="get">
                    <input type="hidden" name="action" value="advanced_search"/>
                    <input type="submit" value="Advanced_Search"/>
                </form>
            </td>
        </tr></table>
        <center>
        <form action="ControlServlet" method="get">
            <table><tr>
                <td><h1>List of titles of some books</h1></td>
                <td><input type="submit" value="See_More"/>
                    <input type="hidden" name="action" value="random"/></td>
            </tr></table>
            <table class="tableWelcome">
                <tr>
                    <td>Title</td>
                    <td>Type</td>
                    <td>Author</td>
                </tr>
                <c:forEach var="book" items="${randbooks}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.type}</td>
                    <td>${book.authors}</td>
                </tr>       
                </c:forEach>
            </table>
        </form>
        </center>
    </div>
    <div class="footer"></div>
</div>
</body>
</html>