<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="lib" uri="/WEB-INF/lib.tld"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Parcels</title>
</head>
<body ononline="document.frm1.submit()">
<link rel="stylesheet" href="Style/create_parcel.css">
<header>
    <div class="containe">
        <div class="heading clearfix">
            <a href="${pageContext.request.contextPath}/get-city-servlet">

                <img src="img/logo.png" alt="" class="logo"></a>

            <nav>
                <ul class="menu">
                    <li class="active">
                        <a href="#">Мої відправлення</a>
                    </li>
                    <li>
                        <a href="get-city-servlet">Створити</a>
                    </li>
                    <li>
                        <a href="my_receipt_start.jsp">Квитанції</a>
                    </li>
                    <li>
                        <form name="callback" method="get" action="controller">
                            <input type="hidden" name="command" value="logout"/>
                            <a href="#" alt="send" onclick="document.forms['callback'].submit();">Вийти</a>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    Ви увійшли як ${loggedUser.name} ${loggedUser.surname}, ${loggedUser.role} № ${loggedUser.id}

    <form class="form" action="controller" method="get" name="frm1"/>
    <input type="hidden" name="command" value="findParcels"/>
    <input type="hidden" name="userId" value="${loggedUser.id}"/>
    <input type="hidden" name="address" value="my_parcel.jsp"/>
    <div class="input_line_left">
        <label for="status"> Показати відправлення:</label><br>
        <select class="input_line" id="status" name="status">
            <option value="All">Усі</option>
            <option value="Ordered">Замовлені</option>
            <option value="Approved">До сплати</option>
            <option value="Payed">Сплачені</option>
            <option value="Delivered">Доставлені</option>
            <option value="Canceled">Відмінені</option>
            <option value="Denied">Відхилені</option>
        </select>
        <button type="submit" class="btn btn-secondary">Знайти</button>
    </div>
    </form>

    <div class="container">
        <div class="groups">
            <div style="margin: 20px 0 ;">
                <table class="groups_table">


                    <c:forEach items="${parcels}" var="parcel">

                        <div class=output_block>
                            <c:out value="Відправлення № ${parcel.id}"/>
                            <br>
                            <c:out value="Статус: ${parcel.status}"/>
                            <br>
                            <c:out value="Категорія: ${parcel.category}"/>
                            <br>
                            <c:out value="Адреса: м. ${parcel.toPoint}"/>
                            <br>
                            <c:out value="${parcel.deliveryAddress}"/>
                            <br>
                            <c:out value="Маса: ${parcel.weight}кг; "/>
                            <c:out value="Об'єм: ${parcel.length*parcel.height*parcel.width}см3"/>
                            <br>
                            <c:out value="Ціна: ${parcel.price}грн."/>
                            <br>
                            <c:out value="Створено: ${parcel.createDate}"/>
                            <br>
                            <c:if test="${parcel.paymentDate != null}">
                                <c:out value="Сплачено: ${parcel.paymentDate}"/>
                            </c:if>
                            <c:if test="${parcel.deliveryDate != null}">
                                <br>
                                <c:out value="Доставлено: ${parcel.deliveryDate}"/>
                            </c:if>
                            <c:if test="${parcel.status == 'Ordered'}">
                                <div>
                                    <form>
                                        <input type="hidden" name="command" value="modifyParcel"/>
                                        <input type="hidden" name="parcel_id" value="${parcel.id}"/>
                                        <input type="hidden" name="userId" value="${loggedUser.id}"/>
                                        <input type="hidden" name="role" value="${loggedUser.role}"/>
                                        <input type="hidden" name="address" value="my_parcel_start.jsp"/>
                                        <input type="hidden" name="status" value="Canceled"/>
                                        <button type="submit" class="btn btn-secondary">Відмінити</button>
                                    </form>
                                </div>
                            </c:if>
                            <c:if test="${parcel.status == 'Approved'}">
                                <div>
                                    <form>
                                        <input type="hidden" name="command" value="findReceipts"/>
                                        <input type="hidden" name="userId" value="${loggedUser.id}"/>
                                        <input type="hidden" name="role" value="${loggedUser.role}"/>
                                        <input type="hidden" name="address" value="my_receipt_start.jsp"/>
                                        <input type="hidden" name="status" value="Approved"/>
                                        <button type="submit" class="btn btn-info">Сплатити</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>


                </table>
            </div>
        </div>
    </div>


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

