package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
	private String login_id;	// ID
	private String user_name; // 氏名
	private String password; //PassWord
	private String content; // 質問内容（生徒間）
	private int answer; // 質問回答（生徒間）
	private LocalDateTime date;
	private String subject; //科目
	private int score; //得点



	public User(String login_id, String password) {
		this.login_id = login_id;
		this.password = password;
	}

	public User() {
		this.login_id = "";
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
	/**
	 * @return s_content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param s_content セットする content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @param s_answer セットする answer
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject セットする subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score セットする score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setScore(LocalDateTime date) {
		this.date = date;
	}

}
