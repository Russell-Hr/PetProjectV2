<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Main</title>
</head>
<body>
<fmt:setLocale value="${lang}"/>
<fmt:bundle basename="header">
    <link rel="stylesheet" href="Style/main.css">
    <header>
        <div class="containerr">
            <div class="heading clearfix">
                <form class="logo" name="callback" method="get"
                      action="${pageContext.request.contextPath}/get-city-servlet">
                    <input type="hidden" name="address" value="calculate.jsp"/>
                    <img src="img/logo.png" alt="send" onclick="document.forms['callback'].submit();"/>
                </form>

                <nav>
                    <ul class="menu">
                        <li class="active">
                            <a href="#"><fmt:message key="msg.main"/></a>
                        </li>
                        <li>
                            <a href="#"><fmt:message key="msg.tariffs"/></a>
                        </li>
                        <li>
                            <a href="#"><fmt:message key="msg.about-us"/></a>
                        </li>
                        <li>
                            <a href="#"><fmt:message key="msg.contacts"/></a>
                        </li>
                    </ul>
                </nav>

            </div>
            <br>
            <div style="padding-left: 30px;">

                <form class="form" action="controller" method="get" name="frm2"/>
                <input type="hidden" name="command" value="setLang"/>
                <div class="btn-group btn-group-sm">
                    <button type="submit" class="btn btn-secondary" name="lang" value="ukr">Укр</button>
                    <button type="submit" class="btn btn-secondary" name="lang" value="eng">Eng</button>
                </div>
                </form>
            </div>
        </div>

        <div class="title">
            <div class="titles__first">
                <fmt:message key="msg.title1"/>
            </div>
            <h1>
                <fmt:message key="msg.title2"/>
            </h1>
        </div>
        <div class="buttons">
            <input class="button_left" type="button" name="conToDB" value="<fmt:message key="msg.registration"/>"
                   onClick='location.href="registration.jsp"'>
            <input class="button_right" type="button" name="conToDB" value="<fmt:message key="msg.enter"/>"
                   onClick='location.href="login.jsp"'>
        </div>
    </header>
</fmt:bundle>
</body>
</html>