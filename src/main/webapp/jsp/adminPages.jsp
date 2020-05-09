<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Pages</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp">
            <jsp:param name="page" value="pages"/>
        </jsp:include>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li class="active">Pages</li>
            </ol>
            <div class="page-header">
                <h1>Pages</h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <p><a href="${pageContext.request.contextPath}/admin/createPageForm" class="btn btn-primary" role="button">Create Page</a></p>
                    <h3>List of Pages</h3>
                    <table class="table table-hover">
                        <tr>
                            <th width="50%">Page</th>
                            <th width="30%">Status</th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="currentPage" items="${pageList}">
                            <tr>
                                <td>
                                    <a href="page?pageId=${currentPage.pageId}"><c:out value="${currentPage.title}"/></a>
                                </td>
                                <td>
                                    <c:out value="${currentPage.status.name}"/>
                                </td>
                                <td>
                                    <a href="editPageForm?pageId=${currentPage.pageId}">Edit</a>
                                     | 
                                    <a href="#" onclick="return showDeleteConfirmation('Page', '${currentPage.pageId}', '${currentPage.title}')">Delete</a>
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
