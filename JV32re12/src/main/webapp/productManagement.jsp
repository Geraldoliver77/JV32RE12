<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import = "java.util.ArrayList,dto.productDTO"%>
<%@ page import = "java.util.ArrayList,dto.genreDTO"%>
<%@ page import = "java.util.ArrayList,dto.makerDTO"%>
<%
String nameError = "";
if (request.getAttribute("nameError") != null) {
	nameError = (String) request.getAttribute("nameError");
}
%>

<%
String priceError = "";
if (request.getAttribute("priceError") != null) {
	priceError = (String) request.getAttribute("priceError");
}
%>

<%
String makerError = "";
if (request.getAttribute("makerError") != null) {
	makerError = (String) request.getAttribute("makerError");
}
%>

<%
String genreError = "";
if (request.getAttribute("genreError") != null) {
	genreError = (String) request.getAttribute("genreError");
}
%>

<%
String success = "";
if (request.getAttribute("success") != null) {
	success = (String) request.getAttribute("success");
}
%>

<%
ArrayList<productDTO> productList = (ArrayList<productDTO>)request.getAttribute("productList");
ArrayList<genreDTO> genreList = (ArrayList<genreDTO>)request.getAttribute("genreList");
ArrayList<makerDTO> makerList = (ArrayList<makerDTO>)request.getAttribute("makerList");
%>   

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
<%@ include file="/WEB-INF/view/style/product.css" %>
</style>
</head>
<body>

	<form action="./ProductManageServlet" method="get">
	
	<div id="header">	
	<% if(nameError == "" && makerError == "" && genreError == "" && priceError == "" )  {	%>
		<h1>Product Management</h1>
		
	</div>
	<h3><%=success%></h3>
	<div id="search">	
		<p>
			商品検索<input type="text" name="search">
			<button type="submit"  name="send" value="search" placeholder="idを検索">検索</button>
		</p>
	</div>
		
		<table border="1">
			<tr id="table">
				<td>ID</td>
				<td>ProductName</td>
				<td>Maker</td>
				<td>Genre</td>
				<td>Price</td>	
				<td>Stock</td>				
				<td>Action</td>

			</tr>

			<% for (productDTO product : productList) { %>
			<tr>
				<td><%= product.getId() %></td>
				<td><%= product.getProductName() %></td>
				<td><%= product.getMakerName() %></td>
				<td><%= product.getGenreName() %></td>
				<td><%= product.getPrice() %></td>	
				<td><%= product.getQuantity() %></td>	
					

				<td>			
				<form action="./ProductManageServlet" method="get">
				<button type="submit" id="searchback" name="del" value="<%= product.getId() %>">Delete</button>
				<button type="submit" id="searchback" name="upd" value="<%= product.getId() %>">Update</button>
				<button type="submit" id="searchback" name="qnt" value="<%= product.getId() %>">Stock</button>	
				</td>			
				</form>
			
				<%
				}
				%>



		</table>
	<% }%>

		<div id=addproduct>
		<div id="body">		
		<h1 >商品登録</h1>
		</div>

		<p>
			商品名：<input type="text" name="productname"><%=nameError%>
		</p>

		<p>
			メーカ： <select name="maker">
				<option value="blank">----</option>
				<%
				for (makerDTO rec : makerList) {
				%>
				
				<option value="<%= rec.getMaker() %>">
					<%= rec.getMaker() %></option>
				<%}%>
			</select><%=makerError%>
			
		
			ジャンル： <select name="genre">
				<option value="blank">----</option>
					<%
				for (genreDTO rec : genreList) {
				%>
				
				<option value="<%= rec.getGenre() %>">
					<%= rec.getGenre() %></option>
				<%}%>
			</select>
			<%=genreError%>
		</p>	
			
		<p>
			価格：<input type="text" name="price" placeholder="12000"><%=priceError%>
		</p>

		<p>
		<button type="submit" id="searchback" name="send" value="send">登録</button>
		<button type="submit" id="searchback" name="send" value="back">戻る</button>
		</p>
		</div>









	</form>




</body>
</html>