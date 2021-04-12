<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<%--    <link rel="stylesheet" type="text/css"--%>
<%--          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />--%>
<%--    <c:url value="/css/main.css" var="jstlCss" />--%>
<%--    <link href="${jstlCss}" rel="stylesheet" />--%>

    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>

</head>
<body>
<div class="container">
    <header>
        <h1>Scheduling System</h1>
    </header>
    <div class="starter-template">
        <h1>Courses List</h1>
        <table class="table table-striped table-hover table-condensed table-bordered">
            <tr>
                <th>Id</th>
                <th>Code</th>
                <th>Title</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.code}</td>
                    <td>${course.title}</td>
                    <td>
                        <a class="btn btn-primary" href="/courses/${course.id}" role="button">
                            Edit
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="/courses/delete/${course.id}" role="button">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <a href="/courses/create">Create Course</a>
    </div>

</div>
</body>

</html>
