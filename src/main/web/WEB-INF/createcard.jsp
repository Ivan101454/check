<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.08.2024
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CREATE</title>
</head>
<body>
<p>Создание новой карты</p>
<form action="/creatediscount" method="post">
    <label for="number">Номер карты
        <input type="text" name="number" id="number">
    </label><br>
    <label for="amount">Размер скидки
        <input type="text" name="amount" id="amount">
    </label><br>
    <button>Создать</button>
</form>
<a href="/discount">Возврат на главную</a>
</body>
</html>
