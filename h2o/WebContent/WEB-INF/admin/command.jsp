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
		<main id= "commandMain">
			<div class= "forms">
				<form class= "commandForm" action= "/h2o/CommandSelector" method= "post">
					<p>商品仕入</p>
					<input type= "submit" value= "Enter">（工事中）
					<input type= "hidden" name= "go" value= "purchasing">
				</form><br>
				<form class= "commandForm" action= "/h2o/CommandSelector" method= "post">
					<p>在庫操作</p>
					<input type= "submit" value= "Enter">
					<input type= "hidden" name= "go" value= "inventory">
				</form><br>
				<form class= "commandForm" action= "/h2o/CommandSelector" method= "post">
					<p>アカウント操作</p>
					<input type= "submit" value= "Enter">（工事中）
					<input type= "hidden" name= "go" value= "account">
				</form><br>
				<form class= "commandForm" action= "/h2o/CommandSelector" method= "post">
					<p>ログアウト</p>
					<input type= "submit" value= "Enter">（工事中）
					<input type= "hidden" name= "go" value= "logout">
				</form><br>
			</div>
		</main>
	</div>
</body>
</html>
