<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Small Company - <c:out value="${page.title}"/></title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp">
            <jsp:param name="page" value="${page.pageId}"/>
        </jsp:include>
        <div class="container">
            <div class="page-header">
                <h1><c:out value="${page.title}"/></h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <c:out value="${page.content}" escapeXml="false"/>
                </div>
            </div>
        </div>
        <jsp:include page="_footer.jsp"/>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
