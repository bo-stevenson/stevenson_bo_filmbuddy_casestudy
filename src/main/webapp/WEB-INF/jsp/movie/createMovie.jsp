<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

</section>
<div class="login-box">
    <img src="../../../pub/images/film-icon.png" class="avatar">

    <c:if test="${empty form.id}">
        <h1>Add a Movie to DB</h1>
    </c:if>


    <c:if test="${not empty form.id}">
        <h1>Edit Movie</h1>
    </c:if>

    <form action="/movie/movieSubmit" method="post" class="form" id="form">
        <input type="hidden" name="id" value="${form.id}">
        <label>Title</label>
        <input type="text" name="title" placeholder="Title" id="title" value="${form.title}">
        <c:forEach items='${bindingResult.getFieldErrors("title")}' var="error">
            <div style="font-size:12px; margin-top: -15px; color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Description</label>
        <textarea type="text-box" name="description" placeholder="Description" id="description" value="${form.description}"></textarea>
        <c:forEach items='${bindingResult.getFieldErrors("description")}' var="error">
            <div style="font-size:12px; margin-top: -15px; color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Genre</label>
        <input type="text" name="genre" placeholder="Genre" id="genre" value="${form.genre}">
        <c:forEach items='${bindingResult.getFieldErrors("genre")}' var="error">
            <div style="font-size:12px; margin-top: -5px; color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label>Release Date YYYY-MM-DD</label>
        <input type="text" name="releaseDate" placeholder="releaseDate" id="releaseDate" value="${form.releaseDate}">
        <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
            <div style="font-size:12px; margin-top: -5px; color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>



        <button class="button" type="submit">Submit</button>

    </form>
</div>
<jsp:include page="../include/footer.jsp" />