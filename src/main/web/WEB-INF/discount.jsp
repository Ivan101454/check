
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Введите номер карты</p>
<form action="/discount" method="post">
    <label for="name">
        <input type="text" name="number" id="name">
    </label><br>
    <button type="submit">Send</button>
</form>
<div>
    <span>СКИДОЧНАЯ КАРТА</span>
    <p>Номер карты: ${requestScope.discount.getNumberOfDiscountCard()}</p>
    <p>Размер скидки карты: ${requestScope.discount.getDiscountAmount()} %</p>
</div>
<p>Создание новой карты</p>
<p><a href="/creatediscount">СОЗДАТЬ</a></p>
</body>
</html>
