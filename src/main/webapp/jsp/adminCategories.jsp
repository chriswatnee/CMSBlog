<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Categories</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li class="active">Categories</li>
            </ol>
            <div class="page-header">
                <h1>Categories</h1>
            </div>
            <c:if test="${message != null}">
                <div class="alert alert-danger fade in alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:out value="${message}"/>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-6">
                    <h3>List of Categories</h3>
                    <table class="table table-hover">
                        <tr>
                            <th width="80%">Category</th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="currentCategory" items="${categoryList}">
                            <tr>
                                <td>
                                    <c:out value="${currentCategory.name}"/>
                                </td>
                                <td>
                                    <a href="editCategoryForm?categoryId=${currentCategory.categoryId}">Edit</a>
                                     | 
                                    <a href="#" onclick="return showDeleteConfirmation('Category', '${currentCategory.categoryId}', '${currentCategory.name}')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6">
                    <h3>Create Category</h3>
                    <sf:form class="form-horizontal" modelAttribute="category" action="createCategory" method="POST">
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label">Name:</label>
                            <div class="col-md-10">
                                <sf:input type="text" class="form-control" id="name" path="name" placeholder="Category Name" maxlength="50" required="required"/>
                                <sf:errors path="name" cssClass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Create Category</button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
        <jsp:include page="_deleteConfirmation.jsp"/>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/cms-blog.js"></script>
    </body>
</html>
