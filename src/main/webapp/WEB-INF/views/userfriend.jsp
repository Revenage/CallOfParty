<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Registration Form</h2>

<form:form method="POST" modelAttribute="userFriend">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="name">Name: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="email">Email: </label> </td>
            <td><form:input path="email" id="email"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Save"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
