<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Posts by Category: <c:out value="${category.name}"/></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp"/>
        <div class="container">
            <div class="page-header">
                <h1>Posts by Category: <c:out value="${category.name}"/></h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <c:forEach var="currentPost" items="${postList}">
                        <h3><a href="post?postId=${currentPost.postId}"><c:out value="${currentPost.title}"/></a></h3>
                        <c:out value="${currentPost.excerpt}" escapeXml="false"/>
                        <br>
                        <br>
                        <p><a href="post?postId=${currentPost.postId}" class="btn btn-primary" role="button">Continue Reading</a></p>
                    </c:forEach>
                    <p class="text-right"><a href="posts" class="btn btn-primary" role="button">All Posts</a></p>
                </div>
            </div>
        </div>
        <jsp:include page="_footer.jsp"/>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
