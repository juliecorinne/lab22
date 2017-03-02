
<%--
  Created by IntelliJ IDEA.
  User: julieschneider
  Date: 3/1/17
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr> <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Quantity</th></tr>
    <c:forEach var="coffeeOrder" items="${coffeeList}">
        <tr>
            <td>${coffeeOrder.name}</td>
            <td>${coffeeOrder.description}</td>
            <td>${coffeeOrder.price}</td>
            <%--<td>${coffeeOrder.quantity}</td>--%>
            <td><input id=${coffeeOrder.quantity} type="submit" value="Add to Order"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
