<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/header.jsp" />

<div class = "row">
<div class="container">
    <aside>
        <div class="navbar">

            <div class="user-logo"><img src="../../../pub/images/default-profile.jpg" /></div>

            <p class="firstName" id="firstName">${firstName} ${lastName}</p>
            <nav>

                <ul>

                    <li class="selectedLink" name="movies">Favorite Movies</li>
                    <li name="following">Following</li>
                    <li name="followers">Followers</li>

                </ul>
            </nav>
        </div>
    </aside>
    <main>

        <div class="card active movies" data-movies>

            <div class="title">Favorite Movies</div>

            <div class="content">

                <table class="table content-table">
                    <tr scope="row">
                        <th>Title</th>
                        <th>Description</th>
                        <th>Genre</th>
                        <th>Release Date</th>
                    </tr>

                    <c:forEach items="${userMovies}" var="movie">
                        <tr scope="row">
                            <td>${movie.title}</td>
                            <td>${movie.description}</td>
                            <td>${movie.genre}</td>
                            <td>${movie.releaseDate}</td>
                            <td><a class="btn" href="/movie/delete/${movie.id}">Delete</a></td>
                        </tr>
                    </c:forEach>


                </table>
            </div>
        </div>

        <div class="card following" data-following>

            <div class="title">Following</div>

            <div class="content">

                <table class="table content-table">

                    <tr scope="row">
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>

                    <c:forEach items="${following}" var="following">
                        <tr scope="row">
                            <td>${following.email}</td>
                            <td>${following.firstName}</td>
                            <td>${following.lastName}</td>
                            <td><a class="btn" href="/follower/profile/${following.id}">Profile</a></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>

        <div class="card followers" data-followers>

            <div class="title">Followers</div>

            <div class="content">

                <table class="table content-table">

                    <tr scope="row">
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>

                    <c:forEach items="${followers}" var="followers">
                        <tr scope="row">
                            <td>${followers.email}</td>
                            <td>${followers.firstName}</td>
                            <td>${followers.lastName}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </main>
</div>

</div>
</section>




<jsp:include page="../include/footer.jsp" />