<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Account, java.util.*, model.Cake, model.CartCounter"%>
<%
Account a = (Account)session.getAttribute("Account");
List<Cake> cartCakes = a.getCartCakes();    // かごに追加したケーキをリストに追加

// カートのカウンターを用意
CartCounter cart = (CartCounter) session.getAttribute("cartCounter");
HashMap<String, Integer> cartCounter = cart.getCounter();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>h2o</title>
		<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<style>
		<%@ include file="./CartIn.css"%>
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
			<form action="SearchServlet" method="post">
				<input class="search" type="search" name="search" placeholder="キーワードを入力">
				<input class="submit" type="submit" value="検索">
			</form>
			<p>	商品カート : <%=cartCakes.size()%></p>
			<% for(Cake c : cartCakes ) {%>
				<p><%=c.getShohin_mei() %> ￥<%= c.getHanbai_tanka() %></p>
			<% } %>
			<form action="ToPurchase" method="post">
				<p><input type="submit"  value="購入"></p>
			</form>
			<form action="Login" method="post">
				<p><input type="submit" value="ホーム画面に戻る"></p>
			</form>
			<form action = "delete" method = "get">
				<input type = "submit" value = "カート商品を削除">
			</form>
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