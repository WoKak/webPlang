<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html>
<head>
    <title>plang</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/styles/homeStyle.css" />">
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/styles/appStyle.css" />">
    <style>
        body {
            background-image: url("../../resources/images/progress.jpg");
        }
    </style>
</head>
<body>
<h2>plang</h2>
<nav>
    <ul>
        <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
        <li><a href=" <spring:url value="/application"/> ">aplikacja</a></li>
        <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
    </ul>
</nav>
<div class="application_box">
    <form:form modelAttribute="userToRegister">
        <label>Login:</label>
        <div class="form-group">
            <form:input id="login" path="login" type="text"/>
        </div>
        <label>Hasło:</label>
        <div>
            <form:input id="password" path="password" type="password"/>
        </div>
        <label>Powtórz hasło:</label>
        <div>
            <form:input id="repeatedPassword" path="repeatedPassword" type="password"/>
        </div>
        <div class="form-group">
            <input type="submit" class="button" value="rejestruj"/>
        </div>
    </form:form>
</div>
</body>
</html>
