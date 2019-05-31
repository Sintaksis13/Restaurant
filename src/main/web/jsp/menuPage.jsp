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
    <style>
        body {
            margin: 30px;
            background: url("/resource/bgimg.jpg");
            background-size: 100%;
        }
    </style>
    <title><fmt:message var="menuTitle" key="menu_title" />${menuTitle}</title>
</head>
<link href="resource/css/menu.css" rel="stylesheet" type="text/css" />


<body>

    <form>
        <p>
            <input type="hidden" name="actionCommand" value="menuPage">
            <select title="language" class="form-control" id="language" name="language" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            </select>
        </p>
    </form>
    <div class="container-fluid">
        <div class="row" align="center">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h1><fmt:message var="menuHello" key="menu_hello" />${menuHello}</h1>
            </div>
            <div class="col-md-2"></div>
            <div class="col-md-1">
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="mainPage">
                    <input type="submit" value="<fmt:message var="back" key="back" />${back}">
                </form>
            </div>
        </div>
        <div>
            <div class="row" align="center">
                <c:forEach var="dish" items="${requestScope.dishes}">
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading"><p>${dish.getName()}</p></div>
                            <div class="panel-body"><p style="min-height: 50px; max-height: 50px">${dish.getDescription()}</p></div>
                            <div class="panel-footer"><p>${dish.getPrice()} T</p></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>




