<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import= "dao.*, model.*, java.util.*" %>

<%
//在庫調整は成功したのか
int isSuccess = CakeDAO.isDone();
%>

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
		<main id= "purchasingMain">
			<div class= "formContainer">
				<h2>新商品の登録</h2>
				<form class= "forms" action= "/h2o/Register" method= "post">
					<div class= "shortText">
						<p class= "inputLabel">商品ID</p>
						<input class= "inputForm" type= "text" name= "id"><br>

						<p class= "inputLabel">商品名</p>
						<input class= "inputForm" type= "text" name= "name"><br>

						<p class= "inputLabel">販売単価</p>
						<input class= "inputForm" type= "number" name= "price"><br>

						<p class= "inputLabel">在庫数</p>
						<input class= "inputForm" type= "number" name= "stock"><br>
					</div>
					<div class= "longText">
						<p class= "inputLabel">説明文</p><br>
						<textarea class= "itemText inputForm" name= "text"></textarea><br>
					</div>
					<input class= "btn" type= "submit" value= "登録実行">

					<% if (isSuccess == 1) { %>
						<p>商品登録処理が完了しました。</p>
					<% } else if (isSuccess == 2) { %>
						<p style= "color: red;">商品登録処理は実行されませんでした。</p>
					<% } else if (isSuccess == 0) { %>
					<% } %>
					<% CakeDAO.undo(); %><%-- ここで処理状況を初期化 --%>

				</form>
			</div>
		</main>
		<footer>
			<form action= "/h2o/GoHome" method= "post">
				<input type= "submit" value= "他の処理へ">
			</form>
		</footer>
	</div>
</body>
</html>
