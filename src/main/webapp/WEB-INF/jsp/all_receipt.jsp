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
    <title>All Receipts</title>
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
                        <a href="all_parcel_start_jsp">Відправлення</a>
                    </li>
                    <li>
                        <a href="all_order_start_jsp">Заявки</a>
                    </li>
                    <li class="active">
                        <a href="#">Квитанції</a>
                    </li>
                    <li>
                        <a href="all_delivery_start_jsp">Доставка</a>
                    </li>
                    <li>
                        <a href="all_report_start_jsp">Звіти</a>
                    </li>
                    <li>
                        <form name="callback" method="get" action="${pageContext.request.contextPath}/logout">
                            <!--<input type="hidden" name="command" value="logout"/>-->
                            <a href="#" alt="send" onclick="document.forms['callback'].submit();">Вийти</a>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </div>


    Ви увійшли як ${loggedUser.name} ${loggedUser.surname}, ${loggedUser.role} № ${loggedUser.id}


    <div class="containe">
        <div class="input_line">
            <input class="form-control" id="myInput" type="text" placeholder="Пошук..">
        </div>
        <table class="table table-striped table-bordered">
            <colgroup>
                <col span="14" style="background:Khaki">
            </colgroup>

            <tr>
                <th>ID Квит</th>
                <th>ID Кор</th>
                <th>Платник</th>
                <th>Інформація</th>
                <th>Сума</th>
                <th>Статус</th>
                <th>ID Менед</th>
                <th>Дата реєстрації</th>
                <th>Дата платежу</th>
                <th>Дія</th>
            </tr>
            <tbody id="myTable">

            <c:forEach begin="${startReceipt}" end="${endReceipt}" items="${receipts}" var="receipt">
                <tr>
                    <td>${receipt.id}</td>
                    <td>${receipt.userId}</td>
                    <td>${receipt.infoUser}</td>
                    <td>${receipt.infoRoute}</td>
                    <td>${receipt.total}</td>
                    <td>${receipt.status}</td>
                    <td>${receipt.managerId}</td>
                    <td>${receipt.createDate}</td>
                    <td>${receipt.paymentDate}</td>
                    <td>
                        <c:if test="${receipt.status == 'Approved'}">
                            <form method="post" action="${pageContext.request.contextPath}/modifyReceipt">
                                <!--<input type="hidden" name="command" value="modifyReceipt"/>-->
                                <input type="hidden" name="address" value="all_receipt_start.jsp"/>
                                <input type="hidden" name="userId" value="${receipt.userId}"/>
                                <input type="hidden" name="receiptId" value="${receipt.id}"/>
                                <input type="hidden" name="receiptStatus" value="Denied"/>
                                <button type="submit" class="btn btn-secondary">Відмінити</button>
                            </form>
                        </c:if>
                    </td>
                    <c:set value="${receipt.id}" var="previousReceiptId" scope="request"></c:set>

                </tr>

            </c:forEach>

            </tbody>
        </table>

        <table class="input_line_mini" border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>

                            <td>
                                <a href="${pageContext.request.contextPath}/findReceipts?page=${i}&sortColumnNumber=${sortColumnNumber}&address=all_receipt.jsp">${i}</a>
                            </td>


                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
    </div>
</header>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
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

