<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン失敗画面</title>
	<link rel="stylesheet" href="index.css">
	<link href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"   rel="stylesheet">
</head>
<style>
		<%@ include file="./failedScreen.css"%>
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
		<p class="error">ユーザーID、パスワードの入力に誤りがあるか登録されていません。再度ログインしてください。</p>
		<form action="/h2o/ToLogin" method="post">
			<h2>h2oShop会員ログイン</h2>
			<table>
				<tr>
					<td class="account">ユーザー名:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td class="account">パスワード:</td>
					<td><input type="password" name="pass"></td>
				</tr>
			</table>
			<input type="submit" style="width: 100%;" value="ログイン">
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