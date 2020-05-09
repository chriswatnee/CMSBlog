<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Business - Login</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Small Company</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="page-header">
                <h1>Login</h1>
            </div>
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <div class="row">
                <div class="col-sm-6 col-md-offset-3">
                    <form class="form-horizontal" role="form" method="POST" action="j_spring_security_check">
                        <div class="form-group">
                            <label for="j_username" class="col-sm-2 control-label">Username:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="j_username" placeholder="Username"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="j_password" class="col-sm-2 control-label">Password:</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="j_password" placeholder="Password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Sign In</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>   
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
