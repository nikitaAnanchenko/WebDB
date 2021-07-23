<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Сотрудник</title>
</head>
<body>
<h1 style="color: darkblue;">Детальная информация о сотруднике</h1>
<h3>Идентификатор: ${seller.id}</h3>
<h3>ФИО сотрудника: ${seller.sellerName}</h3>
<h3>Телефон: ${seller.phone}</h3>
<h3>Адрес: ${seller.address}</h3>
<h3>Пол: ${seller.gender}</h3>

<a href="sellerEdit.jsp?id=${seller.id}" style="color: blueviolet;">Редактировать</a>

<h2 style="color: darkcyan;">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB/seller" style="color: crimson">Список сотрудников</a>
    <a href="http://localhost:8080/WebDB/index.jsp" style="color: crimson">Главная страница</a>
</div>
</body>
</html>
