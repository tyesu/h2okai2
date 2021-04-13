package model;
import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {


	private String name;			// ユーザー名
	private int money;				// 所持金
	private List<Cake> cartCakes;	// 購入検討中のケーキ
	private List<Cake> boughtCakes;	// 購入したケーキ


	public UserData() {
		this.name = "名無しさん";
		this.money = 0;
	}
	public UserData(String name, int money) {
		this.name = name;
		this.money = 2000;		// とりあえず2,000円とする
	}


	public String getName() {return this.name;}
	public int getMoney() {return this.money;}
	public List<Cake> getCartCakes() {return this.cartCakes;}
	public List<Cake> getBoughtCakes() {return this.boughtCakes;}


	// カートに入れる
	public void keepCake(Cake cake) {
		this.cartCakes.add(cake);
	}


	// 支払い。所持金が減少
	public void payment(int price) {
		this.money -= price;

		// カートの商品を持ち物に移す
		for (Cake cake: this.cartCakes) {
			this.boughtCakes.add(cake);
		}

		// カートは空に
		this.cartCakes.clear();
	}

}