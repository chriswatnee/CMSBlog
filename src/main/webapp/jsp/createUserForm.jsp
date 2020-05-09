<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - Create User</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_adminNav.jsp"/>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/users">Users</a></li>
                <li class="active">Create User</li>
            </ol>
            <div class="page-header">
                <h1>Create User</h1>
            </div>
            <sf:form class="form-horizontal" modelAttribute="user" action="createUser" method="POST">
                <div class="form-group">
                    <label for="username" class="col-md-2 control-label">Username:</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="username" id="username" 
                               placeholder="Username" maxlength="20" required="required"/>
                        <sf:errors path="username" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label">Password:</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="password" id="password" 
                               placeholder="Password" maxlength="50" required="required"/>
                        <sf:errors path="password" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="firstName" class="col-md-2 control-label">First Name:</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="firstName" id="firstName" 
                               placeholder="First Name" maxlength="30" required="required"/>
                        <sf:errors path="firstName" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-md-2 control-label">Last Name:</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="lastName" id="lastName" 
                               placeholder="Last Name" maxlength="30" required="required"/>
                        <sf:errors path="lastName" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="isAdmin" class="col-md-2 control-label">Admin User:</label>
                    <div class="col-md-10">
                        <input type="checkbox" name="isAdmin" id="isAdmin" value="yes"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="users" class="btn btn-default" role="button">Cancel</a>
                        <button type="submit" class="btn btn-primary">Create User</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
