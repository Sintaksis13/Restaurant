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
    <title><fmt:message var="chngTitle" key="change_title" />${chngTitle}</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <p>
                    <fmt:message var="changeHello" key="change_hello" />${changeHello}
                </p>
                <form>
                    <p>
                        <input type="hidden" name="actionCommand" value="changeAccount">
                        <select title="language" class="form-control" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </p>
                </form>
                <form action="MainServlet" method="post">
                    <p>
                        <input title="email" type="email" name="email" value="${requestScope.user.getEmail()}">
                        Email
                    </p>
                    <p>
                        <input title="phone" type="tel" name="phone" value="${requestScope.user.getPhoneNumber()}">
                        <fmt:message var="phone" key="user_phone" />${phone}
                    </p>
                    <p>
                        <input title="old_password" type="password" name="oldpassword" value="">
                        <fmt:message var="oldPswd" key="old_password" />${oldPswd}
                    </p>
                    <p>
                        <input title="new_password" type="password" name="newpassword" value="">
                        <fmt:message var="newPswd" key="new_password" />${newPswd}
                    </p>
                    <input type="hidden" name="actionCommand" value="acceptChanges">
                    <input type="submit" value="<fmt:message var="changeData" key="change_data" />${changeData}">
                </form>
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="roomPage">
                    <input type="submit" value="<fmt:message var="cancel" key="cancel" />${cancel}">
                </form>
                <p><h3>${requestScope.fail}</h3>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>
