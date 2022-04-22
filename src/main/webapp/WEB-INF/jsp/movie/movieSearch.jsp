<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp"/>


<div class="container">
    <h1>Search for a Movie</h1>
    <form action="/movie/search" method="GET">
        Title : <input type="text" name="title" value="${title}">
        <button type="submit">Search</button>
    </form>

    <br>


    <div class="table-container">
        <c:if test="${not empty title}">
            <h3>Search Results Found ${moviesModelKey.size()}</h3>
            <br>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <h6 class="error">${errorMessage}</h6>
        </c:if>
        <c:if test="${empty errorMessage}">
            <h6 class="success">${successMessage}</h6>
        </c:if>
        <table class="table content-table">
            <tr scope="row">
                <th>Title</th>
                <th>Description</th>
                <th>Genre</th>
                <th>Release Date</th>
            </tr>
            <c:forEach items="${moviesModelKey}" var="movie">
                <tr scope="row">
                    <td>${movie.title}</td>
                    <td>${movie.description}</td>
                    <td>${movie.genre}</td>
                    <td>${movie.releaseDate}</td>
                    <td><a class="btn" href="/movie/favorite/${movie.id}">Add to favorites</a></td>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <td><a class="btn" href="/movie/edit/${movie.id}">Edit Movie</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <td><a class="btn" href="/movie/delete/${movie.id}">Delete Movie</a></td>
                    </sec:authorize>


                </tr>
            </c:forEach>
        </table>
    </div>


</div>
</section>

<jsp:include page="../include/footer.jsp"/>