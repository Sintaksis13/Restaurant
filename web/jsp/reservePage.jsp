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
    <title> <fmt:message var="reserveTitle" key="reserve_title" />${reserveTitle}</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <p><h1> <fmt:message var="reserveHello" key="reserve_hello" />${reserveHello}</h1>
                <form>
                    <p>
                        <input type="hidden" name="actionCommand" value="reservePage">
                        <select title="language" class="form-control" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </p>
                </form>
                <form action="MainServlet" method="post">
                    <p>
                        <fmt:message var="seatNumber" key="seat_number" />${seatNumber}
                        <select name="seatsNumber" class="form-control" size="1" title="seatsNumber">
                            <option value="2" selected>2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </p>
                    <p>
                        <fmt:message var="resTime" key="reserve_time" />${resTime}
                        <select name="time" class="form-control" size="1" title="time">
                            <option value="18" selected>18:00</option>
                            <option value="19">19:00</option>
                            <option value="20">20:00</option>
                            <option value="21">21:00</option>
                            <option value="22">22:00</option>
                            <option value="23">23:00</option>
                            <option value="24">24:00</option>
                            <option value="1">01:00</option>
                            <option value="2">02:00</option>
                        </select>
                    </p>
                    <p>
                        <input type="radio" name="tableValue" value="common" checked title="valueType">
                        <fmt:message var="comTable" key="common_table" />${comTable}<br>
                        <input type="radio" name="tableValue" value="vip" title=" <fmt:message var="attent" key="reserve_attention" />${attent}">
                        <fmt:message var="vipTable" key="vip_table" />${vipTable}
                    </p>
                    <input type="hidden" name="actionCommand" value="acceptReserve">
                    <input type="submit" value=" <fmt:message var="reserveTable" key="reserve_table" />${reserveTable}">
                </form>
                <form action="MainServlet" method="post">
                    <input type="hidden" name="actionCommand" value="roomPage" />
                    <input type="submit" class="btn" value="<fmt:message var="back" key="back" />${back}" />
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</body>
</html>
