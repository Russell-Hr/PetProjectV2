<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/calculate.css">
<header>
    <div class="container">
        <div class="heading clearfix">
            <form class="logo" name="callback" method="get"
                  action="${pageContext.request.contextPath}/app/getCity">
                <input type="hidden" name="address" value="/calculate.jsp"/>
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="send" onclick="document.forms['callback'].submit();"/>
            </form>

            <nav>
                <ul class="menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/app/main_jsp">Головна</a>
                    </li>
                    <li>
                        <a href="#">Тарифи</a>
                    </li>
                    <li>
                        <a href="#">Про нас</a>
                    </li>
                    <li>
                        <a href="#">Контакти</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <h3>Заповніть форму:</h3>
    <form action="${pageContext.request.contextPath}/app/registration" method="get"/>
    <div class="block_buttons_lines">
        <!--<input type="hidden" name="command" value="registration"/>-->

        <div class="input_lines">
            <div class="field">
                <label for="name">Ім'я: </label>
                <input class="input_line" type="text" id="name" name="name" pattern="[A-Za-zА-Яа-яЄєІіЇї]{2,20}" required/>
            </div>

            <div class="field">
                <label for="surname">Прізвище:</label>
                <input class="input_line" type="text" id="surname" name="surname" pattern="[A-Za-zА-Яа-яЄєІіЇї]{2,20}"
                       required/>
            </div>

            <div class="input_lines">
                <div class="field">
                    <label for="login">Логін: </label>
                    <input class="input_line" type="text" id="login" name="login" pattern="[A-Za-z]{5,20}" required/>
                </div>

                <div class="field">
                    <label for="password">Пароль:</label>
                    <input class="input_line" type="password" id="password" name="password" pattern="[A-Za-z]{5,20}"
                           required/>
                </div>

                <div class="input_lines">
                    <div class="field">
                        <label for="password2">Підтвердити пароль: </label>
                        <input class="input_line" type="password" id="password2" name="password2"
                               pattern="[A-Za-z]{5,20}" required/>
                    </div>
                    <input class="button_center" type=submit value="Зареєструватись"/>
                </div>
            </div>
        </div>
        </form>
        <br/>
    </div>
    </div>
</header>
</body>
</html>

