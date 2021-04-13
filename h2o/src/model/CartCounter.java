package model;

import java.util.HashMap;

// カートの商品、その個数を覚える

public class CartCounter {


	// key= 商品名  value= 在庫数
	private HashMap<String, Integer> counter;


	// constructor
	public CartCounter() {
		this.counter = new HashMap<String, Integer>();
	}


	public HashMap<String, Integer> getCounter() {return this.counter;}


	// カートの商品数をひとつ減らす
	public void countDown(String name) {
		boolean isContain = this.counter.containsKey(name);

		// 引数の商品が登録されていれば
		if (isContain == true) {
			int count = this.counter.get(name);
			count -= 1;
			this.counter.put(name, count);
		}
	}


	// カートの商品数をｶｳﾝﾄｱｯﾌﾟする
	public void countUp(String name) {
		boolean bool = this.counter.containsKey(name);

		// 引数がマップに登録されていないなら
		if (bool == false) {

			// 商品xxxxが１個として登録
			this.counter.put(name, 1);

		// 引数にマップが登録されている場合
		} else {

			// 現在のカートの商品数を取得
			int count = this.counter.get(name);
			count += 1;
			this.counter.put(name, count);
		}
	}
}
