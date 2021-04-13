package model;

// ケーキを表現する

public class Cake implements java.io.Serializable {


	private String shohin_id;	// id
	private String shohin_mei;	// ケーキの名前
	private int hanbai_tanka;	// 販売単価
	private int zaikosu;		// 在庫数
	private String bun;			// 商品説明


	public Cake() {}
	public Cake(
			String shohin_id,
			String shohin_mei,
			int hanbai_tanka,
			int zaikosu,
			String bun) {
		this.shohin_id = shohin_id;
		this.shohin_mei = shohin_mei;
		this.hanbai_tanka = hanbai_tanka;
		this.zaikosu = zaikosu;
		this.bun = bun;
	}


	public String getShohin_id() {return this.shohin_id;}
	public String getShohin_mei() {return this.shohin_mei;}
	public int getHanbai_tanka() {return this.hanbai_tanka;}
	public int getZaikosu() {return this.zaikosu;}
	public String getBun() {return this.bun;}


	// 文字列表現
	public String toString() {
		return getShohin_id() + ", " + getShohin_mei() +
				", 在庫数：" + getZaikosu();
	}
}