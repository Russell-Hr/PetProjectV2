<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculation</title>
</head>
<body>
<link rel="stylesheet" href="Style/calculate.css">
<header>
    <div class="container">
        <div class="heading clearfix">
            <a href="#">

                <img src="img/logo.png" alt="" class="logo"></a>

            <nav>
                <ul class="menu">
                    <li>
                        <a href="index.jsp">Головна</a>
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

    <h3>Вартість перевезення, грн: ${calculatedParcel.price}</h3>
    <form action="controller" method="get"/>
    <div class="block_buttons_lines">
        <input type="hidden" name="command" value="calculate"/>
        <input type="hidden" name="address" value="calculate.jsp"/>
        <div class="input_lines">
            <div class="field">
                <label for="fromPoint">З міста: </label>
                <select class="input_line" id="fromPoint" name="fromPoint">
                    <option value=${calculatedParcel.fromPoint} style="font-weight:bold">${calculatedParcel.fromPoint}</option>
                    <c:forEach items="${cities}" var="city" begin="1">
                        <option value="${city}">${city}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="field">
                <label for="toPoint">У місто:</label>
                <select class="input_line" id="toPoint" name="toPoint">
                    <option value=${calculatedParcel.toPoint} style="font-weight:bold">${calculatedParcel.toPoint}</option>
                    <c:forEach items="${cities}" var="city" begin="1">
                        <option value="${city}">${city}</option>
                    </c:forEach>
                </select>
            </div>

            Тариф перевезення, км: ${calculatedParcel.distance}

            <div class="field">
                <label for="length">Довжина, см: </label>
                <input class="input_line" type="number" id="length" name="length" min="1" max="3000"
                       value=${calculatedParcel.length != null ? calculatedParcel.length : 0}>
            </div>

            <div class="field">
                <label for="width">Ширина, см:</label>
                <input class="input_line" type="number" id="width" name="width" min="1" max="3000"
                       value=${calculatedParcel.width != null ? calculatedParcel.width : 0}>
            </div>

            <div class="field">
                <label for="height">Висота, см: </label>
                <input class="input_line" type="number" id="height" name="height" min="1" max="3000"
                       value=${calculatedParcel.height != null ? calculatedParcel.height : 0}>
            </div>

            <div class="field">
                <label for="weight">Маса, кг:</label>
                <input class="input_line" type="number" id="weight" name="weight" min="0.5" max="500" step="0.5"
                       value=${calculatedParcel.weight  != null ? calculatedParcel.weight : 0}>
                <input class="button_center" type=submit value="Розрахувати"/>
            </div>
        </div>
        </form>
    </div>
</header>
</body>
</html>

