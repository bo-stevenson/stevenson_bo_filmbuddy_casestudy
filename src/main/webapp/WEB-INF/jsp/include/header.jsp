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
    <c:if test="${fn:contains(pageContext.request.requestURI, '/movie/movieSearch')}">
        <link rel="stylesheet" href="../../../pub/css/search.css">
    </c:if>
    <c:if test="${fn:contains(pageContext.request.requestURI, '/index')}">
        <link rel="stylesheet" href="pub/css/index.css">
    </c:if>
    <c:if test="${fn:contains(pageContext.request.requestURI, '/error')}">
        <link rel="stylesheet" href="pub/css/index.css">
    </c:if>
    <c:if test="${fn:contains(pageContext.request.requestURI, '/login/loginForm')}">
        <link rel="stylesheet" href="../../pub/css/login.css">
    </c:if>
    <c:if test="${fn:contains(pageContext.request.requestURI, '/user/register')}">
        <link rel="stylesheet" href="../../pub/css/register.css">
    </c:if>

    <c:if test="${fn:contains(pageContext.request.requestURI, '/user/userSearch')}">
        <link rel="stylesheet" href="../../pub/css/search.css">
    </c:if>

    <c:if test="${fn:contains(pageContext.request.requestURI, '/user/profile')}">
        <link rel="stylesheet" href="../../pub/css/profile.css">
    </c:if>

    <c:if test="${fn:contains(pageContext.request.requestURI, '/movie/createMovie')}">
        <link rel="stylesheet" href="../../pub/css/createMovie.css">
    </c:if>


    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600;700&display=swap"
          rel="stylesheet">
    <script src="https://use.fontawesome.com/9719aa7074.js"></script>
    <title>FilmBuddy</title>
</head>


<body>
<section class="header">
    <nav>
        <c:choose>
            <c:when test="${fn:contains(pageContext.request.requestURI, '/index')}">
                <img src="pub/images/film-icon.png">
            </c:when>
            <c:otherwise>
                <img src="../../pub/images/film-icon.png">
            </c:otherwise>
        </c:choose>


            <a class="brand" href="/index">FilmBuddies</a>


        <div class="nav-links" id="navLinks">

            <sec:authorize access="isAuthenticated()">
                <i class="fa fa-times" onclick="hideMenu()"></i>
            </sec:authorize>

            <ul>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="/index">Home</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="/user/profile">My Profile</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="/movie/search">Search Movies</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="/user/search">Search User</a></li>
                </sec:authorize>

                <sec:authorize access="hasAuthority('ADMIN')">
                    <li><a href="/movie/add">Create a Movie</a></li>
                </sec:authorize>


                <sec:authorize access="isAuthenticated()">
                    <li><a href="/login/logout">Logout</a></li>
                </sec:authorize>
            </ul>

        </div>

        <sec:authorize access="isAuthenticated()">
            <i class="fa fa-bars" onclick="showMenu()"></i>
        </sec:authorize>

    </nav>


