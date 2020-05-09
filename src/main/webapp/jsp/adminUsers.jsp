<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Users</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li class="active">Users</li>
            </ol>
            <div class="page-header">
                <h1>Users</h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <p><a href="${pageContext.request.contextPath}/admin/createUserForm" class="btn btn-primary" role="button">Create User</a></p>
                    <h3>List of Users</h3>
                    <table class="table table-hover">
                        <tr>
                            <th width="30%">Username</th>
                            <th width="25%">First Name</th> 
                            <th width="25%">Last Name</th> 
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="currentUser" items="${userList}">
                            <tr>
                                <td>
                                    <c:out value="${currentUser.username}"/>
                                </td>
                                <td>
                                    <c:out value="${currentUser.firstName}"/>
                                </td>
                                <td>
                                    <c:out value="${currentUser.lastName}"/>
                                </td>
                                <td>
                                    <a href="#" onclick="return showDeleteConfirmation('User', '${currentUser.username}', '${currentUser.username}')">Delete</a>
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
