package model;

import java.io.Serializable;

public class LoginUser implements Serializable {
	private String login_id;	// ログイン時のID

	public LoginUser() {
		this(null);
	}

	public LoginUser(String id) {
		this.login_id = id;
	}

	public String getId() {
		return login_id;
	}

	public void setUserId(String id) {
		this.login_id = id;
	}
}
