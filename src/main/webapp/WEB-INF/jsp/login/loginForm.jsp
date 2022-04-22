<jsp:include page="../include/header.jsp" />
</section>
<div class="login-box">
    <img src="../pub/images/user-icon.jpg" class="avatar">
    <h1>Log in</h1>

    <form action="/login/loginSubmit" method="POST" class="form">
        <i class="fa fa-envelope"></i>
        <input type="text" name="username" placeholder="Enter email">
        <br>
        <i class="fa fa-lock"></i>
        <input type="password" name="password" placeholder="Enter password">

        <button class="btn" type="submit">Submit</button>

        <p>Don't have an account? <a href="/user/register">Sign up</a></p>

    </form>
</div>
<jsp:include page="../include/footer.jsp" />