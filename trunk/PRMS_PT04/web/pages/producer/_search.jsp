<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<fmt:setBundle basename="ApplicationResources" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title.presenter.search" /></title>
</head>
<body>
	<h2>
		<fmt:message key="title.producer.search" />
	</h2>
        <div>
	<form action="${pageContext.request.contextPath}/RSProducer/search"
		method=post>
		<center>
			<table class="framed">
				<tr>
					<th width="45%"><fmt:message key="caption.name" /></th>
					<th width="55%"><fmt:message key="caption.desc" /></th>
				</tr>
				<tr>
					<td><fmt:message key="label.producer.name" /></td>
					<td><input type="text" name="name" size=45 maxlength=45></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit"> <input
						type="reset" value="Reset"></td>
				</tr>
			</table>
		</center>

	</form>
        </div>
        <div>
	<c:if test="${! empty  producers}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.producer.name" /></th>
				<th><fmt:message key="label.producer.action" /></th>
			</tr>
			<c:forEach var="producer" items="${producers}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
					<td class="nowrap">${producer.name}</td>
					<td class="nowrap">
                                            <a href='
                                            <c:url value="/RSProducer/select">
                                                <c:param name="id" value="${producer.id}" />
                                                <c:param name="name" value="${producer.name}" />
                                            </c:url>'>Select</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
        </div>
</body>
</html>