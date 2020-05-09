<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Edit Page: ${page.title}</title>
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
                <li class="active">Edit Page: <c:out value="${page.title}"/></li>
            </ol>
            <div class="page-header">
                <h1>Edit Page: <c:out value="${page.title}"/></h1>
            </div>
            <c:if test="${message != null}">
                <div class="alert alert-danger fade in alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <sf:form class="form-horizontal" modelAttribute="page" action="editPage" method="POST">
                <div class="form-group">
                    <label for="title" class="col-md-2 control-label">Title:</label>
                    <div class="col-md-10">
                        <sf:input type="text" class="form-control" id="title" path="title" placeholder="Page Title" required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="content" class="col-md-2 control-label">Content:</label>
                    <div class="col-md-10">
                        <sf:textarea class="form-control" id="content" path="content"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="status" class="col-md-2 control-label">Status:</label>
                    <div class="col-md-10">
                        <sf:select class="form-control" id="status" path="status" 
                                   required="required">
                            <sf:options items="${statusList}" itemValue="statusId" itemLabel="name"/>
                        </sf:select>
                        <sf:hidden path="pageId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="pages" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Update Page</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
