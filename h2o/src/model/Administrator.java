package model;

public class Administrator implements Human {

	/* 管理者情報.........*/
	private Human admin;
	private String name;
	private String pass;
	private boolean login;

	/* constructor........*/
	public Administrator(Human admin) {
		this.admin = admin;
		this.name = admin.getUser_mei();
		this.pass = admin.getPassword();
		this.login = false;
	}

	/* getter.................*/
	public String getUser_mei() {return this.name;}
	public String getPassword() {return this.pass;}

	/* setter............*/
	public void login() {this.login = true;}
	public void logout() {this.login = false;}

	/* method................*/
	public boolean isLogin() {return this.login;}
}
