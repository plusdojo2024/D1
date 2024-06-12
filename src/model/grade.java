package model;

import java.time.LocalDateTime;

public class grade {
	private String login_id;	// ID
	private LocalDateTime date;//日付
	private String subject; //科目
	private int score; //得点



	public grade(String login_id) {
		this.login_id = login_id;
	}

	public grade() {
		this.login_id = "";
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

