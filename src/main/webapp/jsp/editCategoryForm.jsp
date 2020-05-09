<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Edit Category: ${category.name}</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/categories">Categories</a></li>
                <li class="active">Edit Category: <c:out value="${category.name}"/></li>
            </ol>
            <div class="page-header">
                <h1>Edit Category: <c:out value="${category.name}"/></h1>
            </div>
            <sf:form class="form-horizontal" modelAttribute="category" action="editCategory" method="POST">
                <div class="form-group">
                    <label for="name" class="col-md-2 control-label">Name:</label>
                    <div class="col-md-10">
                        <sf:input type="text" class="form-control" id="name" path="name" placeholder="Category Name" maxlength="50" required="required"/>
                        <sf:errors path="name" cssClass="error"/>
                        <sf:hidden path="categoryId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="categories" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Update Category</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
