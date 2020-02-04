<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <title>Bookshelf</title>
    <link rel="stylesheet" type="text/css" href="mylibrary.css" />
</head>
<body>


<h1>Bookshelf</h1>

<c:out value="${bookshelfempty}"></c:out>
<br>
<br>
<c:forEach items="${books}" var="book">
    <c:out value="${book}"></c:out>
    <br>
    <br>
    <form action="/user/returnBook" method="post">
        <input name="book_id" value="${book.id}" type="hidden">
        <input type="submit" value="Return">
    </form>
</c:forEach>
</body>
</html>