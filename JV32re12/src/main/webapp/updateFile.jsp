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

	
			<%for (productDTO rec : productListId) {%>
			
			<div class="login-container">
			<section class="login" id="login">
				<header>
					<h2>Admin </h2>
					<h4>Update Confirmation</h4>
				</header>
				<form class="login-form"  action="./UpdateDeleteServlet"  method="post">
					商品名:<input type="text" class="login-input" value="<%= rec.getProductName() %>"
						name=productName  /> 商品価格 :<input type="text"
						class="login-input" value="<%= rec.getPrice() %>" name="productPrice"
						 />
					<div class="submit-container">
					 <input type="hidden" name="HiddenId" value="<%=rec.getId() %>">
					 <%}%>
						<button type="submit" class="login-button" name="send"
							value="update">Update</button>
						<button type="submit" class="login-button" name="send"
							value="back">戻る</button>
					</div>
				</form>
			</section>

		</div>
	
	
	
	

</body>
</html>