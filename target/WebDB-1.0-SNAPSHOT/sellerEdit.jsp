<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>
<h1 style="color: darkblue;">Редактирование информации о сотруднике ${seller.sellerName}</h1>
<form method="post" action="http://localhost:8080/WebDB/seller">
    <input type="hidden" name="id" value="${seller.id}"/>
    <div>
        ФИО сотрудника: <input type="text" name="sellerName" value="${seller.sellerName}"/>
    </div>
    <div>
        Номер телефона: <input type="text" name="phone" value="${seller.phone}"/>
    </div>
    <div>
        Адрес: <input type="text" name="address" value="${seller.address}"/>
    </div>
    <div>
        Пол: <input type="text" name="gender" value="${seller.gender}"/>
    </div>

    <input type="submit" value="Сохранить">
</form>

<h2 style="color: darkcyan;">Навигация</h2>
<div>
    <a href="http://localhost:8080/WebDB/seller" style="color: crimson">Список сотрудников</a>
    <a href="http://localhost:8080/WebDB/index.jsp" style="color: crimson">Главная страница</a>
</div>
</body>
</html>
