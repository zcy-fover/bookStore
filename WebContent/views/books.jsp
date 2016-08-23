<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<!-- 静态资源 -->
	<script type="text/javascript" src="asserts/script/jquery-1.7.2.min.js"></script>
	<%@ include file="/views/common/queryCondition.jsp" %>
	<script type="text/javascript">
		$(function(){
			
			//查询数据传送,给页面中所有的标签a都加上两个隐藏域的值。
			//$("a").click(function(){
			//	var serializeVal = $(":hidden").serialize();
			//	var href = this.href + "&" + serializeVal;
				//window.location.href = href;
				//alert(serializeVal);
			//	return false;
			//});
			
			//跳转检验
			$("#pageNo").change(function(){
				
				var val = $(this).val();
				val = $.trim(val);//去掉前后空格
				//校验输入框中的是否是合法数字
				var flag = false;
				var reg = /^\d+$/g;
				var pageNo = 0;
				if(reg.test(val)){
					//校验val在一个合法的范围内
					pageNo = parseInt(val);
					if(pageNo >= 1 && pageNo <= parseInt("${ bookpage.totalPageNumber }")){
						flag = true;
					}
				}
				
				if( !flag ){
					alert("输入的页码格式不是合法的");
					$(this).val("");
					return;
				}
				
				//页面跳转
				var href = "bookServlet?method=getBooks&pageNo=" + pageNo + "&" + $(":hidden").serialize();
				window.location.href = href;
				
			});
			
		})
	</script>

</head>
<body>

	<p align="right"><a href="bookServlet?method=forwardPage&page=users">查看用户购买记录</a></p>

	<center>
		
		
		
		<c:if test="${param.title != null}">
			您已经将 ${param.title} 放入到购物车中. 
			<br><br>
		</c:if>
		
		<c:if test="${!empty sessionScope.ShoppingCart.books }">
			您的购物车中有 ${sessionScope.ShoppingCart.bookNumber } 本书,
			 <a href="bookServlet?method=forwardPage&page=cart&pageNo=${bookpage.pageNo }">查看购物车</a>
		</c:if>
		
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			
			价格: 
			<input type="text" size="1" name="minPrice"/> - 
			<input type="text" size="1" name="maxPrice"/>
			
			<input type="submit" value="Submit"/>
			
		</form>
		
		<table cellpadding="10">
		
			<c:forEach items="${ bookpage.list }" var="book">
				<tr>
					<td>
						<a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo }&id=${book.id}">${book.title }</a>
						<br>
						${book.author }
					</td>
					<td>${book.price }</td>
					<td><a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.id}&title=${book.title}">加入购物车</a></td>
				</tr>
			</c:forEach>
		
		</table>
		
		<br><br>
		共${ bookpage.totalPageNumber }页
		当前第${ bookpage.pageNo }页
		
		<c:if test="${ bookpage.hasPrev }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${ bookpage.prevPage }">上一页</a>
		</c:if>
		<c:if test="${ bookpage.hasNext }">
			<a href="bookServlet?method=getBooks&pageNo=${ bookpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${ bookpage.totalPageNumber }">尾页</a>
		</c:if>
		
		转到<input type="text" size="1" name="pageNo" id="pageNo" />页
		
	</center>

</body>
</html>