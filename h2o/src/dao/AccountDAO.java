package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;

// 顧客情報のDBを操作する

public class AccountDAO {


	// 接続情報
	private final String URL = "jdbc:postgresql://localhost:5432/h2oshop";		// 教室用
	private final String USER = "postgres";
	private final String PASS = "ok";


	// コンストラクタ
	public AccountDAO() {
		super();
	}


	// 登録アカウントを全部取得してリストで返す
	public List<Account> findAll() throws SQLException, ClassNotFoundException {

		// ArrayListを作成
		List<Account> accountList = new ArrayList<>();

		// データベース接続
		Class.forName("org.postgresql.Driver");

		try(Connection conn = DriverManager.getConnection(URL, USER, PASS)){

			// select文の準備
			String sql = "select user_mei, password from account;";		// 教室用
//			String sql = "select name, pass from account;";				// 尾畑用

			// sqlをdbに届けるPreparedStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// select文を実行
			ResultSet rs = pStmt.executeQuery();

			// select文の結果をArrayListに格納
			while(rs.next()) {
				String user_mei = rs.getString("user_mei");
				String password = rs.getString("password");
				Account account = new Account(user_mei, password);
				accountList.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return accountList;
	}

}