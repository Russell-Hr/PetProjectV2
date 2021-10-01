<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
</head>
<body>

<link rel="stylesheet" href="Style/login.css">
<header>
    <div class="container">
        <div class="heading clearfix">
            <form class="logo" name="callback" method="get" action="${pageContext.request.contextPath}/get-city-servlet">
                <input type="hidden" name="address" value="calculate.jsp"/>
                <img src="img/logo.png" alt="send" onclick="document.forms['callback'].submit();" />
            </form>
            <nav>
                <ul class="menu">
                    <li>
                        <a href="index.jsp">Головна</a>
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
    <div class="title">
        <div class="titles__first">
        </div>
        <h1>

        </h1>
    </div>

    <form action="controller" method="post">
    <div class="block_buttons_lines">
        <input type="hidden" name="command" value="login"/></hr>

        <div class="input_lines">
            <div class="field">
                <label for="login">Login: </label>
                <input class="input_line" name="login">
            </div>

            <div class="field">
                <label for="password">Password:</label>
                <input class="input_line" type="password" name="password"/>

            </div>

            <div class="field">
                <input class="button_center" type=submit value="Login"/>
            </div>

        </div>
    </div>
    </form>
    <br/>
</header>
</body>
</html>
