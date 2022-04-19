<jsp:include page="../include/header.jsp" />

</section>
<div class="login-box">
    <img src="../pub/images/user-icon.jpg" class="avatar">
    <h1>Sign up</h1>

    <form action="/user/registerSubmit" method="GET" class="form" id="form">

        <label>First Name</label>
        <input type="text" name="firstName" placeholder="First Name" id="firstName">
        <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Last Name</label>
        <input type="text" name="lastName" placeholder="Last Name" id="lastName">
        <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Email</label>
        <input type="text" name="email" placeholder="Email" id="email">
        <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Password</label>
        <input type="password" name="password" placeholder="Password" id="password">
        <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Confirm Password</label>
        <input type="password" name="confirmPassword" placeholder="Confirm Password" id="confirmPassword">
        <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <button class="button" type="submit">Submit</button>

        <p>Have an account already? <a href="/login/login">Log in</a></p>

    </form>
</div>
<jsp:include page="../include/footer.jsp" />