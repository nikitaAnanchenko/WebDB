<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Date" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>
<h1 style="color: darkblue;">Редактирование информации о книге ${book.bookName}</h1>
<form method="post" action="http://localhost:8080/webDB/book">
    <input type="hidden" name="id" value="${book.id}" />
    <label>Название книги</label><br>
    <input type="text" name="bookName" value="${book.bookName}" /><br><br>
    <label>Автор</label><br>
    <input type="text" name="author" value="${book.author}" /><br><br>
    <label>Цена</label><br>
    <input type="number" name="price" value="${book.price}" /><br><br>
    <label>Дата выпуска</label><br>
    <input type="date" name="releaseDate" value="${book.releaseDate}" /><br><br>
    <input type="submit" value="Сохранить" />
</form>

<h2 style="color: darkcyan;">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB/book" style="color: crimson">Список авторов</a>
    <a href="http://localhost:8080/WebDB/index.jsp" style="color: crimson">Главная страница</a>
</div>
</body>
</html>
