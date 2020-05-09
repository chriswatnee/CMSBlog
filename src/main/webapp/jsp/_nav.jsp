<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Small Company</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="${param.page == 0 ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}">Home</a>
            </li>
            <c:forEach var="currentPage" items="${navbarPageList}">
                <li class="${param.page == currentPage.pageId ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/page?pageId=${currentPage.pageId}"><c:out value="${currentPage.title}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>
</nav>