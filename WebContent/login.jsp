<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shrio表单提交</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/shiro/login" method="post">
		<input type="text" name="username">
		<br><br>
		<input type="password" name="password">
		<br><br>
		<input type="submit" value="Submit"> <span style="color: red;">${message }</span>
	</form>
</body>
</html>