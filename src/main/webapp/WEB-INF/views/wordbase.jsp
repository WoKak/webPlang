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
    <style>
        body {
            background-image: url("../../resources/images/wordbase.jpg");
        }

        .about_box p {
            text-align: center;
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.13.3/react.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.13.3/JSXTransformer.js"></script>
</head>
<body>
<h2>plang</h2>
<nav>
    <ul>
        <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
        <li><a href=" <spring:url value="/application"/> ">aplikacja</a></li>
        <li><a href=" <spring:url value="/addWord"/> ">dodaj słówko</a></li>
        <li><a href=" <spring:url value="/progress"/> ">postępy</a></li>
        <li><a href=" <spring:url value="/wordbase"/> ">baza słówek</a></li>
        <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
    </ul>
</nav>
<div class="about_box" id="root">
    <script type="text/jsx" src="/resources/js/viewer/viewer.js"></script>
</div>
<form:form action="${'/logout'}" method="post">
    <input type="submit" id="logout_button" value="Wyloguj"/>
</form:form>
</body>
</html>