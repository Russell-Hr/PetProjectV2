<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
</head>
<body>
<fmt:setLocale value="${lang}"/>
<fmt:bundle basename="header">
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
                        <a href="main.jsp"><fmt:message key="msg.main"/></a>
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
                <label for="login"><fmt:message key="msg.login"/>: </label>
                <input class="input_line" id="login" name="login" pattern="[A-Za-z]{5,20}" required />
            </div>

            <div class="field">
                <label for="password"><fmt:message key="msg.password"/>:</label>
                <input class="input_line" type="password" id="password" name="password" pattern="[A-Za-z]{5,20}" required />

            </div>

            <div class="field">
                <input class="button_center" type=submit value="<fmt:message key="msg.enter"/>"/>
            </div>

        </div>
    </div>
    </form>
    <br/>
</header>
</fmt:bundle>
</body>
</html>
