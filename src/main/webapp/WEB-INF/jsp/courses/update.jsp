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
        <h1>Edit Course</h1>
        <form:form method="post" modelAttribute="course" action="/courses/update/${course.id}" cssClass="form">
            <div class="form-group">
                <label for="code">Code:</label>
                <form:input path="code" class="form-control" placeholder="Code" />
            </div>

            <div class="form-group">
                <label for="code">Title:</label>
                <form:input path="title" class="form-control" placeholder="title" />
            </div>

            <div class="form-group">
                <label for="code">Description:</label>
                <form:textarea path="description" class="form-control" placeholder="description" />
            </div>

            <button type="submit" class="btn btn-primary">Update</button>
        </form:form>

        <br/>
        <a href="/courses/list">List Courses</a>
    </div>

</div>
</body>

</html>
