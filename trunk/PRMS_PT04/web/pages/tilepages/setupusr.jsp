<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="allRoles" class="sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate" scope="page"/>
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
        <form name="usrForm" action="${pageContext.request.contextPath}/controller/setupusr" method="post" onsubmit="return validateForm()" >
            <center>
                <table cellpadding=4 cellspacing=2 border=0>
                    <tr>
                        <th><fmt:message key="label.crudusr.id" /></th>
                        <td><c:if test="${param['insert'] == 'true'}">
                                <input type="text" name="id" value="${param['id']}" size=15
                                       maxlength=20>
                                <input type="hidden" name="ins" value="true" />
                            </c:if> 
                            <c:if test="${param['insert']=='false'}">
                                <input type="text" name="roid" value="${param['id']}" size=15
                                       maxlength=20 readonly="readonly">
                                <input type="hidden" name="ins" value="false" />
                            </c:if></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.name" /></th>
                        <td><c:if test="${param['insert'] == 'true'}">
                                <input type="text" name="name" value="${param['name']}" size=15
                                       maxlength=20>
                                <input type="hidden" name="ins" value="true" />
                            </c:if> 
                            <c:if test="${param['insert']=='false'}">
                                <input type="text" name="roname" value="${param['name']}" size=15
                                       maxlength=20 readonly="readonly">
                                <input type="hidden" name="ins" value="false" />
                            </c:if></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.pwd" /></th>
                        <td><c:if test="${param['insert'] == 'true'}">
                                <input type="text" name="pwd" value="${param['pwd']}" size=15
                                       maxlength=20>
                                <input type="hidden" name="ins" value="true" />
                            </c:if> 
                            <c:if test="${param['insert']=='false'}">
                                <input type="text" name="ropwd" value="${param['pwd']}" size=15
                                       maxlength=20 readonly="readonly">
                                <input type="hidden" name="ins" value="false" />
                            </c:if></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.crudusr.role" /></th>
                        <td><c:if test="${param['insert'] == 'true'}">
                                <select name="role" id="role" multiple="multiple" style="width:125px">
                                    <c:forEach var="item" items="${allRoles.findAllRole()}" >
                                        <option value="${item.getRole()}">${item.getRole()}</option>
                                    </c:forEach>
                                </select>                              
                            </c:if> 
                            <c:if test="${param['insert']=='false'}">
                                <input type="text" name="rorole" value="${param['role']}" size=15
                                       maxlength=20 readonly="readonly">
                                <input type="hidden" name="ins" value="false" />
                            </c:if></td>
                    </tr>
                </table>

                <input type="submit" value="Submit"> <input type="reset"
                                                            value="Reset">
            </center></form>

    </body>
</html>