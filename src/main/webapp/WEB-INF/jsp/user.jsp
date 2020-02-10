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

<h1 align="center">User</h1>
<div class="container">
    <table class='table table-bordered table-condensed table-striped table-hover'>

        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Role</td>
            <td>Books</td>
            <td>Actions</td>
        </tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.role}</td>
                <td>
                    <table class='table table-bordered table-condensed table-striped table-hover'>
                        <tr>
                            <td>BookID</td>
                            <td>Due Date</td>
                            <td>Actions</td>
                        </tr>
                        <tbody>
                        <tr>
                            <c:forEach items="${user.bookshelf}" var="book">
                                <td> ${book.id}</td>
                                <td>${book.dueDate}</td>
                                <td><form action="/user/returnBook" method="post" ><input type="text" hidden="hidden" name="book_id" value="${book.id}"><input type="submit" class="btn btn-primary" value="Return"></form><br></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
            </td>
                <td><form action="/user/edit" method="post"><input type="text" hidden="hidden" name ="id" value="${user.id}"><input type="submit" class="btn btn-primary" value="Edit"></form><br>
                    <c:if test="${user.role=='ROLE_USER'}">
                    <form action="/admin/lockUser" method ="post"><input type="text" hidden="hidden" name ="id" value="${user.id}"><input type="submit" class="btn btn-primary" value="Lock"></form><br>
                    </c:if>
                    <c:if test="${user.role=='ROLE_LOCKED'}">
                        <form action="/admin/unlockUser" method ="post"><input type="text" hidden="hidden" name ="id" value="${user.id}"><input type="submit" class="btn btn-primary" value="Unlock"></form><br>
                    </c:if>
                    <c:if test="${user.role ne 'ROLE_ADMIN' }">
                    <form action="/user/deleteUser" method="post"><input type="text" hidden="hidden" value="${user}"><input type="submit" class="btn btn-danger" value="Delete User"></form></td>
        </c:if>
        </tr>
    </table>
</div>
</body>
</html>