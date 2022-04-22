<jsp:include page="include/header.jsp" />
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



    <div class="text-box">
        <h1>Welcome to FilmBuddies</h1>
        <sec:authorize access="!isAuthenticated()">
            <p>Share and review your favorite movies with friends. Sign up here to get started!</p>
            <a href="/user/register" class="hero-btn">Sign Up</a>
            <small>OR</small>
            <a href="/login/login" class="hero-btn">Log In</a>
        </sec:authorize>

        <p>How would you rate our site?</p>
        <input type="radio" name="review" id="great"value="great">
        <label for="great">Great</label><br>
        <input type="radio" id="awesome"name="review" value="awesome">
        <label for="awesome">Awesome</label><br>
        <input type="radio" id="best" name="review"value="best">
        <label for="best">The Best</label>

        <p>Do you like movies?</p>
        <input type="checkbox" id="choice1">
        <label for="choice1"> Yes</label><br>

        <label for="genre">Choose a genre:</label>

        <select name="genre" id="genre">
            <option >Horror</option>
            <option >Action</option>
            <option >Comedy</option>
            <option >Romance</option>
        </select>
    </div>


</section>

<section class="about">
    <h1>How it Works</h1>
    <p>There are many ways to use FilmBuddies!</p>

    <div class="row">
        <div class="about-col">
            <img src="pub/images/guy-watching-movie.jpg">
            <h3>Track what you watch</h3>
            <p>With FilmBuddies you can make a log of what movies you have been watching.</p>
        </div>

        <div class="about-col">
            <img src="pub/images/smiling-computer.jpg">
            <h3>Rank your favorite films</h3>
            <p>Rank your favorite films in a top ten list to show others what you liked the most!</p>
        </div>

        <div class="about-col">
            <img src="pub/images/friends-watching-movie.jpg">
            <h3>Share with friends</h3>
            <p>You can share your watch history
                and Top 10 favorites with friends! Just follow their account to see!</p>
        </div>
    </div>

</section>

<jsp:include page="include/footer.jsp" />