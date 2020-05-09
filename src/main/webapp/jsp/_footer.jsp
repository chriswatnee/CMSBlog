<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<footer>
    <div class="container">
        <br>
        <hr>
        <ul class="list-inline">
            <c:forEach var="currentPage" items="${footerPageList}">
                <li>
                    <a href="${pageContext.request.contextPath}/page?pageId=${currentPage.pageId}"><c:out value="${currentPage.title}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>
</footer>