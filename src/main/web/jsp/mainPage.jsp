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
    <title><fmt:message var="mainTitle" key="main_title" />${mainTitle}</title>
</head>
<body>
    <c:set var="user" value="null" />
    <c:set var="user" value="${cookie['user'].name}" />
    <c:if test="${user == \"null\"}">
        <c:redirect url="/index.jsp" />
    </c:if>
    <div class="container-fluid">
        <div class="row"><h1 align="center"><fmt:message var="mainHello" key="main_hello" />${mainHello}</h1></div>
        <div class="row" align="center">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form>
                    <p>
                        <input type="hidden" name="actionCommand" value="mainPage">
                        <select title="language" class="form-control" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </p>
                </form>
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="menuPage">
                    <input type="submit" value="<fmt:message var="menu" key="menu" />${menu}">
                </form>
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="roomPage">
                    <input type="submit" value="<fmt:message var="account" key="account" />${account}">
                </form>
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="reservePage">
                    <input type="submit" value="<fmt:message var="reserve" key="reserve_table" />${reserve}">
                </form>
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="logoutPage">
                    <input type="submit" value="<fmt:message var="logOut" key="log_out" />${logOut}">
                </form>
                <p><h3>${requestScope.success}</h3>
                <p><h3>${requestScope.fail}</h3>
            </div>
        </div>
    </div>
</body>
</html>
