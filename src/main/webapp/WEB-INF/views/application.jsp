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
    <script>
        document.getElementById('ok_button').onclick = test;

        function f(){
            document.getElementById('wtt').innerHTML = "ala ma kota";
        }
    </script>
    <h2>plang</h2>
    <nav>
        <ul>
            <li><a href=" <spring:url value="/home"/> ">strona główna</a></li>
            <li><a href=" <spring:url value="/application"/> ">aplikacja</a></li>
            <li><a href=" <spring:url value="/addWord"/> ">dodaj słówko</a></li>
            <li><a href=" <spring:url value="/about"/> ">o aplikacji</a></li>
        </ul>
    </nav>
    <div class="application_box">
        <div class="word_in_polish">
            <label id="wtt">${wordToTranslate}</label>
        </div>
        <form:form modelAttribute="userAnswer">
            <div class="form-group">
                <form:input id="wordInEnglish" path="wordInEnglish" type="text"/>
            </div>
            <div class="form-group">
                <input type="submit" id="ok_button" value="OK"/>
            </div>
        </form:form>
    </div>
</body>
</html>
