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
    <br/> <br/>
    We should put user list here!!!
</body>
</html>