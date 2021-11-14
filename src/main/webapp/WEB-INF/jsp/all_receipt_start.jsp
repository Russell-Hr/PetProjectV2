<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>AllReceiptsStart</title>
</head>
<body onload="document.frm1.submit()">
<header>
    <form class="form" action="${pageContext.request.contextPath}/app/findReceipts" method="get" name="frm1"/>
    <!--<input type="hidden" name="command" value="findReceipts"/>-->
    <input type="hidden" name="address" value="all_receipt.jsp"/>
    <input type="hidden" name="sortColumnNumber" value="0"/>
    </form>
</header>
</body>
</html>

