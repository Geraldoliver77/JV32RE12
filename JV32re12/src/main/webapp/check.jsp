<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import = "beans.ProductInfoBean,beans.UserInfoBean,java.util.ArrayList" %>

<%
// UserPage確認するページ
int flag = 1;

%>

<%
//商品登録確認するページ

String getProductName = "";
if (request.getAttribute("productname") != null) {
	getProductName = (String) request.getAttribute("productname");
	flag = 2;
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
<%@ include file="/WEB-INF/view/style/cart.css" %>
</style>
</head>
<body>

	
	<%
	//ユーザ登録の場合
	if (flag == 1) {
	%>
	
	<div class="login-container">
		<section class="login" id="login">
			<header>
				<h2>Admin</h2>
				<h4>User Confirmation Page</h4>
			</header>

	<form action="./UserRegistServlet" method="post">
	<div id="registercheck">
		<p>
		
			UserId :
			${USERINFO.getId()}
		<p>
			UserName :
			${USERINFO.getUsername()}
		<p>
			Password :${USERINFO.getPassword()}
		<p>
			Gender :
			${USERINFO.getGender()}
		<p>
			Birth :${USERINFO.getBirth()}  
		</div>
			<br> <br>
			<button type="submit" name="send" value="register">登録</button>
			<button type="submit" name="send" value="backregister">戻る</button>
	</form>
	
	</div>			



	<%
	}
	%>

	<%
	//商品登録の場合
	if (flag == 2) {
	%>
	
	<div class="login-container">
		<section class="login" id="login">
			<header>
				<h2>Admin</h2>
				<h4>Product Confirmation Page</h4>
			</header>

	<form action="ProductManageServlet" method="post">
	<div id="registercheck">
		<p>
			ProductName :
			${PRODUCTINFO.getProductName()}
		<p>
			Price :
			${PRODUCTINFO.getPrice()}
		<p>
			Maker :${PRODUCTINFO.getMakerName()}
		<p>
			Genre :
			${PRODUCTINFO.getGenreName()}


			<br> <br>
			<button type="submit" name="send" value="register">登録</button>
			<button type="submit" name="send" value="backregister">戻る</button>
	</form>
	</section>
	</div>	

	<%
	}
	%>

</body>
</html>