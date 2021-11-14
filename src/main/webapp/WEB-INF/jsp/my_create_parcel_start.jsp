<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MyParcelsStart</title>
</head>
<body onload="document.frm1.submit()">

<header>

    <form class="form" action="${pageContext.request.contextPath}/app/getCity" method="get" name="frm1"/>
    <!--<input type="hidden" name="command" value="findParcels"/>-->
    <input type="hidden" name="userId" value="${loggedUser.id}"/>
    <input type="hidden" name="address" value="my_create_parcel.jsp"/>
    <input type="hidden" name="status" value="All"/>
    </form>

</header>
</body>
</html>

