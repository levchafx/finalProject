<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="index.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Message</title>
</head>
<body>
<h2>Send message to admin</h2>

<div class="container">
    
<form:form action="/user/sendMessage" method="post" modelAttribute="message">

    <form:textarea path="messageText"  name="messageText" class="" cols="40" rows="3"
                   placeholder="type tour message here"></form:textarea><br>
    <input type="submit" class="btn btn-primary" value="Send message">
</form:form>
</div>
</body>
</html>