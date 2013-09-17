<%-- 
    Document   : searchsched
    Created on : Sep 16, 2013, 2:18:24 PM
    Author     : Jeremy
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title.searchsched" /></title>
</head>
<body>
	<h2>
		<fmt:message key="title.searchsched" />
	</h2>
	<form action="${pageContext.request.contextPath}/controller/searchsched"
		method=post>
		<center>
			<table class="framed">
			<%-- 	<tr>
					<th width="45%"><fmt:message key="caption.prog" /></th>
					<th width="55%"><fmt:message key="caption.date" /></th>
                                        <th width="55%"><fmt:message key="caption.time" /></th>
			--%>	</tr>
				<tr> 
					<td><fmt:message key="fieldLabel.prog" /></td>
					<td><input type="text" name="prog" size=45 maxlength=45></td>
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.date" /></td>
					<td><input type="date" name="date" size=45 maxlength=45></td>
				</tr>
                            	<tr>
					<td><fmt:message key="fieldLabel.time" /></td>
					<td><input type="time" name="time" size=45 maxlength=45></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
                                            <input type="submit" value="Submit"> 
                                            <input type="reset" value="Reset">
                                        </td>
				</tr>
			</table>
		</center>

	</form>
	<c:if test="${! empty  searchschedlist}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.searchsched.duration" /></th>
                                <th><fmt:message key="label.searchsched.dateOfProgram" /></th>
                                <th><fmt:message key="label.searchsched.startTime" /></th>
                                <th><fmt:message key="label.searchsched.programName" /></th>
				<th><fmt:message key="label.searchsched.presenterName" /></th>
                                <th><fmt:message key="label.searchsched.producerName" /></th>
			</tr>

			<c:forEach var="sched" items="${searchschedlist}" varStatus="status">
                                <fmt:formatDate value="${sched.duration}" pattern="HH:mm:ss" var="durationStr" />
                                <fmt:formatDate value="${sched.startTime}" pattern="HH:mm:ss" var="startTimeStr" />
                                <fmt:formatDate value="${sched.dateOfProgram}" pattern="YYYY-MM-dd" var="dateOfProgramStr" />
				<tr class="${status.index%2==0?'even':'odd'}">
                                        <td class="nowrap">${durationStr}</td>
                                        <a href="${editurl}"><td class="nowrap">${dateOfProgramStr}</td></a>
                                        <td class="nowrap">${startTimeStr}</td>
                                        <td class="nowrap">${sched.radioProgram.name}</td>
                                        <td class="nowrap">${sched.presenter.id}</td>
                                        <td class="nowrap">${sched.producer.id}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>