<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList,dto.productDTO"%>
<%@ page import = "java.util.ArrayList,dto.genreDTO"%>
<%@ page import = "java.util.ArrayList,dto.makerDTO"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>

<%
ArrayList<productDTO> productList = (ArrayList<productDTO>)request.getAttribute("productList");
ArrayList<genreDTO> genreList = (ArrayList<genreDTO>)request.getAttribute("genreList");
ArrayList<makerDTO> makerList = (ArrayList<makerDTO>)request.getAttribute("makerList");
ArrayList<productDTO> cartList = (ArrayList<productDTO>)request.getAttribute("cartList");
%>   

<%
// ユーザネームをゲット
String userName = "";
if (session.getAttribute("userName") != null) {
	userName = (String) session.getAttribute("userName");
}
%>

<%
//処理完了をゲット
String success = "";
if (request.getAttribute("success") != null) {
	success = (String) session.getAttribute("success");
}
%>

<%
//リストをゲットする
String list = (String) session.getAttribute("ListTable");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/view/style/style.css"%></style>
</head>
<body>

	<form action="./UserPurchaseServlet" method="post">

		<h1 id="header">User Page</h1>
		
		<h4 style="text-align:end">
			Welcome <%=userName%> 様</h4>

		<h3 style="color: red"><%=success%></h3>
	
		<%
		if (cartList != null) {
		%>
		<button type="submit" name="next" value=myCart>マイカート</button>
		<%
		}
		%>

		<div id="checkbox">

		<p>
			商品名<input type="text" name="searchName">
		<p>
			メーカ： <select name="searchMaker">
				<option value="">----</option>
				<%
				for (makerDTO rec : makerList) {
				%>
				
				<option value="<%= rec.getMaker() %>">
					<%= rec.getMaker() %></option>
				<%}%>
				
			</select> <br>
		<p>
		<p>
			ジャンル： <select name="searchGenre">
				<option value="">----</option>
				<%
				for (genreDTO rec : genreList) {
				%>
				
				<option value="<%= rec.getGenre() %>">
					<%= rec.getGenre() %></option>
				<%}%>
				
			</select>
		<p>
			価格（下限）<input type="text" name="searchPriceLow"> 価格（上限）<input
				type="text" name="searchPriceHigh"> <br>
			<br>
			<button type="submit" name="next" value="search"
				style="height: 20px; width: 80px">検索</button>
		</p>	
		<div id="table">
		<table border="1" class="styled-table">
			
			<tr>

        <thead>
				<td >ProductName</td>
				<td>Maker</td>
				<td>Genre</td>
				<td>Price</td>
				<td>Quantity</td>
				<td>Action</td>

		</thead>
			</tr>

			
			<tr>
			<% for (productDTO product : productList) { %>
				<td><%= product.getProductName() %></td>
				<td><%= product.getMakerName() %></td>
				<td><%= product.getGenreName() %></td>
				<td><%= product.getPrice() %></td>		
					
					<% if ("0".equals(product.getQuantity())) { %>
						<td style="color:red"> 品切れ</td>	
					<%} else {%>
						<td><%= product.getQuantity() %></td>	  
					<%} %>
									
				<form action="./UserPurchaseServlet" method="get">
			
			<%if (!"0".equals(product.getQuantity())) {%> 
				<td>				
				<input type="hidden" name="hiddenCartId" value="<%= product.getId() %>">
															
				<button type="submit" id="search" name="cartinfo" value="">カート</button>						
				 <%
				 } 
				 %>

				</td>
				</form>
			</tr>
			<%} %> 
		</table>
		
		</div>
		
		<div id="buttonsearch">
		<button type="submit" name="next" value=logout>ログアウト</button>
		</div>
			</div>
	</form>
	
</body>
</html>


