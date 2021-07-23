<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Должность</title>
</head>
<body>
<h1 style="color: darkblue;">Детальная информация о должности</h1>
<h3>Идентификатор: ${position.id}</h3>
<h3>Название должности: ${position.namePosition}</h3>
<h3>Запрлата: ${position.salary}</h3>

<a href="positionEdit.jsp?id=${position.id}" style="color: blueviolet;">Редактировать</a>

<h2 style="color: darkcyan;">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB/position" style="color: crimson">Список должностей</a>
    <a href="http://localhost:8080/WebDB/index.jsp" style="color: crimson">Главная страница</a>
</div>
</body>
</html>
