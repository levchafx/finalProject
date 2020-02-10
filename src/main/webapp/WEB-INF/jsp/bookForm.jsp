<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>add book</title>
</head>
<body>
<br>
<br>
<br>
<br>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="well well-sm">
                <form:form class="form-horizontal"  action="/admin/addBook" method="post" modelAttribute="book" enctype="multipart/form-data">
                    <input type="hidden" name="id"  path="id" value="${book.id}">
                    <fieldset>
                        <legend class="text-center header">Add book</legend>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                                <form:errors path = "title" cssClass = "error" />
                                <div style="clear: both;"></div>
                              Title  <input  name="title" type="text" path="title" placeholder="title" value="${book.title}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
                            <div class="col-md-8">
                                <form:errors path="author" cssClass="error"/>
                                <div style="clear: both;"></div>
                                Author <input id="lname" name="author" path ="author" type="text" value="<c:forEach var="author" items="${book.authors}">${author.name} ${author.surname} </c:forEach>" placeholder="enter authors name and surname separated by comma" class="form-control">

                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
                            <div class="col-md-8">
                                <form:errors path = "quantity" cssClass = "error" />
                                <div style="clear: both;"></div>
                               Quantity <input id="quantity" name="quantity" path="quantity" type="text"value ="${book.quantity}" placeholder="how many are there?" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
                            <div class="col-md-8">
                              Image  <input type="file" name="file"  path ="file"  class="form-control" >
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-pencil-square-o bigicon"></i></span>
                            <div class="col-md-8">
                                <textarea class="form-control" id="description" path ="description"  name="description" placeholder="Add a short description" rows="7">${book.description} </textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Add Book</button>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>