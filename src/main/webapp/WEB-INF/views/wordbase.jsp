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
          href="<c:url value="/resources/styles/aboutStyle.css" />">
    <style>
        body {
            background-image: url("../../resources/images/wordbase.jpg");
        }

        .about_box p {
            text-align: center;
        }
    </style>
    <head>
        <script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.3/angular.min.js"></script>
    </head>
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
<div class="about_box" ng-app="viewer" ng-controller="guestController">
    <label>Name:</label>
    <input type = "text" ng-model = "guest.name" placeholder = "Jak masz na imię?">
    <hr />

    <h1>Witaj {{guest.getName()}}, niestety strona jest w budowie!</h1>
</div>
<form:form action="${'/logout'}" method="post">
    <input type="submit" id="logout_button" value="Wyloguj"/>
</form:form>
<script src="/resources/js/viewer/viewer.js"></script>
</body>
</html>