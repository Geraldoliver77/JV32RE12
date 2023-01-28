<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList,dto.productDTO"%>

<%@ page import=" java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>



<%
String userName = "";
if (session.getAttribute("userName") != null) {
	userName = (String) session.getAttribute("userName");
}
%>


<%
ArrayList<productDTO> cartList = (ArrayList<productDTO>)request.getAttribute("cartList");

int totalPrice = 0;
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
	<h4>
		Welcome MR/MRS
		<%=userName%></h4>



	<form action="./UserPurchaseServlet" method="post">


		<div id="table">
		<table border="1" class="styled-table">
			<tr>
		<thead>
				<td>ProductID</td>
				<td>ProductName</td>
				<td>Total Price</td>
				<td>Quantity</td>
				<td>Delete</td>
		</thead>

			</tr>
																						
			<% for (productDTO cart : cartList) { %>
			<tr>
			
			<td><%= cart.getId() %></td>
			<td><%= cart.getProductName() %></td>			
			<td><%= cart.getPrice() %></td>
			<td><%= cart.getQuantity() %></td>
			
					
				<input type="hidden" name="deleteId" value="<%=cart.getId()%>">			
				<% totalPrice += Integer.parseInt(cart.getPrice()) ; %>
				
											
				<td>				
				<button style="color:white" type="submit" id="button" name="delCart" value="<%=cart.getId()%>">Delete</button>														
				</td>
				<% } %>


			</tr>
			<td colspan="3">Total</td>
			<td><%=totalPrice%>円</td>
		</table>




	<div id="button">
		<% if(totalPrice > 0 ) {%>
		 <input type="submit" id="button" name="next" value="Purchase">
		 <% }%> 
		 <input type="submit" id="button" name="next" value="Back">
	</div>
	</form>


</body>
</html>