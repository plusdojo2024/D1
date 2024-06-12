package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Question implements Serializable {
	private String login_id;  //ログイン時のID
	private Date date;  //更新日
	private Time time;  //更新時間
	private String content;  //質問内容
	private String answer;  //質問回答
	private String subject;  //科目

	//引数がないコンストラクタ
	public Question() {

	}

	//引数があるコンストラクタ
	public Question(String login_id, Date date, Time time, String content, String answer, String subject) {
		super();
		this.login_id = login_id;
		this.date = date;
		this.time = time;
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
	public Date getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @param time セットする time
	 */
	public void setTime(Time time) {
		this.time = time;
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