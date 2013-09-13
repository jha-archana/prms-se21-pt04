<%-- 
    Document   : setupschedule
    Created on : Sep 12, 2013, 11:06:11 AM
    Author     : Jha Archana
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="<c:url value='/css/jquery-ui-1.8.20.custom.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value='/js/jquery-1.7.2.min.js'/>">  </script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.8.20.custom.min.js'/>">  </script>
<%-- Time Picker js --%>
<script type="text/javascript" src="<c:url value='/js/jquery.ui.timepicker1.3.js'/>">  </script>
<link type="text/css" href="<c:url value='/css/jquery.ui.timepicker1.3.css'/>" rel="stylesheet" />
<%-- Validation Engine js --%>
<link rel="stylesheet" href="<c:url value='/css/validationEngine.jquery.css'/>" type="text/css"/>
<script src="<c:url value='/js/languages/jquery.validationEngine-en.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value='/js/jquery.validationEngine.js'/>" type="text/javascript" charset="utf-8"></script>

<fmt:setBundle basename="ApplicationResources" />


<script type="text/javascript">
	jQuery(document).ready(function(){
		$( ".datepicker" ).datepicker({dateFormat: 'dd/mm/yy'});
                $("input[name='duration']").timepicker({'minDivision': 5,
			'closeOnFormclick'		: true,
			'inputReadOnly'			: true});
                $("input[name='startTime']").timepicker({'minDivision': 5,
			'closeOnFormclick'		: true,
                        'hourFormat'	: 12,
			'inputReadOnly'			: true});
                 $("#submit").click(function(){
                        jQuery("#save").validationEngine();
                    });
	});
</script>
<title><fmt:message key="title.setupschedule" /></title>
</head>
<body>
	<form name="scheduleForm" action="${pageContext.request.contextPath}/controller/setupschedule" method=post id="save">
		<center>
			<table cellpadding=4 cellspacing=2 border=0>
				<tr>
					<td><fmt:message key="label.maintainSchedule.duration" /></td>
					<td>
                                            <input type="text" name="duration" value="${param['duration']}" size=15
                                                    maxlength=20 class="validate[required]" data-prompt-position="centerRight:+40">
                                            <input type="hidden" name="ins" value="true" />
                                        </td>
				</tr>
				<tr>
					<td><fmt:message key="label.maintainSchedule.dateOfProgram" /></td>
					<td><input type="text" name="dateOfProgram" class="validate[required] datepicker"
						value="${param['dateOfProgram']}" data-prompt-position="centerRight:+40"  size=15 maxlength=20></td>
				</tr>
				<tr>
					<td><fmt:message key="label.maintainSchedule.startTime" /></td>
					<td><input type="text" name="startTime"
						value="${param['startTime']}" class="validate[required]" data-prompt-position="centerRight:+40" size=15 maxlength=20></td>
				</tr>
                                <tr>
					<td><fmt:message key="label.maintainSchedule.programName" /></td>
					<td><input type="text" name="radioProgram"
						value="${param['radioProgram']}" class="validate[required]" data-prompt-position="centerRight:+40" size=15 maxlength=20></td>
				</tr>
                                <tr>
					<td><fmt:message key="label.maintainSchedule.presenterName" /></td>
					<td><input type="text" name="presenter"
						value="${param['presenter']}" class="validate[required]" data-prompt-position="centerRight:+40" size=15 maxlength=20></td>
				</tr>
                                <tr>
					<td><fmt:message key="label.maintainSchedule.producerName" /></td>
					<td><input type="text" name="producer"
						value="${param['producer']}" class="validate[required]" data-prompt-position="centerRight:+40" size=15 maxlength=20></td>
				</tr>
			</table>
		</center>
		<input type="submit" value="Submit" id="submit"> <input type="reset"
			value="Reset">
	</form>

</body>
</html>
