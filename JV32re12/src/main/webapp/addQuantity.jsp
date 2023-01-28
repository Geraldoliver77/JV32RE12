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
<style><%@include file="/WEB-INF/view/style/login.css"%></style>
</head>
<body>

	<form action="./UpdateDeleteServlet" method="get">
		
		<div class="login-container">
			<section class="login" id="login">
				<header>
					<h2>Admin </h2>
					<h4>Update Stock </h4>
				</header>
		
		<%for (productDTO rec : productListId) {%>
		<h3>
			商品ID :
			<%= rec.getId() %>
		</h3>

		<h3>
			商品名 :
			<%= rec.getProductName() %>
		</h3>

		<h3>
		在庫　：
			<input type="text" name="newquantity" placeholder="<%= rec.getQuantity() %>">
		</h3>
		

		<input type="hidden" name="HiddenId" value="<%= rec.getId() %>"> <br>
		<%}%>
		<input type="submit" class="login-button" name="send" value="変更">
		<button type="submit" class="login-button" name="send" value="backQuantity">戻る</button>

	</form>

</body>
</html>