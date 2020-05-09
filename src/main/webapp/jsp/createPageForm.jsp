<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Create Page</title>
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
        </script>
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/pages">Pages</a></li>
                <li class="active">Create Page</li>
            </ol>
            <div class="page-header">
                <h1>Create Page</h1>
            </div>
            <c:if test="${message != null}">
                <div class="alert alert-danger fade in alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <form class="form-horizontal" action="createPage" method="POST">
                <div class="form-group">
                    <label for="title" class="col-md-2 control-label">Title:</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="title" id="title" 
                               placeholder="Title" required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="content" class="col-md-2 control-label">Content:</label>
                    <div class="col-md-10">
                        <textarea class="form-control" name="content" id="content"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="status" class="col-md-2 control-label">Status:</label>
                    <div class="col-md-10">
                        <select class="form-control" name="status" id="status" required="required">
                            <option value="" selected="selected">Select a Status</option>
                            <c:forEach var="currentStatus" items="${statusList}">
                                <option value="${currentStatus.statusId}">
                                    <c:out value="${currentStatus.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="pages" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Create Page</button>
                    </div>
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
