package com.book.store;

public class Administration {
	private String administrator;
	private String password;

	public Administration() {}

	public Administration(String administrator, String password) {
		super();
		this.administrator = administrator;
		this.password = password;
	}
	
	public String authenticate() {
		SqlConnect Sqlhandle = new SqlConnect();
		if (Sqlhandle.authenticate("admin", "admin", administrator, password)) {
			Sqlhandle.close();
			return "success";
		} else {
			Sqlhandle.close();
			return "password is incorrect or username does not exist";
		}
	}
}