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

    <title>Repository</title>

</head>
<body>
<h1>Books</h1>

<h3>${result}</h3>
<c:if test="${books ne null}">
<table class='table table-bordered table-condensed table-striped table-hover'>

    <tr>
        <td>Title</td>
        <td>Authors</td>
    </tr >
    <c:forEach items= "${requestScope.books }" var="book">
    <tr class="clickable text-center"
        onclick="window.location='/books/${book.id}'">

            <td>${book.title}</td>
    <td><c:forEach items="${book.authors }" var ="author">
            <c:out value="${author.name } ${author.surname }"></c:out><br>
        </c:forEach>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
</body>
</html>