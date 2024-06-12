package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Question implements Serializable {
	private String login_id;  //ログイン時のID
	private LocalDateTime date;  //ログイン日時
	private String content;  //質問内容
	private String answer;  //質問回答
	private String subject;  //科目

	//引数がないコンストラクタ
	public Question() {

	}

	//引数があるコンストラクタ
	public Question(String login_id, LocalDateTime date, String content, String answer, String subject) {
		super();
		this.login_id = login_id;
		this.date = date;
		this.content = content;
		this.answer = answer;
		this.subject = subject;
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
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setDate(LocalDateTime date) {
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