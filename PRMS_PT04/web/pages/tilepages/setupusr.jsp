<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="adel" class="sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate" scope="page"/>
<jsp:useBean id="sdel" class="sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <fmt:setBundle basename="ApplicationResources" />
        <script type="text/javascript">

            function validateForm()
            {
                var x = document.forms["usrForm"]["id"].value;
                if (x == null || x == "")
                {
                    alert("Id must be filled out");
                    return false;
                }
                x = document.forms["usrForm"]["name"].value;
                if (x == null || x == "")
                {
                    alert("Name must be filled out");
                    return false;
                }
                x = document.forms["usrForm"]["pwd"].value;
                if (x == null || x == "")
                {
                    alert("Pwd must be filled out");
                    return false;
                }
            }
        </script>        
        <title><fmt:message key="title.setupusr" /></title>
    </head>
    <body>
        <c:set var="usr" value="${adel.findUser(param['id'])}"/>
        <form name="usrForm" action="${pageContext.request.contextPath}/controller/setupusr" method="post" onsubmit="return validateForm()" >
            <center>
                <input type="hidden" name="currId" value="${param['id']}" >
                <input type="hidden" name="ins" value="${param['insert']}" />
                <table cellpadding=4 cellspacing=2 border=0>
                    <tr>
                        <th><fmt:message key="label.crudusr.id" /></th>
                        <td>
                            <input type="text" name="id" value="${usr.getId()}" size=15
                                   maxlength=20>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.name" /></th>
                        <td>
                            <input type="text" name="name" value="${usr.getName()}" size=15
                                   maxlength=20>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.pwd" /></th>
                        <td>
                            <input type="text" name="pwd" value="${usr.getPassword()}" size=15
                                   maxlength=20>
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.role" /></th>
                            <c:set var="strRoles" value="${usr.getRoleString()}"/>
                        <td>
                            <select name="role" id="role" multiple="multiple" style="width:125px">
                                <c:forEach var="item" items="${adel.findAllRole()}" >
                                    <c:set var="strRole" value="${item.getRole()}" />
                                    <c:choose>
                                        <c:when test="${fn:contains(strRoles, strRole)}">
                                            <option value="${item.getRole()}" selected="true">${item.getRole()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item.getRole()}">${item.getRole()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>                              
                        </td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.program" /></th>
                        <td>
                            <c:set var="psList" value="${sdel.findProgramSlotByUser(usr.getId())}"/>
                            <ul name="role" id="programs" style="width:125px">
                                <c:forEach var="item" items="${psList}" >
                                    <fmt:formatDate value="${item.startTime}" pattern="HH:mm:ss" var="startTimeStr" />
                                    <fmt:formatDate value="${item.duration}" pattern="HH:mm:ss" var="durationStr" />
                                    <fmt:formatDate value="${item.dateOfProgram}" pattern="YYYY-MM-dd" var="dateOfProgramStr" />
                                    <c:url var="editurl" scope="page" value="/pages/setupschedule.jsp">
                                        <c:param name="duration" value="${durationStr}"/>
                                        <c:param name="startTime" value="${startTimeStr}"/>
                                        <c:param name="dateOfProgram" value="${dateOfProgramStr}"/>
                                        <c:param name="radioProgram" value="${item.radioProgram.name}"/>
                                        <c:param name="presenter_id" value="${item.presenter.id}"/>
                                        <c:param name="presenter_name" value="${item.presenter.name}"/>
                                        <c:param name="producer_id" value="${item.producer.id}"/>
                                        <c:param name="producer_name" value="${item.producer.name}"/>
                                        <c:param name="id" value="${item.id}"/>
                                        <c:param name="insert" value="false"/>
                                    </c:url>
                                    <li value="${item.getId()}"><a href="${editurl}">${item.radioProgram.name}</a></li>
                                    </c:forEach>
                            </ul>              
                        </td>
                    </tr>
                </table>

                <input type="submit" value="Submit"> <input type="reset"
                                                            value="Reset">
            </center></form>

    </body>
</html>