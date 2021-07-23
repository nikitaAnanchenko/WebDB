<%@ page import="java.util.List" %>
<%@ page import="model.Seller" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Таблица Seller</title>
</head>
<body>
<h2 style="color: blue;">Сотрудники</h2>
<table>
    <tr><th>ФИО продавца</th><th>Номер телефона</th><th>Пол</th></tr>
    <c:forEach var="seller" items="${requestScope.sellers}">
        <tr><td><a href="/WebDB/seller?id=${seller.id}">${seller.sellerName}</a> </td>
            <td>${seller.phone}</td>
            <td>${seller.gender}</td></tr>
    </c:forEach>
</table>
<h2 style="color: darkmagenta">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB" style="color: crimson">Главная страница</a>
</div>
</body>
</html>

