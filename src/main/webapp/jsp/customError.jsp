<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Small Company - Error</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="_nav.jsp">
            <jsp:param name="page" value="0"/>
        </jsp:include> 
        <div class="container">
            <div>
                <h1>An error has occurred:</h1>
                <h3>${errorMessage}</h3>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        </div>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>
