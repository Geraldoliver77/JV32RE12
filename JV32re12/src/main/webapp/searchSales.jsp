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

<%

// MYSQLを立ち上げる
String DATABASE_NAME = "jv32";
String PROPATIES = "?characterEncoding=utf-8";
String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPATIES;
String USER = "root";
String PASS = "";
String driver = "com.mysql.jdbc.Driver";
Connection con = null;
PreparedStatement prst = null;
ResultSet rs = null;

//変数を受け取り
String product = request.getParameter("Product");
String user = request.getParameter("User");
String order = request.getParameter("order");
String check = "";
String sql = "SELECT * FROM sales12 ";

//SQLを結果により設定
if ("Product".equals(product)) {
	sql = "SELECT * FROM sales12 ORDER BY ProductName";

}

if ("User".equals(user)) {
	sql = "SELECT * FROM sales12 ORDER BY BuyerName";

}

if ("User".equals(user) && "Product".equals(product)) {
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , ProductName";

}

//ASC TOTAL

if ("ascTotal".equals(order)) {
	sql = "SELECT * FROM sales12 ORDER BY Total ASC";

}

if ("ascTotal".equals(order) && "Product".equals(product)) {
	sql = "SELECT * FROM sales12 ORDER BY ProductName , Total ASC";

}

if ("ascTotal".equals(order) && "User".equals(user)) {
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , Total ASC";

}

if ("ascTotal".equals(order) && "User".equals(user) && "Product".equals(product)) {
	check = "check";
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , ProductName , Total ASC";

}

//DESC TOTAL

if ("descTotal".equals(order)) {
	sql = "SELECT * FROM sales12 ORDER BY Total DESC";
}

if ("descTotal".equals(order) && "Product".equals(product)) {
	sql = "SELECT * FROM sales12 ORDER BY ProductName , Total DESC";

}

if ("descTotal".equals(order) && "User".equals(user)) {
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , Total DESC";

}

if ("descTotal".equals(order) && "User".equals(user) && "Product".equals(product)) {
	check = "check";
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , ProductName , Total DESC";

}

//ASC QUANTITY

if ("ascQuantity".equals(order)) {
	sql = "SELECT * FROM sales12 ORDER BY Quantity ASC";
}
if ("ascQuantity".equals(order) && "Product".equals(product)) {
	sql = "SELECT * FROM sales12 ORDER BY ProductName , Quantity ASC";

}

if ("ascQuantity".equals(order) && "User".equals(user)) {
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , Quantity ASC";

}

if ("ascQuantity".equals(order) && "User".equals(user) && "Product".equals(product)) {
	check = "check";
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , ProductName , Quantity ASC";

}

//DESC QUANTITY

if ("descQuantity".equals(order)) {
	sql = "SELECT * FROM sales12 ORDER BY Quantity DESC";
}

if ("descQuantity".equals(order) && "Product".equals(product)) {
	sql = "SELECT * FROM sales12 ORDER BY ProductName , Quantity DESC";

}

if ("descQuantity".equals(order) && "User".equals(user)) {
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , Quantity DESC";

}

if ("descQuantity".equals(order) && "User".equals(user) && "Product".equals(product)) {
	check = "check";
	sql = "SELECT * FROM sales12 ORDER BY BuyerName , ProductName , Quantity DESC";

}
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

<%-- 	<%=order%>
	<%=product%>
	<%=user%>
	<%=check%> --%>

	<h1>Search Sales Management</h1>

	<form action="./SalesManagementServlet" method="Get">

		<table border="1">
			<tr id="table">

				<td>ProductNo</td>
				<td>ProductName</td>
				<td>BuyerName</td>
				<td>Quantity</td>
				<td>Total</td>

			</tr>



			<%
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(URL, USER, PASS);

				prst = con.prepareStatement(sql);
				rs = prst.executeQuery();

				while (rs.next()) {
			%>
			<tr>

				<td><%=rs.getString("ProductId")%></td>
				<td><%=rs.getString("ProductName")%></td>
				<td><%=rs.getString("BuyerName")%></td>
				<td><%=rs.getString("Quantity")%></td>
				<td><%=rs.getString("Total")%></td>


			</tr>



			<%
			}

			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			}
			%>

		</table>


		<button type="submit" name="next" id="button" value="backSearch">back</button>



	</form>
</body>
</html>