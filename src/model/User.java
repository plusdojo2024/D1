package model;
import java.io.Serializable;

public class User implements Serializable {
	private int id;	// ID
	private String user_id;	// User_ID
	private String user_name; // User_Name
	private String password; //PassWord
	private int n_question; //質問数
	private int n_answer; // 質問回答数
	private String s_content; // 質問内容（生徒間）
	private int s_answer; // 質問回答（生徒間）
	private String t_content; // 質問内容（教師）
	private int t_answer; // 質問回答（教師）
	private String subject; //科目
	private int score; //得点



	public User(String user_id, String password) {
		this.user_id = user_id;
		this.password = password;
	}

	public User() {
		this.user_id = "";
		this.password = "";
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id セットする user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	 * @return n_question
	 */
	public int getN_question() {
		return n_question;
	}

	/**
	 * @param n_question セットする n_question
	 */
	public void setN_question(int n_question) {
		this.n_question = n_question;
	}

	/**
	 * @return n_answer
	 */
	public int getN_answer() {
		return n_answer;
	}

	/**
	 * @param n_answer セットする n_answer
	 */
	public void setN_answer(int n_answer) {
		this.n_answer = n_answer;
	}

	/**
	 * @return s_content
	 */
	public String getS_content() {
		return s_content;
	}

	/**
	 * @param s_content セットする s_content
	 */
	public void setS_content(String s_content) {
		this.s_content = s_content;
	}

	/**
	 * @return s_answer
	 */
	public int getS_answer() {
		return s_answer;
	}

	/**
	 * @param s_answer セットする s_answer
	 */
	public void setS_answer(int s_answer) {
		this.s_answer = s_answer;
	}

	/**
	 * @return t_content
	 */
	public String getT_content() {
		return t_content;
	}

	/**
	 * @param t_content セットする t_content
	 */
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}

	/**
	 * @return t_answer
	 */
	public int getT_answer() {
		return t_answer;
	}

	/**
	 * @param t_answer セットする t_answer
	 */
	public void setT_answer(int t_answer) {
		this.t_answer = t_answer;
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


}
