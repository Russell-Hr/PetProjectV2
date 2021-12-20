<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Receipts</title>
</head>
<body ononline="document.frm1.submit()">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style/create_parcel.css">
<header>
    <div class="containe">
        <div class="heading clearfix">
            <a href="${pageContext.request.contextPath}/get-city-servlet">

                <img src="img/logo.png" alt="" class="logo"></a>

            <nav>
                <ul class="menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/app/my_parcel_start_jsp">Мої відправлення</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/app/my_create_parcel_start_jsp">Створити</a>
                    </li>
                    <li class="active">
                        <a href="#">Квитанції</a>
                    </li>
                    <li>
                        <form name="callback" method="get" action="${pageContext.request.contextPath}/app/logout">
                            <!--<input type="hidden" name="command" value="logout"/>-->
                            <a href="#" alt="send" onclick="document.forms['callback'].submit();" >Вийти</a>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    Ви увійшли як ${loggedUser.name} ${loggedUser.surname}, ${loggedUser.role} № ${loggedUser.id}
    <form class="form" action="${pageContext.request.contextPath}/app/findReceipts" method="get" name="frm1"/>
    <!--<input type="hidden" name="command" value="findReceipts"/>-->
    <input type="hidden" name="userId" value="${loggedUser.id}"/>
    <input type="hidden" name="address" value="my_receipt.jsp"/>
    <div class="input_line_left">
        <label for="status"> Показати квитанції:</label><br>
        <select class="input_line" id="status" name="status">
            <option value="All">Усі</option>
            <option value="Approved">До сплати</option>
            <option value="Payed">Сплачені</option>
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


                    <c:forEach items="${receipts}" var="receipt">

                        <div class=output_block>
                            КВИТАНЦІЯ № ${receipt.id}
                            <br>
                            Статус: ${receipt.status}
                            <br>
                            <br>
                            ДО СПЛАТИ: ${receipt.total}грн.
                            <br>
                            <br>
                            Створена: ${receipt.createDate}
                            <c:if test="${receipt.status == 'Payed'}">
                                <br>
                                Сплачена: ${receipt.paymentDate}
                            </c:if>
                            <br>
                            <c:if test="${receipt.status == 'Approved'}">
                                <br>
                                <div class="btn-group">
                                    <form action="${pageContext.request.contextPath}/app/modifyReceipt">
                                        <!--<input type="hidden" name="command" value="modifyReceipt"/>-->
                                        <input type="hidden" name="address" value="my_receipt_start.jsp"/>
                                        <input type="hidden" name="userId" value="${loggedUser.id}"/>
                                        <input type="hidden" name="role" value="${loggedUser.role}"/>
                                        <input type="hidden" name="receiptId" value="${receipt.id}"/>
                                        <input type="hidden" name="receiptStatus" value="Canceled"/>
                                        <button type="submit" class="btn btn-secondary">Відмінити</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/app/payReceipt">
                                        <!--<input type="hidden" name="command" value="payReceipt"/>-->
                                        <input type="hidden" name="address" value="my_payment.jsp"/>
                                        <input type="hidden" name="userId" value="${loggedUser.id}"/>
                                        <input type="hidden" name="role" value="${loggedUser.role}"/>
                                        <input type="hidden" name="receiptTotal" value="${receipt.total}"/>
                                        <input type="hidden" name="receiptId" value="${receipt.id}"/>
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

