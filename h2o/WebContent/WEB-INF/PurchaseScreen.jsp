<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import= "model.Cake, model.Account" %>
<%@ page import= "java.util.*" %>

<%
// カート商品の呼び出し
Account account = (Account) session.getAttribute("Account");
List<Cake> cakes = account.getCartCakes();

// 合計金額の算出
int total = 0;
for (Cake cake: cakes) {

	total += cake.getHanbai_tanka();
};
account.setTotal(total);
session.setAttribute("Account", account);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入画面</title>
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<style>
		<%@ include file="./PurchaseScreen.css"%>
    </style>
<body>
	<header>
			<h1>h2oShop</h1>
			<i class="fas fa-tint"></i>
	</header>
	<nav>
		<div class="genre">
			<h2>ジャンル</h2>
			<a>一覧</a>
		</div>
		<div class="list">
			<ul>
				<li>ファッション・インナー</li>
				<li>ファッション小物</li>
				<li>キッズ・ベビー玩具</li>
				<li>スポーツ・ゴルフ</li>
				<li>家電・テレビ・カメラ</li>
				<li>PC・スマホ・通信</li>
				<li>食品・スイーツ</li>
				<li>ドリンク・お酒</li>
				<li>インテリア・寝具</li>
			</ul>
		</div>
	</nav>
	<main>
		<h1>注文確認</h1>
		<% for (Cake cake: cakes) { %>
			<div class= "cakes">
			<img src= "./img/<%= cake.getShohin_id() %>.jpg" width= "150" height= "100">
				<p><%= cake.getShohin_mei() %></p>
				<p>金額 : <%= cake.getHanbai_tanka() %>円</p>
			</div>
		<% } %>
		<div class= "itemCount">
			<p>商品かご：<%= cakes.size() %></p>
		</div>
		<div class= "total">
			<p>合計金額：<%= total %>円</p>
		</div>
		<div class= "total">
			<p>ウォレット：<%= account.getMoney() %></p>
		</div>
		<form action= "ToOrderComp" method= "post">
			<input type= "submit" value= "注文確定">
		</form>
		<p>
			<form action= "CartIn" method= "post">
				<input type= "submit" value= "カートへ戻る">
			</form>
		</p>
	</main>
	<aside>
		<img src="https://source.unsplash.com/random" alt="unsplash random" width="193" height="300">
	</aside>
	<footer>
		<div class="corporate-container">
			<ul>
				<li>会社概要</li>
				<li>投資家向け情報 [Investor Relations]</li>
				<li>社会的責任[CSR]</li>
				<li>プレスルーム</li>
				<li>採用情報</li>
				<li>個人情報保護方針</li>
			</ul>
		</div>
		<div class="ft-global-container">
			<p class="ri-copyright">
				<font size="-1"> © h2o Group, Inc.</font>
			</p>
		</div>
	</footer>
</body>
</html>