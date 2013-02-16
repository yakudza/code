<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.Date"%>
<%@page import="com.intita.domain.Exam"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include flush="true" page="header.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/user.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/exam.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<H1>${masseng}</H1>
<br/>
<form:form action="show" method="post" modelAttribute="resiver">
	<form:input type="hidden" readonly="readonly" value="${groupId.groupId}" path="groupId"/>
	<form:input type="hidden" readonly="readonly" value="${groupId.subjectName}" path="subjectName"/>
 	<form:input type="hidden" readonly="readonly" value="${groupId.subjectType}" path="subjectType"/>
 <input type="submit" class="button" value="show rezults"/>
	
</form:form>
</center>
<jsp:include flush="footer" page="footer.jsp"/>