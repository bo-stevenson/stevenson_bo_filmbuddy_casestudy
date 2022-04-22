<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="footer">
    <h4>Follow us on these platforms to keep up-to-date!</h4>
    <div class="icons">
        <i class="fa fa-facebook"></i>
        <i class="fa fa-twitter"></i>
        <i class="fa fa-instagram"></i>
        <i class="fa fa-linkedin"></i>
    </div>



</section>

<c:choose>
    <c:when test="${fn:contains(pageContext.request.requestURI, '/index')}">
        <script src="pub/js/index.js"></script>
    </c:when>
    <c:when test="${fn:contains(pageContext.request.requestURI, '/user/profile')}">
        <script src="../../../pub/js/profile.js"></script>
        <script src="../../../pub/js/index.js"></script>
    </c:when>
    <c:otherwise>
        <script src="../pub/js/index.js"></script>
    </c:otherwise>
</c:choose>


</body>
</html>