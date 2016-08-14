<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>University Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Events</h2>
	<table>
		<tr>
			<td>NAME</td><td>Joining Date</td><td></td><td></td>
		</tr>
		<c:forEach items="${userEvents}" var="userEvent">
			<tr>
			<td>${userEvent.name}</td>
			<td>${userEvent.joiningDate}</td>
			<td><a href="<c:url value='/edit-${userEvent.id}-userevent' />">edit</a></td>
			<td><a href="<c:url value='/delete-${userEvent.id}-userevent' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New Event</a>
</body>
</html>