<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title></title>
</head>
<body>

<h1><spring:message code="detail.list"/></h1>
<table border="1">
  <tr>
    <td>id</td>
    <td>name</td>
  </tr>
  <c:forEach items="$detailList" var="detail">
    <tr>
      <td>${detail.detailId}</td>
      <td>${detail.detailName}</td>
    </tr>
  </c:forEach>
</table>
<a href="/inputDetail"><spring:message code="detail.create"/></a>
</body>
</html>
