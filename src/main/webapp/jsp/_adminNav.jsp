<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Small Company</a>
        </div>
        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}">Home</a>
            </li>
            <li class="${param.page == 'admin' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/admin">Admin</a>
            </li>
            <li class="${param.page == 'myDraftPosts' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/admin/myDraftPosts">My Draft Posts</a>
            </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="${param.page == 'posts' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/posts">Posts</a>
                </li>
                <li class="${param.page == 'pages' ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/admin/pages">Pages</a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <div class="container">
        <p class="text-right">${pageContext.request.userPrincipal.name}
            | <a href="<c:url value="/j_spring_security_logout"/>" > Logout</a>
        </p>
    </div>
</c:if>