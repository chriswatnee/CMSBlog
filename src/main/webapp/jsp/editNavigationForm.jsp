<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Edit Navigation: ${navigation.section}</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li class="active">Edit Navigation: <c:out value="${navigation.section}"/></li>
            </ol>
            <div class="page-header">
                <h1>Edit Navigation: <c:out value="${navigation.section}"/></h1>
            </div>
            <sf:form class="form-horizontal" modelAttribute="navigation" action="editNavigation" method="POST">
                <div class="form-group">
                    <label for="pages" class="col-md-2 control-label">Pages:</label>
                    <div class="col-md-10">
                        <sf:select class="form-control" id="pages" path="pages" multiple="true" size="10">
                            <sf:options items="${pageList}" itemValue="pageId" itemLabel="title"/>
                        </sf:select>
                        <sf:hidden path="navigationId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="${pageContext.request.contextPath}/admin" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Update Navigation</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
