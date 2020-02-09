<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>


<form:form action="/register" method="post" modelAttribute="user" >

    <input type="hidden" name ="id" value="${user.id}" path ="id" required="required">
    <div class="form-group ">
        <form:errors path = "name" cssClass = "error" />
        <div style="clear: both;"></div>
        <label>Name</label>
        <div style="clear: both;"></div>
    <input type="text" name="name" value ="${user.name}"  path ="name" required="required" placeholder="name" >
    </div>
    <input type="hidden" name="authenticate.id" value ="${user.authenticate.id}" path="authenticate.id" required="required">
    <div class="form-group">
        <form:errors path = "authenticate.login" cssClass = "error" />
        <div style="clear: both;"></div>
        <label>Login</label>
        <div style="clear: both;"></div>
   <input type="text" name="authenticate.login" value ="${user.authenticate.login}" path="authenticate.login" required="required" placeholder="login">

    </div>
    <div class="form-group">
        <form:errors path = "authenticate.password" cssClass = "error" />
        <div style="clear: both;"></div>
        <label>Password</label>
        <div style="clear: both;"></div>
    <input type="password" name="authenticate.password" value ="${user.authenticate.password}" path="authenticate.password" required="required" placeholder="password">
    </div>
    <div class="form-group">
        <form:errors path = "authenticate.confirmPassword" cssClass = "error" />
        <div style="clear: both;"></div>
        <label>Confirm password</label>
        <div style="clear: both;"></div>
    <input type="password" name="authenticate.confirmPassword" value ="${user.authenticate.confirmPassword}"  path="authenticate.confirmPassword"required="required" placeholder="confirm password">

    </div>

    <sec:authorize access="hasRole('ADMIN')">
    <div class="form-group">
        <label>Role</label>
        <div style="clear: both;"></div>
       <select name="role" >
            <option>ROLE_USER</option>
            <option>ROLE_ADMIN</option>
        </select>
    </div>
    </sec:authorize>

    <input type="submit" class ="btn btn-primary" value="Register">
</form:form>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>

</html>
