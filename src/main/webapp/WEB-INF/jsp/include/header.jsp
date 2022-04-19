<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <c:if test="${fn:contains(pageContext.request.requestURI, '/index')}">
        <link rel="stylesheet" href="pub/css/index.css">
    </c:if>
    <c:if test="${fn:contains(pageContext.request.requestURI, '/login/loginForm')}">
        <link rel="stylesheet" href="../pub/css/login.css">
    </c:if>
    <c:if test="${fn:contains(pageContext.request.requestURI, '/user/register')}">
        <link rel="stylesheet" href="../pub/css/register.css">
    </c:if>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600;700&display=swap" rel="stylesheet">
    <script src="https://use.fontawesome.com/9719aa7074.js"></script>
    <title>FilmBuddy</title>
</head>


<body>
<section class="header">
    <nav>
        <c:if test="${fn:contains(pageContext.request.requestURI, '/index')}">
            <img src="pub/images/film-icon.png">
        </c:if>
        <c:if test="${fn:contains(pageContext.request.requestURI, '/user/register')}">
            <img src="../pub/images/film-icon.png">
        </c:if>

        <c:if test="${fn:contains(pageContext.request.requestURI, '/login/loginForm')}">
            <img src="../pub/images/film-icon.png">
        </c:if>
        <a class="brand" href="/index">FilmBuddy</a>
        <div class="nav-links" id="navLinks">
            <c:if test="${fn:contains(pageContext.request.requestURI, '/index')}">
                <i class="fa fa-times" onclick="hideMenu()"></i>
            </c:if>


            <ul>
                <sec:authorize access="isAuthenticated()">
                    <a href="/index">Home</a>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <a href="/login/logout">Logout</a>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <a href="/login/logout">Logout</a>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <a href="/login/logout">Logout</a>
                </sec:authorize>
            </ul>

        </div>

        <c:if test="${fn:contains(pageContext.request.requestURI, '/index')}">
            <i class="fa fa-bars" onclick="showMenu()"></i>
        </c:if>

    </nav>

<body>
