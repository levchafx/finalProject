<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Repository</title>
</head>
<body>

<h1>Books</h1>
<table>

    <tr>

        <td>Title</td>
        <td>Authors</td>
        <td>Quantity</td>
        <td>Description</td>
        <sec:authorize access="isAuthenticated()">
        <td>Get</td>
        </sec:authorize>
    </tr>
    <c:forEach items="${requestScope.books }" var="book">
        <tr><td>${book.title}</td><td><c:forEach items="${book.authors }" var ="author">
            <c:out value="${author.name } ${author.surname }"></c:out><br>
        </c:forEach>

        </td><td>${book.quantity }</td><td><form
                action="${pageContext.request.contextPath}/front-controller/?Command=Description" method="post">
            <input name="description" type="hidden" value="${book.description}">
            <input name="image_id" type="hidden" value="${book.id}"> <input
                type="submit" value="Read more">
        </form></td><td><sec:authorize access="hasAnyRole({'ADMIN','USER'})" ><c:if test="${book.quantity>0}">
            <form action="/user/getBook"
                  method="post">
                <input name="book_id" type="hidden" value="${book.id}">
                how many weeks would you like to read it for?<br>
                <select name="weeks">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select> <input type="submit" value="Get book"> <br>
            </form>
        </c:if></sec:authorize></td></tr>
    </c:forEach>
</table>

</body>
</html>