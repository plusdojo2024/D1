package model;

public class grade {
	private String login_id; // ID
	private String date;//日付
	private String subject; //科目
	private int score; //得点

	public grade(String login_id, String date, String subject, int score) {
		super();
		this.login_id = login_id;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
