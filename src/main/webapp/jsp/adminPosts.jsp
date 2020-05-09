<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - <c:out value="${referer == 'posts' ? 'Posts' : 'My Draft Posts'}"/></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp">
            <jsp:param name="page" value="${referer}"/>
        </jsp:include>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li class="active"><c:out value="${referer == 'posts' ? 'Posts' : 'My Draft Posts'}"/></li>
            </ol>
            <div class="page-header">
                <h1><c:out value="${referer == 'posts' ? 'Posts' : 'My Draft Posts'}"/></h1>
            </div>
            <c:if test="${message != null}">
                <div class="alert alert-danger fade in alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-12">
                    <p><a href="${pageContext.request.contextPath}/admin/createPostForm?referer=${referer}" class="btn btn-primary" role="button">Create Post</a></p>
                    <h3>List of Posts</h3>
                    <table class="table table-hover">
                        <tr>
                            <th width="25%">Post</th>
                            <th width="15%">Creation Date</th>
                            <th width="15%">Live Date</th>
                            <th width="15%">Expiration Date</th>
                            <th width="15%">Status</th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentPost" items="${postList}">
                            <tr>
                                <td>
                                    <a href="post?postId=${currentPost.postId}&referer=${referer}"><c:out value="${currentPost.title}"/></a>
                                </td>
                                <td>
                                    <c:out value="${currentPost.creationDate}"/>
                                </td>
                                <td>
                                    <c:out value="${currentPost.liveDate}"/>
                                </td>
                                <td>
                                    <c:out value="${currentPost.expirationDate}"/>
                                </td>
                                <td>
                                    <c:out value="${currentPost.status.name}"/>
                                </td>
                                <td>
                                    <a href="editPostForm?postId=${currentPost.postId}&referer=${referer}">Edit</a>
                                     | 
                                    <a href="#" onclick="return showDeleteConfirmation('Post', '${currentPost.postId}', '${currentPost.title}')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="_deleteConfirmation.jsp"/>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/cms-blog.js"></script>
    </body>
</html>
