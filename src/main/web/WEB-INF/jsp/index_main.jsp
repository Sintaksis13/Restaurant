<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<spring:message code="index_hello" />
<form action="<c:url value="/addDish"/>" method="post">
    <label>
        Dish name:
        <input type="text" name="dishName" id="dishName">
    </label>
    <label>
        Dish description:
        <input type="text" name="dishDescription" id="dishDescr">
    </label>
    <label>
        Dish price:
        <input type="number" name="dishPrice" id="dishPrice" >
    </label>
    <input type="submit" value="Add dish">
</form>

<c:forEach var="dish" items="${requestScope.dishes}">
    <div class="col-md-4">
        Name = ${dish.name}
    </div>
    <div class="col-md-4">
        Description = ${dish.description}
    </div>
    <div class="col-md-4">
        Price = ${dish.price}
    </div>
</c:forEach>
</body>
</html>
