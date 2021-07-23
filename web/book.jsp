<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<html>
<head>
    <title>Книга</title>
</head>
<body>
<h1 style="color: darkblue;">Детальная информация о книге</h1>
<h3>Идентификатор: ${book.id}</h3>
<h3>Название книги: ${book.bookName}</h3>
<h3>Автор: ${book.author}</h3>
<h3>Цена: ${book.price}</h3>
<h3>Дата выхода: <fmt:formatDate value="${book.getDate()}" pattern="dd.MM.yyyy"/></h3>

<a href="bookEdit.jsp?id=${book.id}" style="color: blueviolet;">Редактировать</a>

<h2 style="color: darkcyan;">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB/book" style="color: crimson">Список авторов</a>
    <a href="http://localhost:8080/WebDB/index.jsp" style="color: crimson">Главная страница</a>
</div>
</body>
</html>
