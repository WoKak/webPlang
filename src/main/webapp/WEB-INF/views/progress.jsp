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
          href="<c:url value="/resources/styles/progressStyle.css" />">
    <script type="text/javascript" src="<c:url value="/resources/chartjs/Chart.bundle.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-3.2.1.min.js" />"></script>
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
        <li><a href=" <spring:url value="/addWord"/> ">dodaj słówko</a></li>
        <li><a href=" <spring:url value="/progress"/> ">postępy</a></li>
        <li><a href=" <spring:url value="/wordbase"/> ">baza słówek</a></li>
        <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
    </ul>
</nav>
<div class="info_box">
    <table>
        <tr>
            <td id="options_side">
                <form id="criteria">
                    <h4>Kryteria do wykresu słówek:</h4>
                    <input id="how_many" type="text" autocomplete="off" placeholder="ile słówek?"/>
                    <br>
                    <p>
                        <input type="radio" name="order" value="desc">najlepiej umiem<br>
                        <input type="radio" name="order" value="asc">najsłabiej umiem<br>
                    </p>
                    <input type="submit" id="ok_button_words" value="OK"/>
                </form>
                <form id="word_criteria">
                    <h4>Kryteria do wykresu słówka:</h4>
                    <input id="word" type="text" autocomplete="off" placeholder="jakie słówko?"/>
                    <br>
                    <input type="submit" id="ok_button_word" value="OK"/>
                </form>
            </td>
            <td id="canvas_side">
                <canvas id="myChart"></canvas>
            </td>
        </tr>
    </table>
</div>
<form:form action="${'/logout'}" method="post">
    <input type="submit" id="logout_button" value="Wyloguj"/>
</form:form>
<script type="text/javascript" src="/resources/js/progress.js"></script>
<script type="text/javascript" src="/resources/js/word_chart.js"></script>
</body>
</html>
