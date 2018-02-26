<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        }
    </style>
    <title><fmt:message var="registerTitle" key="register_title" />${registerTitle}</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 text-justify">
                <p><h1 align="center"><fmt:message var="registerHello" key="register_hello" />${registerHello}</h1>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-5"></div>
            <div class="col-md-2">
                <form>
                    <p>
                        <select title="language" class="form-control" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </p>
                </form>
                <form action="MainServlet" method="post">
                    <p>
                        <input type="text" name="login" class="form-control" placeholder="<fmt:message var="enterLgn" key="enter_login" />${enterLgn}" required />
                        <input type="email" name="email" class="form-control" placeholder="<fmt:message var="enterEml" key="enter_email" />${enterEml}l" required />
                        <input type="tel" name="phone" class="form-control" placeholder="8-xxx-xxx-xxxx" minlength="14" maxlength="14" pattern="8{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}" title="7xxx-xxx-xxxx" required />
                        <input type="password" name="password" class="form-control" placeholder="<fmt:message var="enterPswd" key="enter_password" />${enterPswd}" required />
                        <input type="hidden" name="actionCommand" value="registerPage" />
                        <input type="submit" class="btn btn-success btn-md btn-block" value="<fmt:message var="SignUp" key="SignUp" />${SignUp}" />
                    </p>
                </form>
                <form action="/index" method="post">
                    <p>
                        <input type="hidden" name="actionCommand" value="indexPage">
                        <input type="submit" class="btn btn-warning btn-md btn-block" value="<fmt:message var="cancel" key="cancel" />${cancel}">
                    </p>
                </form>
            </div>
            <div class="col-md-5"></div>
        </div>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4"><h3 align="center">${requestScope.fail}</h3></div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>
