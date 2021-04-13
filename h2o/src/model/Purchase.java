package model;

import java.util.HashMap;

// 購入処理の補助を行う

public class Purchase {


	private Account target;							// 処理対象
	private HashMap<String, Integer> cartData;		// カートの状況（商品の種類、それぞれの個数）


	public Purchase(Account account, CartCounter cart) {
		this.cartData = new HashMap<String, Integer>();
		this.cartData = cart.getCounter();
		this.target = account;
	}


	public Account getAccount() {return this.target;}
	public HashMap<String, Integer> getCartData() {return this.cartData;}


	// 購入できるか（カート商品）
	public boolean isGetCake() {

		// 変数準備
		int total = this.target.getTotal();		// 購入金額
		int money = this.target.getMoney();		// ユーザー所持金

		// 所持金が足りているか
		if (total <= money) {
			return true;

		// 残高が足りていないなら購入失敗
		} else {
			return false;
		}
	}


	// カート商品は空か
	public boolean isCartEnpty() {
		if (this.target.getCartCakes().isEmpty()) {
			return true;
		}
		return false;
	}
}