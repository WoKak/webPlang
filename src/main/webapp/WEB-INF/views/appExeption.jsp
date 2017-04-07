<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <head>
        <title>plang</title>
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/styles/homeStyle.css" />" >
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/styles/errorStyle.css" />" >
    </head>
<body>
    <h2>plang</h2>
    <nav>
        <ul>
            <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
            <li><a href=" <spring:url value="/application"/> ">aplikacja</a></li>
            <li><a href=" <spring:url value="/addWord"/> ">dodaj słówko</a></li>
            <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
        </ul>
    </nav>
    <div class="about_box">
        <p>
            ${message}
        </p>
    </div>
</body>
</html>