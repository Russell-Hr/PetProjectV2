<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>AllParcelsStart</title>
</head>
<body onload="document.frm1.submit()">
<header>
    <form class="form" action="${pageContext.request.contextPath}/findParcels" method="get" name="frm1">
    <!--<form class="form" action="controller" method="get" name="frm1"/>
    <input type="hidden" name="command" value="findParcels"/>-->
    <input type="hidden" name="address" value="all_parcel.jsp"/>
    <input type="hidden" name="sortColumnNumber" value="0"/>
    </form>
</header>
</body>
</html>

