<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Edit Tag: ${tag.name}</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/tags">Tags</a></li>
                <li class="active">Edit Tag: <c:out value="${tag.name}"/></li>
            </ol>
            <div class="page-header">
                <h1>Edit Tag: <c:out value="${tag.name}"/></h1>
            </div>
            <sf:form class="form-horizontal" modelAttribute="tag" action="editTag" method="POST">
                <div class="form-group">
                    <label for="name" class="col-md-2 control-label">Name:</label>
                    <div class="col-md-10">
                        <sf:input type="text" class="form-control" id="name" path="name" placeholder="Tag Name" maxlength="50" required="required"/>
                        <sf:errors path="name" cssClass="error"/>
                        <sf:hidden path="tagId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="tags" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Update Tag</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
