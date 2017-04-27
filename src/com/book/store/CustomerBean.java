package com.book.store;

public class CustomerBean {
	private String username;
	private String password;	
	private String retype;
	private String nickname;
	private String firstname;
	private String lastname;
	private String email;
	private String birthyear;
	private String address;
	private String creditcard;
	private String status;
	private int uid;

	public CustomerBean() {}

	public CustomerBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public CustomerBean(String username, String password, String retype,
			String firstname, String lastname, String email,
			String birthyear, String address, String creditcard) {
		super();
		this.username = username;
		this.password = password;
		this.retype = retype;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.birthyear = birthyear;
		this.address = address;
		this.creditcard = creditcard;
	}

	public String getName() {
		return username;
	}
	public void setName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCredit() {
		return creditcard;
	}
	public void setCredit(String creditcard) {
		this.creditcard = creditcard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String authenticate() {
		SqlConnect Sqlhandle = new SqlConnect();
		if (Sqlhandle.authenticate("users", "username", username, password)) {
			if (Sqlhandle.checkUserStatus("users", "status", username, "pending")) {
				Sqlhandle.close();
				return "please check your email.";
			}
			if (Sqlhandle.checkUserStatus("users", "status", username, "good")) {
				Sqlhandle.close();
				return "success";
			} else {
				Sqlhandle.close();
				return "account is banned";
			}
		} else {
			Sqlhandle.close();
			return "username does not exist or password is incorrect";
		}
	}

	public int getLoginId(String username) {
		SqlConnect Sqlhandle = new SqlConnect();
		int loginId = Sqlhandle.getId("uid", "users", "username", username);
		Sqlhandle.close();
		return loginId;
	}

	public CustomerBean getUserInfo(String username) {
		SqlConnect Sqlhandle = new SqlConnect();
		CustomerBean cb = Sqlhandle.getUserInfo(username);
		Sqlhandle.close();
		return cb;
	}

	public CustomerBean getUserInfo(int uid) {
		SqlConnect Sqlhandle = new SqlConnect();
		CustomerBean cb= Sqlhandle.getUserInfo(uid);
		Sqlhandle.close();
		return cb;
	}
	
	public String insertUser() {
		String state;
		if (!authUsername()) {
			state = "occupied username";
			return state;
		} else {
			SqlConnect Sqlhandle = new SqlConnect();
			Sqlhandle.insertUser(username, email, password, retype, firstname, lastname,
					address, birthyear, creditcard, "good");
			Sqlhandle.close();
		}
		return "success";
	}

	public String updateProfile() {
		SqlConnect Sqlhandle = new SqlConnect();
		String userId = Integer.toString(uid);
		if (!email.equals("")) {
			Sqlhandle.updateUserProfile("email", email, userId);
		}
		if (!password.equals("")) {
			Sqlhandle.updateUserProfile("password", password, userId);
		}
		if (!firstname.equals("")) {
			Sqlhandle.updateUserProfile("firstname", firstname, userId);
		}
		if (!lastname.equals("")) {
			Sqlhandle.updateUserProfile("lastname", lastname, userId);
		}
		if (!address.equals("")) {
			Sqlhandle.updateUserProfile("address", address, userId);
		}
		if (!birthyear.equals("")) {
			Sqlhandle.updateUserProfile("birthyear", birthyear, userId);
		}
		if (!creditcard.equals("")) {
			Sqlhandle.updateUserProfile("creditcard", creditcard, userId);
		}
		Sqlhandle.close();
		return "success";
	}

	public boolean authUsername() {
		SqlConnect Sqlhandle = new SqlConnect();
		if (username == null || username.trim() == "") {
			Sqlhandle.close();
			return false;
		} else if (Sqlhandle.authUsername("users", "username", username)) {
			Sqlhandle.close();
			return false;			
		}
		Sqlhandle.close();
		return true;
	}

	public void unban() {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.unbanUser(uid);
		Sqlhandle.close();
	}

	public void ban() {
		SqlConnect Sqlhandle = new SqlConnect();
		Sqlhandle.banUser(uid);
		Sqlhandle.close();
	}
}
