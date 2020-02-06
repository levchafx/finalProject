<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Repository</title>
</head>
<body>
<spring:url value="/images" var="images" />
<h1>Book</h1>
<div class="container" align="right">
    <c:choose>
        <c:when test="${book.base64Image.isEmpty()}">
            <img src="/images/default.png" width="300" height="300" class="float-left"/>
        </c:when>

        <c:otherwise>
            <img src="data:image/jpg;base64,${book.base64Image}" width="100" height="100" class="float-left"/>
        </c:otherwise>
    </c:choose>


<table class='table table-bordered table-condensed table-striped table-hover'>

    <tr>

        <td>Title</td>
        <td>Authors</td>
        <td>Quantity</td>
        <td>Description</td>

        <sec:authorize access="isAuthenticated()">
            <td>Get</td>
        </sec:authorize>

    </tr >
        <td>${book.title}</td>
    <td><c:forEach items="${book.authors }" var ="author">
        <c:out value="${author.name } ${author.surname }"></c:out><br>
    </c:forEach>
    </td>
        <td>${book.quantity }</td>
       <td><sec:authorize access="hasAnyRole({'ADMIN','USER'})" >
    <c:if test="${book.quantity>0}">
        <form action="/user/getBook"
              method="post">
            <input name="book_id" type="hidden" value="${book.id}">
            how many weeks would you like to keep it for?<br>
            <select name="weeks" required="required">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
            </select> <input type="submit" class ="btn btn-primary" value="Get book"> <br>
        </form>
    </td>
        </c:if>
    </sec:authorize>
    </tr>
    </tbody>
</table>

</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>