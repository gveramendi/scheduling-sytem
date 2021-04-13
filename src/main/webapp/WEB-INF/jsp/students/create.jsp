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
        <h1>Create Student</h1>
        <form:form method="post" modelAttribute="student" action="/students/create" cssClass="form">
            <div class="form-group">
                <label for="firstName">First name:</label>
                <form:input path="firstName" class="form-control" placeholder="First name" />
            </div>

            <div class="form-group">
                <label for="lastName">Last name:</label>
                <form:input path="lastName" class="form-control" placeholder="Last name" />
            </div>

            <button type="submit" class="btn btn-primary">Create</button>
            <a class="btn btn-secondary" href="/students/list">Cancel</a>
        </form:form>
    </div>

</div>
</body>

</html>
