<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>
<div class="container">
    <h1>Search User</h1>

    <br>
    <form action="/user/search" method="GET">
        First Name : <input type="text" name="firstName" value="${firstName}">
        <button type="submit">Submit</button>
    </form>

    <br>


    <div class="table-container">
        <c:if test="${not empty firstName}">
            <h3>Search Results Found ${usersModelKey.size()}</h3>
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
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Follow</th>
            </tr>
            <c:forEach items="${usersModelKey}" var="user">
                <tr scope="row">
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td><a href="/user/follow/${user.id}">Follow</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</section>
<jsp:include page="../include/footer.jsp"/>