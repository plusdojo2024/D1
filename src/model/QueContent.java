package model;

import java.io.Serializable;

public class QueContent implements Serializable {
	private String login_id;  //ログイン時のID
	private String date;  //更新日時
	private String content;  //質問内容
	private String answer;  //質問回答
	private String subject;  //科目

	//引数がないコンストラクタ
	public QueContent() {

	}

	//引数があるコンストラクタ
	public QueContent(String login_id, String date, String content, String answer, String subject) {
		super();
		this.login_id = login_id;
		this.date = date;
		this.content = content;
		this.answer = answer;
		this.subject = subject;
	}

	public QueContent(String content, String subject, String date) {
		super();
		this.content = content;
		this.subject = subject;
		this.date = date;
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
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content セットする content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer セットする answer
	 */
	public void setAnswer(String answer) {
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


}