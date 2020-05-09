<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - <c:out value="${post.title}"/></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h1><c:out value="${post.title}"/></h1>
                    <fmt:parseDate value="${post.liveDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
                    <p>By <c:out value="${author.firstName}"/> <c:out value="${author.lastName}"/> on <fmt:formatDate value="${parsedDate}" type="date" pattern="MM/dd/yyyy"/></p>
                    <hr>
                    <p>Published in <a href="postsByCategory?categoryId=${post.category.categoryId}"><c:out value="${post.category.name}"/></a></p>
                    <p>Tags:<c:forEach var="currentTag" items="${post.tags}"> <a href="postsByTag?tagId=${currentTag.tagId}" class="label label-primary"><c:out value="${currentTag.name}"/></a></c:forEach></p>
                    <hr>
                    <c:out value="${post.content}" escapeXml="false"/>
                </div>
                <div class="col-md-4">
                    <h3>Recent Posts in this Categories</h3>
                    <ul class="list-unstyled">
                        <c:forEach var="currentPost" items="${postList}">
                            <c:choose>
                                <c:when test="${currentPost.postId == post.postId}">
                                    <li><c:out value="${currentPost.title}"/> (Current post)</li>
                                </c:when>    
                                <c:otherwise>
                                    <li><a href="post?postId=${currentPost.postId}"><c:out value="${currentPost.title}"/></a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
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
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
