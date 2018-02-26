<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="language" />
<html lang="${language}">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <title><fmt:message var="dishTitle" key="dish_title" />${dishTitle}</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4" align="center">
                <h1>${dishTitle}</h1>
                <form>
                    <input type="hidden" name="actionCommand" value="menuPage" />
                    <p>
                        <select title="language" class="form-control" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </p>
                </form>
                <p>
                    <b><fmt:message var="addDish" key="add_new_dish" />${addDish}</b>
                </p>
                <fmt:message var="dishName" key="dish_name" />
                <fmt:message var="dishDesc" key="dish_description" />
                <fmt:message var="dishPrice" key="dish_price" />
                <form action="MainServlet" method="post" class="form-control">
                    <input type="text" name="dishName" value="" placeholder="${dishName}"><br>
                    <input type="text" name="dishDesc" value="" placeholder="${dishDesc}"><br>
                    <input type="number" name="dishPrice" value="" placeholder="${dishPrice}"><br>
                    <input type="hidden" name="actionCommand" value="addDish">
                    <input type="submit" value="${addDish}">
                </form>
                <p>
                    <fmt:message var="updateDish" key="update_dish" />
                    <b>${updateDish}</b>
                </p>
                <form action="MainServlet" method="post" class="form-control">
                    <select name="dish" title="dish" size="1">
                        <c:forEach var="dish" items="${requestScope.dishes}">
                            <option value="${dish.getName()}">${dish.getName()}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="actionCommand" value="updateDish" />
                    <input type="submit" value="${updateDish}">
                </form>
                <p>
                    <fmt:message var="deleteDish" key="delete_dish" />
                    <b>${deleteDish}</b>
                </p>
                <form action="MainServlet" method="post" class="form-control">
                    <select name="dish" title="dish" size="1">
                        <c:forEach var="dish" items="${requestScope.dishes}">
                            <option value="${dish.getId()}">${dish.getName()}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="actionCommand" value="deleteDish" />
                    <input type="submit" value="${deleteDish}" />
                </form>
                <form action="/adminPage" method="post">
                    <fmt:message var="back" key="back" />
                    <input type="submit" value="${back}">
                </form>
                <h1>${requestScope.message}</h1>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>
