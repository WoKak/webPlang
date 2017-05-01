<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>plang</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/styles/homeStyle.css" />">
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/styles/aboutStyle.css" />">
</head>
<body>
<h2>Historia aplikacji.</h2>
<nav>
    <ul>
        <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
        <li><a href=" <spring:url value="/login"/> ">aplikacja</a></li>
        <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
    </ul>
</nav>
<div class="about_box">
    <p>
        Aplikacja powstaje od końca listopada 2016 r. Na początku był to prosty projekt Maven, nastepnie został
        rozbudowany o moduł prezentowania postępów, później powstawały kolejne części programu, takie jak: gui w javafx,
        integracja z bazą danych, refaktoryzacja pod Java 8. Obecnie od marca 2017 r. całość jest przepisywana na
        webPlang w oparciu
        o framework Spring. Więcej informacji na
        <a href="https://github.com/WoKak/webPlang" style="text-decoration: none">
            stronie
        </a>.
    </p>
</div>
</body>
</html>
