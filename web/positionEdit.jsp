<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>
<h1 style="color: darkblue;">Редактирование информации о должности ${position.namePosition}</h1>
<form method="post" action="http://localhost:8080/WebDB/position">
    <input type="hidden" name="id" value="${position.id}"/>
    <div>
        Название должности: <input type="text" name="namePosition" value="${position.namePosition}"/>
    </div>
    <div>
        Заплата: <input type="number" name="salary" value="${position.salary}" />
    </div>
    <input type="submit" value="Сохранить">
</form>

<h2 style="color: darkcyan;">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB/position" style="color: crimson">Список должностей</a>
    <a href="http://localhost:8080/WebDB/index.jsp" style="color: crimson">Главная страница</a>
</div>
</body>
</html>
