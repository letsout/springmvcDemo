<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Success Page</h1>
	<h2>SUCCESS Page1</h2>
	<p>time:${requestScope.time}</p>
	
	<p>time:${requestScope.names}</p>
	<p>request user:${requestScope.user}</p>
	<p>session user:${sessionScope.user}</p>
	<p>request school:${requestScope.school}</p>
	<p>session school:${sessionScope.school}</p>
	<p><fmt:message key="i18n.username"></fmt:message></p>
	<p><fmt:message key="i18n.password"></fmt:message></p>
</body>
</html>