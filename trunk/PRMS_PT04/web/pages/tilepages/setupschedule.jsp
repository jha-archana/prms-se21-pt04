<%-- 
    Document   : setupschedule
    Created on : Sep 12, 2013, 11:06:11 AM
    Author     : Jha Archana
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<fmt:setBundle basename="ApplicationResources" />

<title><fmt:message key="title.setupschedule" /></title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/controller/setupschedule" method=post>
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<td><fmt:message key="label.maintainSchedule.duration" /></td>
					<td>
                                            <input type="text" name="duration" value="${param['duration']}" size=15
                                                    maxlength=20>
                                            <input type="hidden" name="ins" value="true" />
                                        </td>
				</tr>
				<tr>
					<td><fmt:message key="label.maintainSchedule.dateOfProgram" /></td>
					<td><input type="text" name="dateOfProgram"
						value="${param['dateOfProgram']}" size=45 maxlength=20></td>
				</tr>
				<tr>
					<td><fmt:message key="label.maintainSchedule.startTime" /></td>
					<td><input type="text" name="startTime"
						value="${param['startTime']}" size=15 maxlength=20></td>
				</tr>
                                <tr>
					<td><fmt:message key="label.maintainSchedule.programName" /></td>
					<td><input type="text" name="radioProgram"
						value="${param['radioProgram']}" size=15 maxlength=20></td>
				</tr>
                                <tr>
					<td><fmt:message key="label.maintainSchedule.presenterName" /></td>
					<td><input type="text" name="presenter"
						value="${param['presenter']}" size=15 maxlength=20></td>
				</tr>
                                <tr>
					<td><fmt:message key="label.maintainSchedule.producerName" /></td>
					<td><input type="text" name="producer"
						value="${param['producer']}" size=15 maxlength=20></td>
				</tr>
			</table>
		</center>
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>

</body>
</html>
