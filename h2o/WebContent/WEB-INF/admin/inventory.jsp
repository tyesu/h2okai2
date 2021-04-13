<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import= "dao.*, model.*, java.util.*" %>

<%
// ケーキ情報を取得
CakeDAO dao =  CakeDAO.getInstance();
List<Cake> cakes = dao.findAll();

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
		<main id= "inventoryMain">
			<div class= "formContainer">
				<form action= "/h2o/Inventory" method= "post">
					<h2>在庫数の調整</h2>
					<p class= "inputLabel">商品名</p>
					<input name= "item" type= "search" list= "list">
						<datalist id= "list">

							<% for (Cake cake: cakes) { %>
								<option value= <%= cake.getShohin_mei() %>>
							<% } %>

						</datalist>
					<p class= "inputLabel">在庫数</p>
					<input class= "inputForm" type= "number" name= "stock"><br>
					<input class= "btn" type= "submit" value= "在庫数決定"><br>

					<% if (isSuccess == 1) { %>
						<p>在庫調整処理が完了しました。</p>
					<% } else if (isSuccess == 2) { %>
						<p style= "color: red;">在庫調整処理は実行されませんでした。</p>
					<% } else if (isSuccess == 0) { %>
					<% } %>
					<% CakeDAO.undo(); %><%-- ここで処理状況を初期化 --%>

				</form><br>
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
