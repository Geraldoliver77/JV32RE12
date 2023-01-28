<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import=" java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@ page import = "java.util.ArrayList,dto.productDTO"%>
<%
//検索の配列を立ち上げる
ArrayList<productDTO> productList = (ArrayList<productDTO>)request.getAttribute("searchList");
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


	<h1 id="headersearch">Search Product Page</h1>
	<h4>
		Welcome MR/MRS
		<%=userName%></h4>
	

	<form action="./UserPurchaseServlet" method="post">

		<div id="tablesearch">
		<table border="1" class="styled-table">
			<tr>

		<thead>
				<td>ProductName</td>
				<td>Maker</td>
				<td>Genre</td>
				<td>Price</td>
				<td>Quantity</td>
				<td>Action</td>
		
			</tr>
		</thead>

			<% for (productDTO product : productList) { %>
			<tr>

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
				
				<%
				}
				%>
			
		</table>

		<div id="button">
		<button type="submit" class="back" name="next" value="backAdmin">戻る</button>
		</div>

	</form>

</body>
</html>