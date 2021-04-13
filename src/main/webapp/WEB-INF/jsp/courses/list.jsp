<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

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
        <h1>Courses</h1>

        <c:if test="${courses.size() > 0}">
            <div class="input-group">
                <form:form method="get" action="/courses/search" class="input-group">
                    <input type="search" class="form-control" placeholder="Search"
                           name="keyword" aria-label="Search" aria-describedby="search-addon" />
                    <button type="submit" class="btn btn-outline-primary">Search</button>
                </form:form>
            </div>
            <br/>

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
                            <a class="btn btn-success" href="/courses/${course.id}" role="button">
                                Edit
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-danger" href="/courses/delete/${course.id}"
                               onclick="return confirm('Do you really want to delete?')"
                               role="button">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <br/>
        <a href="/courses/create">Create Course</a>
    </div>

</div>
</body>

</html>
