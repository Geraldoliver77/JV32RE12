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
//MYSQLを立ち上げる、設定する
String DATABASE_NAME = "jv32";
String PROPATIES = "?characterEncoding=utf-8";
String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPATIES;
String USER = "root";
String PASS = "";
String driver = "com.mysql.jdbc.Driver";
Connection con = null;
PreparedStatement prst = null;
ResultSet rs = null;

ArrayList<ArrayList<String>> salesList = new ArrayList<ArrayList<String>>();
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection(URL, USER, PASS);

	String sql = "SELECT * FROM sales12 ";

	prst = con.prepareStatement(sql);
	rs = prst.executeQuery();

	while (rs.next()) {
		ArrayList<String> record = new ArrayList<String>();

		record.add(rs.getString("Id"));
		record.add(rs.getString("ProductId"));
		record.add(rs.getString("ProductName"));
		record.add(rs.getString("BuyerName"));
		record.add(rs.getString("Quantity"));
		record.add(rs.getString("Total"));

		salesList.add(record);

	}

}

catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
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

	<div id="header">	
	<h1>Sales Management</h1>
	</div>

	<form action="./SalesManagementServlet" method="Post">

	<div id="checkbox">
		<p>
			絞り込み： 
			 <input	type="checkbox" name="Product" value="Product">商品
			 <input	type="checkbox" name="User" value="User">ユーザー
		<p>
			売り上げ金額：<input type="radio" name="order" value="ascTotal">昇 <input
				type="radio" name="order" value="descTotal">降 <br>
			<br> 個数：<input type="radio" name="order" value="ascQuantity">昇
			<input type="radio" name="order" value="descQuantity">降
		</p>

		<button type="submit" id="button" name="next" value="search">search</button>
	</div>	

		<br>
		<br>

		<table border="1">
			<tr id="table">
				<td>ID</td>
				<td>ProductNo</td>
				<td>ProductName</td>
				<td>BuyerName</td>
				<td>Quantity</td>
				<td>Total</td>

			</tr>

			<%
			for (ArrayList<String> rec : salesList) {
			%>
			<tr>
				<%
				for (String data : rec) {
				%>
				<td><%=data%></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
		</table>

		<button type="submit" name="next" id="button" value="back">back</button>

	</form>


</body>
</html>