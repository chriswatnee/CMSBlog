<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp">
            <jsp:param name="page" value="0"/>
        </jsp:include>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h2>Recent posts</h2>
                    <c:forEach var="currentPost" items="${postList}">
                        <h3><a href="post?postId=${currentPost.postId}"><c:out value="${currentPost.title}"/></a></h3>
                        <c:out value="${currentPost.excerpt}" escapeXml="false"/>
                        <br>
                        <br>
                        <p><a href="post?postId=${currentPost.postId}" class="btn btn-primary" role="button">Continue Reading</a></p>
                    </c:forEach>
                    <p class="text-right"><a href="posts" class="btn btn-primary" role="button">All Posts</a></p>
                </div>
                <div class="col-md-4">
                    <h3>Tags</h3>
                    <p><c:forEach var="currentTag" items="${tagList}"> <a href="postsByTag?tagId=${currentTag.tagId}" class="label label-primary"><c:out value="${currentTag.name}"/></a></c:forEach></p>
                    <h3>Categories</h3>
                    <ul class="list-unstyled">
                        <c:forEach var="currentCategory" items="${categoryList}">
                            <li><a href="postsByCategory?categoryId=${currentCategory.categoryId}"><c:out value="${currentCategory.name}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <jsp:include page="_footer.jsp"/>
        <div class="container">
            <p><a href="admin">Admin</a></p>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
