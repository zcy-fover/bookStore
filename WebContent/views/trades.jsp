<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		
		<p><a href="index.jsp">继续购物</a></p>
		
		<br><br>
		<h3>用户：${ user.username }</h3>
		<br><br>
		
		<table border="1" cellpadding="10">
			<c:forEach items="${ user.trades }" var="trade">
				
				<tr bgcolor="gray">
					<td colspan="3">交易时间：${ trade.tradeTime }</td>
				</tr>
				
				<c:forEach items="${ trade.items }" var="item">
					<tr>
						<td>${ item.book.title }</td>
						<td>${ item.book.price }</td>
						<td>${ item.quantity }</td>
					</tr>
				</c:forEach>
				
			</c:forEach>
		</table>
	</center>

</body>
</html>