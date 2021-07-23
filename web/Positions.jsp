<%@ page import="java.util.List" %>
<%@ page import="model.Positions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Таблица Positions</title>
</head>
<body>
<h2 style="color: blue;">Должности</h2>
<table>
    <tr><th>Название должности</th><th>Запрлата</th></tr>
    <c:forEach var="position" items="${requestScope.positions}">
        <tr><td><a href="/WebDB/position?id=${position.id}">${position.namePosition}</a> </td>
            <td>${position.salary}</td></tr>
    </c:forEach>
</table>
<h2 style="color: darkmagenta">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB" style="color: crimson">Главная страница</a>
</div>
</body>
