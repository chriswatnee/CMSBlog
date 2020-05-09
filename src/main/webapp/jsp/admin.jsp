<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Admin</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp">
            <jsp:param name="page" value="admin"/>
        </jsp:include>
        <div class="container">
            <div class="page-header">
                <h1>Admin</h1>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">My Draft Posts</div>
                        <div class="panel-body">
                            <ul class="list-unstyled">
                                <c:forEach var="currentMyPost" items="${myDraftPostList}">
                                    <li><c:out value="${currentMyPost.title}"/></li>
                                </c:forEach>
                            </ul>
                            <p><a href="${pageContext.request.contextPath}/admin/myDraftPosts" class="btn btn-primary" role="button">My Draft Posts</a></p>
                        </div>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Posts</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentPost" items="${postList}">
                                        <li><c:out value="${currentPost.title}"/></li>
                                    </c:forEach>
                                </ul>
                                <p><a href="${pageContext.request.contextPath}/admin/posts" class="btn btn-primary" role="button">All Posts</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Pages</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentPage" items="${pageList}">
                                        <li><c:out value="${currentPage.title}"/></li>
                                    </c:forEach>
                                </ul>
                                <p><a href="${pageContext.request.contextPath}/admin/pages" class="btn btn-primary" role="button">All Pages</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Images</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentImage" items="${imageList}">
                                        <li><c:out value="${currentImage.name}"/></li>
                                    </c:forEach>
                                </ul>
                                <p><a href="${pageContext.request.contextPath}/admin/images" class="btn btn-primary" role="button">All Images</a></p>
                            </div>
                        </div>
                    </div>
                </sec:authorize>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="row">
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Navigations</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentNavigation" items="${navigationList}">
                                        <li><a href="admin/editNavigationForm?navigationId=${currentNavigation.navigationId}"><c:out value="${currentNavigation.section}"/></a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Categories</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentCategory" items="${categoryList}">
                                        <li><c:out value="${currentCategory.name}"/></li>
                                    </c:forEach>
                                </ul>
                                <p><a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-primary" role="button">All Categories</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Tags</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentTag" items="${tagList}">
                                        <li><c:out value="${currentTag.name}"/></li>
                                    </c:forEach>
                                </ul>
                                <p><a href="${pageContext.request.contextPath}/admin/tags" class="btn btn-primary" role="button">All Tags</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-primary">
                            <div class="panel-heading">Users</div>
                            <div class="panel-body">
                                <ul class="list-unstyled">
                                    <c:forEach var="currentUser" items="${userList}">
                                        <li><c:out value="${currentUser.username}"/></li>
                                    </c:forEach>
                                </ul>
                                <p><a href="${pageContext.request.contextPath}/admin/users" class="btn btn-primary" role="button">All Users</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </sec:authorize>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
