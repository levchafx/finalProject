<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="index.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Repository</title>
</head>
<body>
<spring:url value="/images" var="images" />
<h1>${book.title}</h1><br>
<div class="col-md-10" style="width: 100%">
    <div class="row">
        <div class="col-md-6">
            <c:choose>
                <c:when test="${book.base64Image.isEmpty()}">
                    <img src="/images/default.png" width="300" height="300" class="float-left"/>
                </c:when>

                <c:otherwise>
                    <img src="data:image/jpg;base64,${book.base64Image}" width="300" height="300" class="float-left"/>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="col-md-6">

            <h3>Authors</h3><c:forEach items="${book.authors }" var ="author">
            <c:out value="${author.name } ${author.surname }"></c:out><br>
        </c:forEach><br>
          <h3>Description</h3><c:out value="${book.description}"></c:out><br><br>
            <div class="btn-toolbar" >
                <div class="row">
                    <div class="col-xs-4">
                        <sec:authorize access="hasAnyRole({'ADMIN','USER'})" >
                            <c:if test="${book.quantity>0}">
                                <button class="btn btn-primary mr-1" type="button"  data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    Get book
                                </button>
                                <div class="collapse" id="collapseExample">
                                    <form action="/user/getBook"
                                          method="post">
                                        <input name="book_id" type="hidden" value="${book.id}">
                                        how many weeks would you like to keep it for?
                                        <select name="weeks" required="required">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                        </select> <input type="submit" class ="btn btn-primary" value="Get book"> <br>
                                    </form>
                                </div>
                            </c:if>
                        </sec:authorize>
                    </div>
                    <div class="col-xs-4 text-center">
                        <sec:authorize access="hasRole('ADMIN')">
                        <form action ="/admin/addBook" method ="get">
                            <input type="hidden" name ="bookId" value="${book.id}">
                            <input type="submit" class ="btn btn-primary mr-1" value ="Edit book">
                        </form>
                        </sec:authorize>
                    </div>
                    <div class="col-xs-4 text-right">
                       <sec:authorize access="hasRole('ADMIN')"> <form action ="/admin/deleteBook" method ="post">
                           <input type="hidden" name ="bookId" value="${book.id}">
                           <input type="submit" class ="btn btn-danger" value ="Delete book">
                       </form>
                       </sec:authorize>
                    </div>
                </div>
            </div>
    </div>
</div>
</div>
</body>
</html>