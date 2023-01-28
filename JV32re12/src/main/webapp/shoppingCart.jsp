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

<%
//ユーザネームをゲットする
String userName = "";
if (session.getAttribute("userName") != null) {
	userName = (String) session.getAttribute("userName");
}
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/view/style/style.css"%></style>
</head>
<body>

	<h1 id="headersearch">Shopping Cart</h1>
	<h4><u>
		Welcome MR/MRS
		<%=userName%></h4>
		</u>

	<form action="./UserPurchaseServlet" method="post">
	
	<%for (productDTO rec : productListId) {%>

		<input type="hidden" name="id" value="<%=rec.getId()%>"> <input
			type="hidden" name="quantity" value="<%=rec.getQuantity()%>"> <input
			type="hidden" name="name" value="<%=rec.getProductName()%>"> <input
			type="hidden" name="maker" value="<%=rec.getMakerName()%>"> <input
			type="hidden" name="price" value="<%=rec.getPrice()%>">
	<div id="cartcheck">
		<p>
			Id :
			<%=rec.getId()%>
		</p>	
		<p>
			Name :
			<%=rec.getProductName()%>
		</p>	
		<p>
			Maker :
			<%=rec.getMakerName()%>
		</p>	
		<p>
			Price :
			<%=rec.getPrice()%>
		</p>	
		<p>
			Quantity： <select name="newQuantity">
				<option value="1">1</option>
	
				<%
				for (int i = 2; i <= Integer.parseInt(rec.getQuantity()); i++) {
				%>
				<option value="<%=i%>"><%=i%></option>
				<%}%> 
			</select> 
		<%}%>
		</p>
		</div>
		<div id="button">
		<input type="submit" id="button" name="next" value="addCart">
		<input type="submit" id="button" name="next" value="back">
		</div>	
	</form>
	
</body>
</html>