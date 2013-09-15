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
		<fmt:message key="title.presenter.search" />
	</h2>
        <div>
	<form action="${pageContext.request.contextPath}/ReviewSelectPresenter/search?returnURL=${requestScope.returnURL}"
		method=post>
		<center>
			<table class="framed">
				<tr>
					<th width="45%"><fmt:message key="caption.name" /></th>
					<th width="55%"><fmt:message key="caption.desc" /></th>
				</tr>
				<tr>
					<td><fmt:message key="label.presenter.name" /></td>
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
	<c:if test="${! empty  presenters}">
		<table class="borderAll">
			<tr>
				<th><fmt:message key="label.presenter.name" /></th>
				<th><fmt:message key="label.presenter.action" /></th>
			</tr>
			<c:forEach var="presenter" items="${presenters}" varStatus="status">
				<tr class="${status.index%2==0?'even':'odd'}">
					<td class="nowrap">${presenter.name}</td>
					<td class="nowrap">
                                            <c:url value="/ReviewSelectPresenter/select" var="selurl">
                                                <c:param name="id" value="${presenter.id}" />
                                                <c:param name="name" value="${presenter.name}" />
                                                <c:param name="returnURL" value="${requestScope.returnURL}"/>
                                            </c:url>
                                            <a href='${selurl}'>Select</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
        </div>
</body>
</html>