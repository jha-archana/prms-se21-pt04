<%-- 
    Document   : loadSchedule
    Created on : Sep 9, 2013, 3:35:55 PM
    Author     : Jha Archana
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html> 
<head>
<link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
<fmt:setBundle basename="ApplicationResources" />
<title> <fmt:message key="title.maintainSchedule"/> </title>
</head>
<body>
        <h1><fmt:message key="label.maintainSchedule"/></h1>
        <c:url var="url" scope="page" value="/pages/loadSchedule.jsp">
                <c:param name="duration" value=""/>
                <c:param name="dateOfProgram" value=""/>
                <c:param name="startTime" value=""/>
                <c:param name="programName" value=""/>
                <c:param name="insert" value="true"/>
        </c:url>
        <a href="${url}"><fmt:message key="label.maintainSchedule.add"/></a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.maintainSchedule.duration"/></th>
                <th><fmt:message key="label.maintainSchedule.dateOfProgram"/></th>
                <th><fmt:message key="label.maintainSchedule.startTime"/></th>
                <th><fmt:message key="label.maintainSchedule.programName"/></th>
                <th><fmt:message key="label.maintainSchedule.edit"/> <fmt:message key="label.maintainSchedule.delete"/></th>
            </tr>
            <c:forEach var="crudps" items="${schd}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${crudps.duration}</td>
                    <td class="nowrap">${crudps.dateOfProgram}</td>
                    <td class="nowrap">${crudps.startTime}</td>
                     <td class="nowrap">${crudps.radioProgram.name}</td>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/pages/loadSchedule.jsp">
                            <c:param name="duration" value="${crudps.duration}"/>
                            <c:param name="dateOfProgram" value="${crudps.dateOfProgram}"/>
                            <c:param name="startTime" value="${crudps.startTime}"/>
                             <c:param name="radioProgram" value="${crudps.radioProgram.name}"/>
                             <c:param name="insert" value="false"/>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.maintainSchedule.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        <c:url var="delurl" scope="page" value="/controller/deleterp">
                            <c:param name="name" value="${crudps.dateOfProgram}"/>
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.maintainSchedule.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
