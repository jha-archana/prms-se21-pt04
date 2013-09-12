<%-- 
    Document   : searchuser
    Created on : Sep 12, 2013, 3:18:24 PM
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
<title><fmt:message key="title.searchuser" /></title>
</head>
<body>
	<h2>
		<fmt:message key="title.searchuser" />
	</h2>
	<form action="${pageContext.request.contextPath}/controller/searchuser"
		method=post>
		<center>
			<table class="framed">
				<tr>
					<th width="45%"><fmt:message key="caption.name" /></th>
					<th width="55%"><fmt:message key="caption.role" /></th>
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.userfullname" /></td>
					<td><input type="text" name="name" size=45 maxlength=45></td>
				</tr>
				<tr>
					<td><fmt:message key="fieldLabel.role" /></td>
					<td><input type="text" name="role" size=45 maxlength=45></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
	<c:if test="${! empty  searchuserlist}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.crudusr.name" /></th>
				<th><fmt:message key="label.crudusr.role" /></th>
			</tr>
			<c:forEach var="user" items="${searchuserlist}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
					<a href="${editurl}"><td class="nowrap">${user.name}</td></a>
					<td class="nowrap">${user.roles}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>