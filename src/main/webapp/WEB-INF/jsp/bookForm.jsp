<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="index.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
</body>
</html>