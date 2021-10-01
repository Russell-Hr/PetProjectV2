<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Main</title>
</head>
<body>

<link rel="stylesheet" href="Style/main.css">
<header>
    <div class="container">
        <div class="heading clearfix">
            <form class="logo" name="callback" method="get" action="${pageContext.request.contextPath}/get-city-servlet">
                <input type="hidden" name="address" value="calculate.jsp"/>
                <img src="img/logo.png" alt="send" onclick="document.forms['callback'].submit();" />
            </form>

            <nav>
                <ul class="menu">
                    <li class="active">
                        <a href="#">Головна</a>
                    </li>
                    <li>
                        <a href="#">Тарифи</a>
                    </li>
                    <li>
                        <a href="about.html">Про нас</a>
                    </li>
                    <li>
                        <a href="#">Контакти</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="title">
        <div class="titles__first">
            Український сервіс доставки
        </div>
        <h1>
            Вантаж+
        </h1>
    </div>
    <div class="buttons">
        <input class="button_left" type="button" name="conToDB" value="Зареєструватись"
               onClick='location.href="registration.jsp"'>
        <input class="button_right" type="button" name="conToDB" value="Увійти" onClick='location.href="login.jsp"'>
    </div>
</header>
</body>
</html>