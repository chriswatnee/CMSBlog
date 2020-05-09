<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Edit Post: ${post.title}</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tiny.cloud/1/ctq9ebl1rz51ftalehdhttyqgdh5rrapcspzlz8kkkimaoev/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script>
            tinymce.init({
                selector: '#content',
                plugins: 'image',
                relative_urls: false,
                image_list: [
                    <c:forEach var="currentImage" items="${imageList}" varStatus="status">
                        {
                            title: '<c:out value="${currentImage.name}"/>',
                            value: '${pageContext.request.contextPath}/images/${currentImage.name}'
                        }<c:if test="${!status.last}">,</c:if>
                    </c:forEach>
                ]
            });
            tinymce.init({
                selector: '#excerpt',
                plugins: 'image',
                relative_urls: false,
                image_list: [
                    <c:forEach var="currentImage" items="${imageList}" varStatus="status">
                        {
                            title: '<c:out value="${currentImage.name}"/>',
                            value: '${pageContext.request.contextPath}/images/${currentImage.name}'
                        }<c:if test="${!status.last}">,</c:if>
                    </c:forEach>
                ]
            });
        </script>
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li><a href="${referer}"><c:out value="${referer == 'posts' ? 'Posts' : 'My Draft Posts'}"/></a></li>
                <li class="active">Edit Post: <c:out value="${post.title}"/></li>
            </ol>
            <div class="page-header">
                <h1>Edit Post: <c:out value="${post.title}"/></h1>
            </div>
            <c:if test="${message != null}">
                <div class="alert alert-danger fade in alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <sf:form class="form-horizontal" modelAttribute="post" action="editPost" method="POST">
                <div class="form-group">
                    <label for="title" class="col-md-2 control-label">Title:</label>
                    <div class="col-md-10">
                        <sf:input type="text" class="form-control" id="title" path="title" 
                                  placeholder="Post Title" required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="content" class="col-md-2 control-label">Content:</label>
                    <div class="col-md-10">
                        <sf:textarea class="form-control" id="content" path="content"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="excerpt" class="col-md-2 control-label">Excerpt:</label>
                    <div class="col-md-10">
                        <sf:textarea class="form-control" id="excerpt" path="excerpt"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="liveDate" class="col-md-2 control-label">Live Date:</label>
                    <div class="col-md-10">
                        <sf:input type="date" class="form-control" id="liveDate" path="liveDate" required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="expirationDate" class="col-md-2 control-label">Expiration Date (optional):</label>
                    <div class="col-md-10">
                        <sf:input type="date" class="form-control" id="expirationDate" path="expirationDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="category" class="col-md-2 control-label">Category:</label>
                    <div class="col-md-10">
                        <sf:select class="form-control" id="category" path="category" required="required">
                            <sf:options items="${categoryList}" itemValue="categoryId" itemLabel="name"/>
                        </sf:select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tags" class="col-md-2 control-label">Tags (optional):</label>
                    <div class="col-md-10">
                        <sf:select class="form-control" id="tags" path="tags" 
                                   multiple="true" size="10">
                            <sf:options items="${tagList}" itemValue="tagId" itemLabel="name"/>
                        </sf:select>
                        <input type="hidden" name="referer" id="referer" value="${referer}">
                        <sf:hidden path="postId"/>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="form-group">
                        <label for="status" class="col-md-2 control-label">Status:</label>
                        <div class="col-md-10">
                            <sf:select class="form-control" id="status" path="status" required="required">
                                <sf:options items="${statusList}" itemValue="statusId" itemLabel="name"/>
                            </sf:select>
                        </div>
                    </div>
                </sec:authorize>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="${referer}" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Update Post</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
