<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Images</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li class="active">Images</li>
            </ol>
            <div class="page-header">
                <h1>Images</h1>
            </div>           
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <c:forEach var="currentImage" items="${imageList}">
                            <div class="col-md-4">
                                <div class="thumbnail">
                                    <img src="${pageContext.request.contextPath}/images/${currentImage.name}" alt="" style="width:100%">
                                    <div class="caption">
                                        <p>${currentImage.name}</p>
                                        <p><a href="#" onclick="return showDeleteConfirmation('Image', '${currentImage.imageId}', '${currentImage.name}')">Delete</a></p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-6">
                    <h3>Upload Image</h3>
                    <c:if test="${message != null}">
                        <div class="alert alert-danger fade in alert-dismissible">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <c:out value="${message}"/>
                        </div>
                    </c:if>
                    <form class="form-horizontal" action="createImage" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label">Name:</label>
                            <div class="col-md-10">
                                <input type="file" class="form-control" name="name" id="name" 
                                          accept=".jpg, .jpeg, .png" required="required"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Upload Image</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="_deleteConfirmation.jsp"/>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/cms-blog.js"></script>
    </body>
</html>
