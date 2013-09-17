<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <fmt:setBundle basename="ApplicationResources" />
        <style type="text/css">
            error{
                color: crimson;
                margin-bottom: 10px;
            }
            success{
                color: green;
                margin-bottom: 10px;
            }
        </style>
        <title> <fmt:message key="title.crudusr"/> </title>
    </head>
    <body>
        <h1><fmt:message key="label.crudusr"/></h1>
        <c:url var="url" scope="page" value="/pages/setupusr.jsp">
            <c:param name="id" value=""/>
            <c:param name="insert" value="true"/>
        </c:url>
        <a href="${url}"><fmt:message key="label.crudusr.add"/></a>
        <br/> <br/>
    <error>${errorMsg}</error><success>${successMsg}</success>
   
    <form action="${pageContext.request.contextPath}/controller/searchuser"
          method=post>
        <center>
            <table class="framed">
                <%-- 	<tr>
                                <th width="45%"><fmt:message key="caption.name" /></th>
                                <th width="55%"><fmt:message key="caption.role" /></th>
                --%>	</tr>
                <tr> 
                    <td><fmt:message key="fieldLabel.username" /></td>
                    <td><input type="text" name="id" size=45 maxlength=45></td>
                </tr>
                <tr>
                    <td><fmt:message key="fieldLabel.userfullname" /></td>
                    <td><input type="text" name="name" size=45 maxlength=45></td>
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
    <c:if test="${! empty  searchuserlist}">
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudusr.id" /></th>
                <th><fmt:message key="label.crudusr.name" /></th>
                <th><fmt:message key="label.crudusr.role" /></th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="user" items="${searchuserlist}" varStatus="status">
                <c:url var="editurl" scope="page" value="/pages/setupusr.jsp">
                    <c:param name="id" value="${user.id}"/>
                    <c:param name="insert" value="false"/>
                </c:url>
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap"><a href="${editurl}">${user.id}</td></a>
                    <td class="nowrap">${user.name}</td>
                    <td class="nowrap">${user.getRoleString()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>