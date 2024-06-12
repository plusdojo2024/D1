package model;

import java.sql.Time;
import java.util.Date;


public class grade {
	private String login_id;	// ID
	private Date date;//日付
	private Time time;//時間
	private String subject; //科目
	private int score; //得点

	public grade(String login_id,Date date,Time time,String subject,int score) {
		super();
		this.login_id = login_id;
		this.date = date;
		this.time=time;
		this.subject = subject;
		this.score = score;
	}

	public grade() {
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}

