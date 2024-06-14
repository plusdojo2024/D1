package model;
import java.io.Serializable;

public class User implements Serializable {
	private String login_id;	// ID
	private String user_name; // 氏名
	private String password; //PassWord
	//private String content; // 質問内容（生徒間）
	//private int answer; // 質問回答（生徒間）
	//private LocalDateTime date;
	//private String subject; //科目
	//private int score; //得点



	public User(String login_id, String user_name, String password) {
		this.login_id = login_id;
		this.user_name = user_name;
		this.password = password;
	}

	public User(String login_id, String password) {
		this.login_id = login_id;
		this.user_name = "";
		this.password = password;
	}

	public User() {
		this.login_id = "";
		this.user_name = "";
		this.password = "";
	}
	/**
	 * @return login_id
	 */
	public String getLogin_id() {
		return login_id;
	}

	/**
	 * @param login_id セットする login_id
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	/**
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name セットする user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
