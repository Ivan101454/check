<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.08.2024
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <span>СКИДОЧНАЯ КАРТА</span>
    <p>Номер карты: ${requestScope.discount.getNumberOfDiscountCard()}</p>
    <p>Размер скидки карты: ${requestScope.discount.getDiscountAmount()}</p>
</div>
</body>
</html>
