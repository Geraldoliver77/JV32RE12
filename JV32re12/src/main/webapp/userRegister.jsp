<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
<%@ include file="/WEB-INF/view/style/register.css" %>
</style>
</head>
<body>


	<div class="login-container">
		<section class="login" id="login">
			<header>
				<h2>Admin</h2>
				<h4>User Register Page</h4>
			</header>
			<form class="login-form" action="./UserRegistServlet" method="post">

				<input type="text" class="login-input" placeholder="ユーザID"
					name="userid" />
				<p style="color: red">${idError} </p>
				<input type="text" class="login-input" placeholder="ユーザ名"
					name="username" />
				<p style="color: red">${nameError}</p>
				<input type="password" class="login-input" placeholder="パスワード"
					name="password" />
				<p style="color: red">${passError}</p>
				<p>
					性別：<input type="radio" name="gender" value="男性" checked>男性 <input
						type="radio" name="gender" value="女性">女性
				</p>
				<input type="text" class="login-input" placeholder="19980102"
					name="birth" />

				<div class="submit-container">
					<button type="submit" class="login-button" name="send" value="send">送信</button>
					<button type="submit" class="login-button" name="send" value="back">戻る</button>
				</div>
			</form>
		</section>

	</div>

</body>
</html>