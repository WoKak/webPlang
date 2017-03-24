<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <head>
        <title>plang</title>
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/homeStyle.css" />">
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/appStyle.css" />">
    </head>
<body>
<header>
    <h2>plang</h2>
    <nav>
        <ul>
            <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
            <li><a href=" <spring:url value="/application"/> ">aplikacja</a></li>
            <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
        </ul>
    </nav>
    <div class="application_box">
        <div class="word_in_polish">
            <label>Aby rozpocząć naciśnij przycisk next.</label>
        </div>
        <div class="word_in_english">
            <textarea></textarea>
        </div>
        <div class="button_box">
            <button id="ok_button">OK</button>
            <button id="next_button">Next</button>
        </div>
    </div>
</header>
</body>
</html>
