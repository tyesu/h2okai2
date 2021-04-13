package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cake;
import model.CartCounter;

// 商品DB（ケーキ）を操作する

public class CakeDAO {


	// 接続情報.........................//
	private static CakeDAO dao = new CakeDAO();									// 自身のインスタンス
	private Connection conn = null;
	private static int isDone = 0;												// 0：未処理 1：成功 2：失敗
	private final String URL = "jdbc:postgresql://localhost:5432/h2oshop";		// 教室用
	private final String USER = "postgres";
	private final String PASS = "ok";


	// constructor（new不可）.......................//
	private CakeDAO() {
		try {
			// 接続
			Class.forName("org.postgresql.Driver");
			this.conn = DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	// インスタンスを取得.............//
	public static CakeDAO getInstance() {return dao;}


	// SQL実行に成功したか
	public static int isDone() {return isDone;}
	// SQL実行が成功した
	public static void success() {isDone = 1;}
	// SQL実行が失敗した
	public static void error() {isDone = 2;}
	// SQL処理状況のリセット
	public static void undo() {isDone = 0;}


	// 新商品の登録......................//
	// 登録要素 → ID、商品名、価格、在庫、商品説明文
	public boolean register(String id, String name, int price, int stock, String text) throws SQLException {

		// IDはDBで固定長の3文字としている
		if (id.length() == 3) {

			// sql文の組み立て
			StringBuilder sql = new StringBuilder("insert into cake values ");
			sql.append("(?, ?, ?, ?, ? );");

			// sql文の用意、値の組み込み
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.setString(5, text);

			// sql実行
			ps.execute();

			return true;
		}
		return false;
	}


	// 在庫の修正....................//
	public void updateInventory(String name, int stock) throws SQLException, ClassNotFoundException {

		// sql文の組み立て
		StringBuilder sql =	new StringBuilder("update cake set zaikosu = ? ");
		sql.append("where shohin_mei = ? ;");

		// sql文の準備
		PreparedStatement ps = this.conn.prepareStatement(sql.toString());
		ps.setInt(1, stock);
		ps.setString(2, name);

		// 実行
		ps.executeUpdate();
	}


	// 商品登録..................................//
	public void addItem(String id, String name, int price, int stock, String text) throws ClassNotFoundException, SQLException {

		// sql文の作成、準備
		StringBuilder sql =	new StringBuilder("insert into cake values ( ");
		sql.append("?, ?, ?, ?, ? );");							// 後で登録内容を埋め込む

		PreparedStatement ps = this.conn.prepareStatement(sql.toString());

		// 登録内容を埋め込み
		ps.setString(1, id);
		ps.setString(2, name);
		ps.setInt(3, price);
		ps.setInt(4, stock);
		ps.setString(5, text);

		// sql実行
		ps.execute();
	}


	// 検索したキーワードで部分一致検索を行う..................//
	public List<Cake> findItem(String item) throws SQLException, ClassNotFoundException {

		// 命令文の用意。
		StringBuilder sql = new StringBuilder("select * ");
		sql.append("from cake ");
		sql.append("where shohin_mei like ? ");
		sql.append("order by shohin_id asc ;");

		// 引数の文字列を埋め込む
		PreparedStatement ps = this.conn.prepareStatement(sql.toString());
		ps.setString(1, "%" + item + "%");

		// sql文実行、取得した表の確保。リストへの詰め込み
		ResultSet rs = ps.executeQuery();
		List<Cake> cakes = new ArrayList<Cake>();
		while (rs.next()) {

			// インスタンスにレコードを詰め込む
			Cake cake = new Cake(
					rs.getString("shohin_id"),
					rs.getString("shohin_mei"),
					rs.getInt("hanbai_tanka"),
					rs.getInt("zaikosu"),
					rs.getString("bun")
			);
			// インスタンスをリストに詰め込む
			cakes.add(cake);
		}
		return cakes;
	}


	// 登録商品を全て抽出する
	public List<Cake> findAll() throws SQLException, ClassNotFoundException {

		// 接続を安全に
		Class.forName("org.postgresql.Driver");

		// 接続する
		Connection conn = DriverManager.getConnection(URL, USER, PASS);

		// 命令文の用意。
		StringBuilder sql = new StringBuilder("select * ");
		sql.append("from cake ");
		sql.append("order by shohin_id asc ;");

		// 引数の文字列を埋め込む
		PreparedStatement ps = conn.prepareStatement(sql.toString());

		// sql文実行、取得した表の確保。リストへの詰め込み
		ResultSet rs = ps.executeQuery();
		List<Cake> cakes = new ArrayList<Cake>();
		while (rs.next()) {

			// インスタンスにレコードを詰め込む
			Cake cake = new Cake(
					rs.getString("shohin_id"),
					rs.getString("shohin_mei"),
					rs.getInt("hanbai_tanka"),
					rs.getInt("zaikosu"),
					rs.getString("bun")
			);
			// インスタンスをリストに詰め込む
			cakes.add(cake);
		}
		return cakes;
	}


	// 決済時の在庫の減少処理
	public void reduceInventory(CartCounter cartCounter) throws SQLException, ClassNotFoundException {

		// カート状況を把握
		Map<String, Integer> cart = cartCounter.getCounter();

		// 全データ取得後、マップに詰め込む
		List<Cake> cakes = this.findAll();
		Map<String, Integer> cakeData =	new HashMap<String, Integer>();

		for (Cake cake: cakes) {
			cakeData.put(cake.getShohin_mei(), cake.getZaikosu());
		}

		// 命令文の文字列を用意
		for (String cake: cart.keySet()) {

			// sql文の組み立て
			StringBuilder sql =	new StringBuilder("update cake set zaikosu = ? ");
			sql.append("where shohin_mei = ? ; ");
			PreparedStatement ps =
				this.conn.prepareStatement(sql.toString());

			// テンプレリテラル
			ps.setInt(1, cakeData.get(cake) - cart.get(cake));		// 在庫数 ー カート点数を埋め込む
			ps.setString(2, cake);									// 商品名を埋め込む

			// 実行
			ps.executeUpdate();
		}
	}
}