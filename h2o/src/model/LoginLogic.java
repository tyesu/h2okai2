package model;

import java.sql.SQLException;
import java.util.List;

import dao.AccountDAO;

public class LoginLogic {

	AccountDAO dao = new AccountDAO();

	public boolean execute(Account account) throws ClassNotFoundException, SQLException {

		// データベースの登録アカウントを取得してliに代入
		List<Account> li = dao.findAll();

		// liをaに1つずつ取り出す
		for(Account a : li) {

			// ログイン画面で入力された情報がデータベースに登録された情報が
			if(account.getPassword().equals(a.getPassword()) &&

				// 同じだった場合(パスワードとユーザー名)
				account.getUser_mei().equals(a.getUser_mei()) ) {
				return true;					// trueを返す
			}
		}
		return false;							// 違った場合falseを返す
	}
}