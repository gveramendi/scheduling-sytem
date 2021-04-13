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
        <h1>Edit Student</h1>
        <form:form method="post" modelAttribute="student" action="/students/update/${student.id}" >
            <div class="form-group">
                <label for="firstName">First name:</label>
                <form:input path="firstName" class="form-control" placeholder="First name" />
            </div>

            <div class="form-group">
                <label for="lastName">Last name:</label>
                <form:input path="lastName" class="form-control" placeholder="Last name" />
            </div>

            <button type="submit" class="btn btn-primary">Update</button>
            <a class="btn btn-secondary" href="/students/list">Cancel</a>
        </form:form>

        <br/>
        <h1>Add Courses</h1>
        <div class="input-group">
            <form:form method="get" modelAttribute="selectedCourse"
                       action="/students/add_course/${student.id}" class="input-group" >
                <form:select id="courseList" path="id" class="form-control" >
                    <form:options items="${courseList}" itemLabel="title"/>
                </form:select>
                <button type="submit" class="btn btn-outline-primary">Add Course</button>
            </form:form>
        </div>

        <c:if test="${courses.size() > 0}">
            <br/>
            <table class="table table-striped table-hover table-condensed table-bordered">
                <tr>
                    <th>Id</th>
                    <th>Code</th>
                    <th>Title</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td>${course.id}</td>
                        <td>${course.code}</td>
                        <td>${course.title}</td>
                        <td>
                            <a class="btn btn-danger" href="/students/remove_course/${student.id}/${course.id}"
                               onclick="return confirm('Do you really want to delete this course?')"
                               role="button">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>

</html>
