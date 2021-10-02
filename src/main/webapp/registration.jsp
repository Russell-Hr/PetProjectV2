<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<link rel="stylesheet" href="Style/calculate.css">
<header>
    <div class="container">
        <div class="heading clearfix">
            <a href="${pageContext.request.contextPath}/calculation-servlet"><img src="img/logo.png" alt="" class="logo"></a>

            <nav>
                <ul class="menu">
                    <li>
                        <a href="main.jsp">Головна</a>
                    </li>
                    <li>
                        <a href="#">Тарифи</a>
                    </li>
                    <li>
                        <a href="about.html">Про нас</a>
                    </li>
                    <li>
                        <a href="contacts.html">Контакти</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <h3>Заповніть форму:</h3>
    <form action="controller" method="get"/>
    <div class="block_buttons_lines">
        <input type="hidden" name="command" value="registration"/>

        <div class="input_lines">
            <div class="field">
                <label for="name">Ім'я: </label>
                <input class="input_line" type="text" id="name" name="name">
            </div>

            <div class="field">
                <label for="surname">Прізвище:</label>
                <input class="input_line" type="text" id="surname" name="surname"/>
            </div>


            <div class="input_lines">
                <div class="field">
                    <label for="login">Логін: </label>
                    <input class="input_line" type="text" id="login" name="login">
                </div>

                <div class="field">
                    <label for="password">Пароль:</label>
                    <input class="input_line" type="password" id="password" name="password"/>
                </div>

                <div class="input_lines">
                    <div class="field">
                        <label for="password2">Підтвердити пароль: </label>
                        <input class="input_line" type="password" id="password2" name="password2">
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

