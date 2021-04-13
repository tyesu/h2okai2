<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Cake" %>

<%
Cake cake = (Cake) request.getAttribute("cake");
%>

<!DOCTYPE html>
<html>
	<head>
	     <meta charset="UTF-8">
	     <title>商品の紹介画面</title>
	     <link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet">
	</head>
	<style>
		<%@ include file="./product.css"%>
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
		    <form action="/h2o/SearchServlet" method="post">
			    <input class="search" type="search" name="search" placeholder="キーワードを入力">
			    <input class="submit" type="submit" value="検索">
		    </form>
		    <p> <%-- ケーキの画像を表示 --%>
	  			<img src= "./img/<%=cake.getShohin_id() %>.jpg" width= "150" height= "100">
	  		</p>
		    <h2><%= cake.getShohin_mei() %></h2>
		    <p>金額 : <%= cake.getHanbai_tanka() %>円</p>
		    <p>在庫数 : <%= cake.getZaikosu() %></p>
		    <p><%= cake.getBun() %></p>
		    <form action= "/h2o/CartIn" method= "post">
		        <input type= "submit" value= "カートにいれる">
		        <input type= "hidden" name= "cake" value= "<%= cake.getShohin_id() %>">
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