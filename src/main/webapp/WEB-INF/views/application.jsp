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
        <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-3.2.1.min.js" />"></script>
        <style>
            body {
                background-image: url("../../resources/images/application.jpg");
            }
        </style>
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
    <div class="application_box">
        <div class="appText">
            <label id="word_to_translate">Wpisz "OK" aby rozpocząć.</label>
        </div>
        <form id="answer_form">
            <input id="wordInEnglish" type="text" autocomplete="off"/>
            <br>
            <input type="submit" id="ok_button" value="OK"/>
        </form>
        <div class="appText">
            <label id="is_correct"></label>
            <br>
            <label id="correct_answer"></label>
            <br>
            <label id="points"></label>
        </div>
    </div>
    <form:form action="${'/logout'}" method="post">
        <input type="submit" id="logout_button" value="Wyloguj"/>
    </form:form>
    <script type="text/javascript" src="/resources/js/application.js"></script>
    <script type="text/javascript" src="/resources/js/script.js"></script>
</body>
</html>
