<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//IDエラーをゲットする
String idError = "";
if (request.getAttribute("idErrors") != null) {
	idError = (String) request.getAttribute("idErrors");
}
%>


<%
//パスワードエラーをゲットする
String passError = "";
if (request.getAttribute("passErrors") != null) {
	passError = (String) request.getAttribute("passErrors");
}
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
	<style><%@include file="/WEB-INF/view/style/login.css"%></style>
</head>
<body>
	
		<div class="login-container">
			<section class="login" id="login">
				<header>
					<h2>Welcome </h2>
					<h4>Login</h4>
				</header>
				<form class="login-form" action="./LoginServlet" method="post">
					<input type="text" class="login-input" placeholder="User"
						name="userid" required autofocus /> <input type="password"
						class="login-input" placeholder="Password" name="password"
						required />
					<div class="submit-container">
						<button type="submit" class="login-button" name="next"
							value="next">送信</button>
						<button type="submit" class="login-button" name="reset"
							value="reset">リセット</button>
					</div>
				</form>
			</section>

		</div>



	
</body>
</html>