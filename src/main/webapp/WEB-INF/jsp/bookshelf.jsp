<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="index.jsp" />
<!DOCTYPE html>
<html>
<head>

    <title>Bookshelf</title>
</head>
<body>


<h1>Bookshelf</h1>

<c:out value="${bookshelfempty}"></c:out>
<c:if test="${fn:length(books) > 0}">
<table class='table table-bordered table-condensed table-striped table-hover'>
    <tr>
        <td>Book ID</td>
        <td>Due date</td>
        <td>Actions</td>
    </tr>
    <tbody>
<c:forEach items="${books}" var="book">
    <tr  class="clickable text-center"
          onclick="window.location='/books/${book.bookId}'">
        <td>${book.bookId}</td>
        <td>${book.dueDate}</td>

   <td>
    <form action="/user/returnBook" method="post">
        <input name="book_id" value="${book.id}" type="hidden">
        <input type="submit" class="btn btn-primary" value="Return">
    </form>
   </td>
    </tr>
</c:forEach>
    </tbody>
</table>
</c:if>
</body>
</html>