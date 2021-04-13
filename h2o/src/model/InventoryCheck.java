package model;

import java.util.HashMap;
import java.util.List;

// 在庫確認を担う

public class InventoryCheck {


	// カートの情報、商品情報を持っている
	private HashMap<String, Integer> cartData;
	private List<Cake> cakes;


	// constructor
	public InventoryCheck(CartCounter counter, List<Cake> cakes) {
		this.cartData = counter.getCounter();
		this.cakes = cakes;
	}


	// 在庫数以上にカートへ入れようとしたらtrue、問題なしでfalse
	public boolean isBreakLimit(String cakeName) {

		for (Cake cake: this.cakes ) {								// 登録商品全てをチェック
			if (cake.getShohin_mei().equals(cakeName)) {			// 引数と同じ商品をチェック

				int cartItemCount = 0;								// カートの商品数
				try {
					cartItemCount = this.cartData.get(cakeName);
				} catch (NullPointerException e) {
					cartItemCount = 0;								// 初回はヌルポなので０で設定
				}
				int inventory = cake.getZaikosu();					// 商品在庫数

				// 在庫数以上にカートに入ることになるか
				if (cartItemCount + 1 > inventory) {
					return true;
				}
			}
		}

		return false;
	}
}
