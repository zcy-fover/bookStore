<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
	
		<form action="userServlet?getUser" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" id="username"></td>
				</tr>
				<tr>
					<td>密    码：</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" id="submit" value="提交"></td>
				</tr>
			</table>
		</form>
		
	</center>

</body>
</html>