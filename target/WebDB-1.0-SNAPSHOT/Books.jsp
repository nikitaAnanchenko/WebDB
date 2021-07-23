<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="model.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Таблица Book</title>
</head>
<body>
<h2 style="color: blue;">Книги</h2>
<table>
    <tr><th>Название книги</th><th>Цена</th><th>Дата выпуска</th></tr>
    <c:forEach var="book" items="${requestScope.books}">
        <tr><td><a href="/WebDB/book?id=${book.id}">${book.bookName}</a> </td>
            <td>${book.price}</td>
            <td><fmt:formatDate value="${book.getDate()}" pattern="dd.MM.yyyy"/></td></tr>
    </c:forEach>
</table>
<h2 style="color: darkmagenta">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB" style="color: crimson">Главная страница</a>
</div>
</body>
</html>

