<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <head>
        <title>plang</title>
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/styles/homeStyle.css" />" >
        <link rel="stylesheet"
              type="text/css"
              href="<c:url value="/resources/styles/aboutStyle.css" />" >
    </head>
<body>
<header>
    <h2>Historia aplikacji.</h2>
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
            Aplikacja powstaje od końca listopada 2016 r. Na początku był to prosty projekt Maven, nastepnie został
            rozbudowany <br>o monitor postępu, później powstawały kolejne części programu, takie jak: gui w javafx,
            dodanie bazy danych, refaktoryzacja pod Java 8. Obecnie całość jest przepisywana na webPlang w oparciu
            o framework Spring.
        </p>
    </div>
</header>
</body>
</html>
