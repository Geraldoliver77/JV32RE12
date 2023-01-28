<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String idError = "" ;
if (request.getAttribute("makerError") != null) {
 idError = (String) request.getAttribute("makerError");
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
<%@ include file="/WEB-INF/view/style/register.css" %>
</style>
</head>
<body>


<div class="login-container">
		<section class="login" id="login">
			<header>
				<h2>Admin</h2>
				<h4>Maker Register Page</h4>
			</header>
	<form action="./MakerRegistServlet" method="post">
		<h1 >Admin Maker Register Page</h1>
		<p>
			メーカー名：<input type="text" name="genreid"><p style="color: red"><%=idError%></p>
		
		
		<button type="submit" class="login-button" name="send" value="send">登録</button>
		<button type="submit" class="login-button" name="send" value="back">戻る</button>	
				
	</form>
	
	</section>
	</div>
	

</body>
</html>