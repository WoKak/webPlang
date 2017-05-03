<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
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
    <div class="appText">
        <label>Zaloguj się</label>
    </div>
    <form action="${"/login"}" method="post">
        <div class="form-group">
            <input type="text" name="username" placeholder="login"/>
            <br>
            <br>
            <input type="password" name="password" placeholder="hasło"/>
        </div>
        <div class="form-group">
            <input type="submit" id="ok_button" value="Zaloguj"/>
        </div>
    </form>
    <a href=" <spring:url value="/register"/> ">Rejestracja</a>
</div>
</body>
</html>

