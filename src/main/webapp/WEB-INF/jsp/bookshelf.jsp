<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>

    <title>Bookshelf</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>