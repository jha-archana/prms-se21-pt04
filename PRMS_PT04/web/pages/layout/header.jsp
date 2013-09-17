<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setBundle basename="ApplicationResources" />
<table class="footer" >
  <tr>
    <td width="200">
      <img src="${pageContext.request.contextPath}/img/logo.jpg" width="150" height="150" alt="PHOENIX" border="0">
    </td>
    <td valign="middle">
      <table class="footer">
        <tr>
          <td class="applicationTitle" align="center" width="100%">
            <fmt:message key="title.application"/>
          </td>
        </tr>
        <tr>
          <td class="userTitle" align="center" width="100%">
            <c:if test="${not empty sessionScope.user}">
                <b><fmt:message key="caption.user"/></b>:
              <c:out value="${sessionScope.user.name}"/> &nbsp; &nbsp;
              <b><fmt:message key="caption.role"/></b>:
              <c:forEach var="item" items="${sessionScope.user.roles}">
                 <c:out value="${item.role}"/> &nbsp;
              </c:forEach>
            </c:if>
          </td>
        </tr>
      </table>    
</table>






