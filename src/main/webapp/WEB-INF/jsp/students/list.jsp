<!DOCTYPE html>
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
        <h1>Student List</h1>
        <table class="table table-striped table-hover table-condensed table-bordered">
            <tr>
                <th>Id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>
                        <a class="btn btn-primary" href="/students/${student.id}" role="button">
                            Edit
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-primary"
                           href="/students/delete/${student.id}"
                           onclick="return confirm('Do you really want to delete?')"
                           role="button">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <a href="/students/create">Create Student</a>
    </div>

</div>
</body>

</html>
