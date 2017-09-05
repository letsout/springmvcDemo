<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<a href="hello/testRedirect">testRedirect</a>
<br>
<a href="hello/testHelloView">testHelloView</a>  
<br>
<a href="hello/testViewAndViewResolver">testViewAndViewResolver</a>
<br>
	<form action="hello/testModelAttribute" method="post">
		<input type="hidden" name="id" value="1"/>
		username:<input type="text" name="username" value="H"/>
		<br>
		email:<input type="text" name="email" value="1@qq.com"/>
		<br>
		age:<input type="text" name="age" value="11">
		<br>
		<input type="submit" value="Submit"/>
	</form>
<br>
	<a href="hello/testSessionAttributes">testSessionAttributes</a>
	<br>
<br>
	<a href="hello/testMap">testMap</a>
	<br>
	<a href="hello/testModelAndView">testModelAndView</a>
	<br>
	<br>
	<a href="hello/testServletApi">testServletApi</a>
	<br>
	<form action="hello/testPojo" method="post">
		username:<input type="text" name="username"/>
		<br>
		password:<input type="password" name="password"/>
		<br>
		email:<input type="text" name="email"/>
		<br>
		age:<input type="text" name="age">
		<br>
		city:<input type="text" name="address.city">		
		<br>
		province:<input type="text" name="address.province">
		<br>
		<input type="submit" value="Submit">
	</form>
	<br>
	<a href="hello/testRequestHeader">testRequestHeader</a>
	<br/>
	<a href="hello/testRequestParam?username=H&age=10">testRequestParam</a>
	
	<br/>
	<form action="hello/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE" >
		<input type="submit" value="testRestDelete"/>		
	</form>
	<br/>
	<form action="hello/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT" >
		<input type="submit" value="testRestPut"/>		
	</form>
	<br/>
	<form action="hello/testRest" method="post">
		<input type="submit" value="testRestPost"/>		
	</form>
	
	<br/>
	<a href="hello/testRest/1">testRestGet</a>
	<br/>
	<a href="hello/pathVariable/1">pathVariable</a>
	<br/>
	<a href="hello/test">hello world</a>
	<br/>
	<a href="hello">hello world</a>
	<br/>
</body>
</html>