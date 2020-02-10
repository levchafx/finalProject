
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="index.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Messages</title>
</head>
<body>
<sec:authentication var="principal" property="principal" />
<h1 align="center">Messages</h1>
<table class='table table-bordered table-condensed table-striped table-hover'>
    <tr>
        <td>userId</td>
        <td>Message</td>
    </tr>
    <c:forEach items="${messages }" var="message">
        <tbody>
        <tr class="clickable text-center"
            onclick="window.location='/admin/users/${message.userId}'">
            <td>${message.userId}</td>
            <td>${message.messageText}</td>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>

