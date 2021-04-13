<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>h2o 管理者コマンド</title>
	<link rel= "stylesheet" href= "/h2o/admin/style/nomalize ver.8.0.1.css">
	<link rel= "stylesheet" href= "/h2o/admin/style/style.css">
</head>
<body>
	<header>
		<div class= "container">
			<h1>管理者コマンド</h1>
		</div>
	</header>
	<div class= "wrapper">
		<main id= "loginMain">
			<div class= "inputForm">
				<form action= "/h2o/AdminLogin" method= "post">
					<h2>管理者ログイン</h2>
					<p class= "inputLabel">admin name</p>
					<input class= "inputForm" type= "text" name= "id"><br>
					<p class= "inputLabel">password</p>
					<input class= "inputForm" type= "password" name= "pass"><br>
					<input class= "btn" type= "submit" value= "Login">
				</form>
			</div>
		</main>
	</div>
</body>
</html>
