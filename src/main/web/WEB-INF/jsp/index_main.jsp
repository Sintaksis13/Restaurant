<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
Hello and welcome
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

<!-- TODO add parsing of Dish object from DishController-->
</body>
</html>
