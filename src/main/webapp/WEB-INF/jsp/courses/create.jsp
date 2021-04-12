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
        <h1>Create Course</h1>
        <form:form method="post" modelAttribute="course" action="/courses/create" cssClass="form">
            <table>
                <tr>
                    <td>Code: </td>
                    <td><form:input path="code"/></td>
                </tr>
                <tr>
                    <td>Title: </td>
                    <td><form:input path="title"/></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><form:input path="description"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Create"/></td>
                </tr>
            </table>
        </form:form>

        <br/>
        <a href="/courses/list">List Courses</a>
    </div>

</div>
</body>

</html>
