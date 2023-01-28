<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import = "java.util.ArrayList,dto.productDTO"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>


<%
ArrayList<productDTO> productListId = (ArrayList<productDTO>)request.getAttribute("productListId");
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
				<h4>Delete Confirmation Page</h4>
			</header>
	<form action="./UpdateDeleteServlet" method="get">
	<div id="registercheck">
		<h3>削除の確認</h3>
		

		<%for (productDTO rec : productListId) {%>
		
			
		<p>ID :<%= rec.getId() %>	</p>
		<p>ProductName :<%= rec.getProductName() %></p>
		</div>
	
		<input type="hidden" name="HiddenId" value="<%= rec.getId() %>">
		<%}%> <input
			type="submit" name="send" value="delete">
			<button type="submit" name="send" value="back">戻る</button>

	</form>

</body>
</html>