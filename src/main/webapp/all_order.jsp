<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>All Orders</title>
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
                        <a href="all_parcel_start.jsp">Відправлення</a>
                    </li>
                    <li class="active">
                        <a href="#">Заявки</a>
                    </li>
                    <li>
                        <a href="all_receipt_start.jsp">Квитанції</a>
                    </li>
                    <li>
                        <a href="all_delivery_start.jsp">Доставка</a>
                    </li>
                    <li>
                        <a href="all_report_start.jsp">Звіти</a>
                    </li>
                    <li>
                        <form name="callback" method="get" action="controller">
                            <input type="hidden" name="command" value="logout"/>
                            <a href="#" alt="send" onclick="document.forms['callback'].submit();" >Вийти</a>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </div>


    Ви увійшли як ${loggedUser.name} ${loggedUser.surname}, ${loggedUser.role} № ${loggedUser.id}


    <div class="containe">
        <table class="table table-striped table-bordered">
            <colgroup>
                <col span="14" style="background:Khaki">
            </colgroup>

            <tr>
                <th>ID Відпр</th>
                <th>ID Кор</th>
                <th>Користувач</th>
                <th>До міста</th>
                <th>Адреса</th>
                <th>Категорія</th>
                <th>Відстань</th>
                <th>Об'єм</th>
                <th>Вага</th>
                <th>Ціна</th>
                <th>Статус</th>
                <th>Дата замовлення</th>
                <th>Дія</th>
            </tr>
            <tbody id="myTable">
            <c:forEach begin="0" end="0" items="${parcels}" var="parcel">
                <c:set value="${parcel.userId}" var="previousUserId" scope="request"></c:set>
            </c:forEach>

            <c:forEach items="${parcels}" var="parcel">
                <tr>
                <c:if test="${parcel.userId == previousUserId}">
                    <td>${parcel.id}</td>
                    <td>${parcel.userId}</td>
                    <td>${parcel.fromPoint}</td>
                    <td>${parcel.toPoint}</td>
                    <td>${parcel.deliveryAddress}</td>
                    <td>${parcel.category}</td>
                    <td>${parcel.distance}</td>
                    <td>${parcel.length*parcel.height*parcel.width}</td>
                    <td>${parcel.weight}</td>
                    <td>${parcel.price}</td>
                    <td>${parcel.status}</td>
                    <td>${parcel.createDate}</td>
                    <td>
                        <form>
                            <input type="hidden" name="command" value="modifyParcel"/>
                            <input type="hidden" name="parcel_id" value="${parcel.id}"/>
                            <input type="hidden" name="managerId" value="${loggedUser.id}"/>
                            <input type="hidden" name="address" value="all_order_start.jsp"/>
                            <input type="hidden" name="status" value="Denied"/>
                            <button type="submit" class="btn btn-secondary">Відхилити</button>
                        </form>
                    </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>

        <form>
            <input type="hidden" name="command" value="createReceipt"/>
            <input type="hidden" name="userId" value="${previousUserId}"/>
            <input type="hidden" name="managerId" value="${loggedUser.id}"/>
            <input type="hidden" name="status" value="Ordered"/>
            <input type="hidden" name="sortColumnNumber" value="1"/>
            <input type="hidden" name="address" value="all_order_start.jsp"/>
            <c:if test="${previousUserId != null}">
                <button type="submit" class="btn btn-secondary">Опрацювання</button>
            </c:if>
        </form>
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

