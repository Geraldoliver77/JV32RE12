
<%
String success = "";
if (request.getAttribute("success") != null) {
	success = (String) request.getAttribute("success");
}
%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/view/style/adminpage.css"%></style>
</head>
<body>


		<div class="login-container">
		<section class="login" id="login">
			<header>
				<h1>Admin</h1>
				<h3>Admin Top Page</h3>
			</header>
	<form action="./LoginServlet" method="get">
		

		<h3 style="color: red"><%=success%></h3>

	<div id="block1">
		<p id="box1">
			<a href="./userRegister.jsp">ユーザ登録</a>
		</p>

		<p id="box1">
			<a href="./makerRegister.jsp">メーカー登録</a>
		</p>
	
	</div>
	
	<div id="block2">
		<p id="box1">
			<a href="./genreRegister.jsp">ジャンル登録</a>
		</p>

		<p id="box2">
			<button type="submit" name="next" value="productmanagement">商品管理</button>
		</p>
	</div>		
	
	
	<div id="block3">
	
		<p id="box2">
			<button type="submit" name="next" value="salesManagement">売り上げ管理</button>
		</p>
				
		</div>
		

	<div id="block4">
		<button id="box3" type="submit" name="next" value="logout">logout</button>
		</div>
		</div>
	</section>
	</form>



</body>
</html>