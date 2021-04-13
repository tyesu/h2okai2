package model;

// 人を表現

public interface Human {

	/* getter..............*/
	public abstract String getUser_mei();
	public abstract String getPassword();
	public abstract boolean isLogin();

	/* setter........................*/
	public abstract void login();
	public abstract void logout();
}
