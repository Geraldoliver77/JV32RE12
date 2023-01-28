<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import=" java.util.ArrayList"%>
<%@ page import = "java.util.ArrayList,dto.productDTO"%>
<%
//検索の配列を立ち上げる
ArrayList<productDTO> productList = (ArrayList<productDTO>)request.getAttribute("searchList");
%>
<!DOCTYPE html>
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
			</tr>
			
				<%
				}
				%>



		</table>


		<button type="submit" id="searchback" name="send" value="backAdmin">戻る</button>

	</form>
</body>
</html>