<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <head>
        <title>plang</title>
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/homeStyle.css" />" >
</head>
<body>
    <header>
        <h1>Witam w serwisie plang!</h1>
        <h2>Platformie stworzonej do nauki języków obcych.</h2>
        <nav>
            <ul>
                <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
                <li><a href=" <spring:url value="/application"/> ">aplikacja</a></li>
                <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
            </ul>
        </nav>
    </header>
</body>
</html>