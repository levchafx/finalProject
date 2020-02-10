<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="index.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>

<h1 align="center">USERS</h1>
<div class="container">
<table class='table table-bordered table-condensed table-striped table-hover'>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Role</td>
    </tr>
    <c:forEach items="${users }" var="user">
            <tbody>
                <tr class="clickable text-center"
                onclick="window.location='/admin/users/${user.id}'">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.role}</td>
                </tr>
            </tbody>
    </c:forEach>
</table>
</div>
</body>
</html>