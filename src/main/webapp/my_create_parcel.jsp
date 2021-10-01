<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Creation</title>
</head>
<body>
<link rel="stylesheet" href="Style/create_parcel.css">
<header>
    <div class="containe">
        <div class="heading clearfix">
            <a href="${pageContext.request.contextPath}/get-city-servlet">

                <img src="img/logo.png" alt="" class="logo"></a>

            <nav>
                <ul class="menu">
                    <li>
                        <a href="my_parcel_start.jsp">Мої відправлення</a>
                    </li>
                    <li class="active">
                        <a href="#">Створити</a>
                    </li>
                    <li>
                        <a href="my_receipt_start.jsp">Квитанції</a>
                    </li>
                    <li>
                        <a href="index.jsp">Вийти</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    Ви зайшли як ${loggedUser.name}, ${loggedUser.surname},${loggedUser.role},${loggedUser.id}
    <h3>Створити відправлення</h3>
    <form class="form" action="controller" method="get"/>

    <input type="hidden" name="command" value="createParcel"/>
    <input type="hidden" name="userId" value="${loggedUser.id}"/>
    <input type="hidden" name="role" value="${loggedUser.role}"/>
    <input type="hidden" name="address" value="my_parcel_start.jsp"/>
    <div class="contain">
        <div class="row justify-content-center">
            <div class=row>
                <div class="col-xl-6">

                    <h6>З міста: </h6>
                    <!-- <input class="input_line" type="search" id="fromPoint" name="fromPoint" value=${calculatedParcel.fromPoint}> -->

                    <select class="input_line" id="fromPoint" name="fromPoint">
                        <option value=""></option>
                        <c:forEach items="${cities}" var="city" begin="1">
                            <option value="${city}">${city}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-xl-6">
                    <h6>У місто:</h6>
                    <!--    <input class="input_line" type="search" id="toPoint" name="toPoint" value=${calculatedParcel.toPoint}>   -->

                    <select class="input_line" id="toPoint" name="toPoint">
                        <option value=""></option>
                        <c:forEach items="${cities}" var="city" begin="1">
                            <option value="${city}">${city}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <!--  Тариф перевезення, км: ${calculatedParcel.distance}-->

            <div class="row">
                <div class="col-xl-6">
                    Вулиця:
                    <input class="input_line_mini" type="text" id="street" name="street">
                </div>

                <div class="col-xl-3">
                    Дім:
                    <input class="input_line_mini" type="number" id="house" name="house" min="1" max="3000">
                </div>

                <div class="col-xl-3">
                    Квартира:
                    <input class="input_line_mini" type="number" id="flat" name="flat" min="1" max="3000">
                </div>

            </div>

            <div class="row">
                <div class="col-xl-6">
                    <h6>Прізвище:</h6>
                    <input class="input_line_mini" type="text" id="surnameR" name="surnameR">
                </div>

                <div class="col-xl-6">
                    <h6>Ім'я:</h6>
                    <input class="input_line_mini" type="text" id="nameR" name="nameR">
                </div>

            </div>

            <div class=row>
                <div class="col-xl-3">
                    Довжина, см:
                    <input class="input_line_mini" type="number" id="length" name="length" min="1" max="3000"
                           value=${calculatedParcel.length != null ? calculatedParcel.length : 0}>
                </div>

                <div class="col-xl-3">
                    Ширина, см:
                    <input class="input_line_mini" type="number" id="width" name="width" min="1" max="3000"
                           value=${calculatedParcel.width != null ? calculatedParcel.width : 0}>
                </div>

                <div class="col-xl-3">
                    Висота, см:
                    <input class="input_line_mini" type="number" id="height" name="height" min="1" max="3000"
                           value=${calculatedParcel.height != null ? calculatedParcel.height : 0}>
                </div>

                <div class="col-xl-3">
                    Вага, кг:
                    <input class="input_line_mini" type="number" id="weight" name="weight" min="0.5" max="500" step="0.5"
                           value=${calculatedParcel.weight  != null ? calculatedParcel.weight : 0}>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-6">
                    <h6>Категорія:</h6>
                    <!--input class="input_line_mini" type="text" id="category" name="category"-->
                    <select class="input_line_mini" type="text" id="category" name="category" required>
                        <option value="Без категорії">Без категорії</option>
                        <option value="Автозапчастини">Автозапчастини</option>
                        <option value="Електроніка">Електроніка</option>
                        <option value="Одежа">Одежа</option>
                        <option value="Побутова техніка">Побутова техніка</option>
                        <option value="Продукти">Продукти</option>
                        <option value="Цінності">Цінності</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <input class="button_center" type=submit value="Замовити"/>

    </form>

</header>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.3/js/bootstrap.min.js"
        integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
        crossorigin="anonymous"></script>
</body>
</html>

