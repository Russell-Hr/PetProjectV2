<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment</title>
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
                        <a href="my_parcel_start_jsp">Мої відправлення</a>
                    </li>
                    <li>
                        <a href="my_create_parcel_jsp">Створити</a>
                    </li>
                    <li>
                        <a href="my_receipt_start_jsp">Квитанції</a>
                    </li>
                    <li>
                        <form name="callback" method="get" action="${pageContext.request.contextPath}/logout">
                            <!--<input type="hidden" name="command" value="logout"/>-->
                            <a href="#" alt="send" onclick="document.forms['callback'].submit();" >Вийти</a>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <h3>Платіж:</h3>
    <form action="controller" method="get"/>
    <div class="block_buttons_lines">
        <input type="hidden" name="command" value="providePayReceipt"/>
        <input type="hidden" name="address" value="my_receipt_start.jsp"/>
        <input type="hidden" name="userId" value="${userId}"/>
        <input type="hidden" name="receiptId" value="${receiptId}"/>
        <input type="hidden" name="status" value="Payed"/>
        <div class="input_lines">
            <div class="field">
                <label for="nameEng">Name:</label>
                <input class="input_line" type="text" id="nameEng" name="nameEng"/>
            </div>

            <div class="field">
                <label for="surnameEng">Surname: </label>
                <input class="input_line" type="text" id="surnameEng" name="surnameEng">
            </div>

            <div class="input_lines">
                <div class="field">
                    <label for="cardNumber">Card number: </label>
                    <input class="input_line" type="text" id="cardNumber" name="cardNumber" pattern="[0-9]{16}">
                </div>

                <div class="field">
                    <label for="expireDate">Термін дії (MM/YY):</label>
                    <input class="input_line" type="text" id="expireDate" name="expireDate"
                           pattern="[0-9]{2}/[0-9]{2}"/>
                </div>
                <div class="input_lines">
                    <div class="field">
                        <label for="cvv2">CVV2: </label>
                        <input class="input_line" type="password" id="cvv2" name="cvv2" pattern="[0-9]{3}">
                    </div>

                    <div class="field">
                        <label for="receiptTotal">Сума:</label>
                        <input class="input_line" type="text" id="receiptTotal" name="receiptTotal" value="${receiptTotal}">
                        <input class="button_center" type=submit value="Сплатити"/>
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

