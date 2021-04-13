package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CakeDAO;

// 会員アカウントを表現

public class Account implements Human, java.io.Serializable {


	private String user_mei;			// ユーザー名
	private String password;			// パスワード
	private int money;					// 所持金
	private List<Cake> cartCakes;		// 購入検討中のケーキ
	private List<Cake> boughtCakes;		// 購入したケーキ
	private int total;					// カート商品代金の合計
	private String search;				// 検索ワードを記憶しておく
	private boolean failed;				// 残高不足か
	private boolean breakLimit;			// カートに詰めすぎたか（在庫＜カート点数）
	private boolean login;				// ログイン状態か


	public Account() {}
	public Account(String user_mei, String password) {
		this.user_mei = user_mei;
		this.password = password;
		this.money = 1500;
		this.cartCakes = new ArrayList<Cake>();
		this.boughtCakes = new ArrayList<Cake>();
		this.total = 0;
		this.failed = false;
		this.breakLimit = false;
	}


	public String getUser_mei() {return this.user_mei;}
	public String getPassword() {return this.password;}
	public int getMoney() {return this.money;}
	public List<Cake> getCartCakes() {return this.cartCakes;}
	public List<Cake> getBoughtCakes() {return this.boughtCakes;}
	public int getTotal() {return this.total;}
	public String getSearch() {return this.search;}
	public boolean getFailed() {return this.failed;}
	public boolean getBreakLimit() {return this.breakLimit;}
	public boolean isLogin() {return this.login;}


	public void setMoney(int money) {this.money = money;}
	public void setTotal(int money) {this.total = money;}
	public void setSearch(String search) {this.search = search;}

	// ログイン（アウト）状態になる
	public void login() {this.login = true;}
	public void logout() {this.login = false;}


	// カートに入れる
	public void setCake(Cake cake) {
		this.cartCakes.add(cake);			// カートへ追加
	}


	// カートに入れる動作を無かったことにする
	public void setCakeUndo() {
		this.cartCakes.remove(
			this.cartCakes.size() - 1);		// 最後に追加したケーキを削除
		}


	// 購入商品を更新する
	public void setBoughtCakes() {
		for (Cake c: this.cartCakes) {
			this.boughtCakes.add(c);
		}
	}


	// カートの商品を初期化
	public void resetCartCakes() {
		this.cartCakes.clear();
	}


	// 支払い。各種フィールドを更新
	public void payment(CartCounter cartCounter) {
		this.money -= this.total;		// 所持金の更新
		this.setBoughtCakes();			// 購入商品を更新
		this.resetCartCakes();			// カート商品を空に

		// DBの在庫を更新
		CakeDAO dao = CakeDAO.getInstance();
		try {
			dao.reduceInventory(cartCounter);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	// failed状態の切り替え
	public void toggleFailed() {
		if (this.failed == true) {
			this.failed = false;
		} else {
			this.failed = true;
		}
	}


	// breakLimit状態の切り替え
	public void toggleBreakLimit() {
		if (this.breakLimit == true) {
			this.breakLimit = false;
		} else {
			this.breakLimit = true;
		}
	}
}