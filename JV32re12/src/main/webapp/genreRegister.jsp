<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String genreError = "" ;
if (request.getAttribute("genreError") != null) {
	genreError = (String) request.getAttribute("genreError");
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
				<h4>Genre Register Page</h4>
			</header>
	<form action="./GenreRegistServlet" method="post">
		<h1 >Admin Genre Register Page</h1>


		<p>
			ジャンル名：<input type="text" name="genreid"><p style="color: red"><%=genreError%></p>
		</p>
		<button type="submit" class="login-button" name="send" value="send">登録</button>
		<button type="submit" class="login-button" name="send" value="back">戻る</button>

	</form>

</body>
</html>