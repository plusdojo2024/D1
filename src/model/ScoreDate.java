package model;

import java.time.Month;
import java.util.Date;

public class ScoreDate {
    private String subject;
    private int score;
    private Date date;
    private int year;
    private Month month;

    public ScoreDate(String subject, int score, Date date, int year, Month month2) {
        this.subject = subject;
        this.score = score;
        this.date = date;
        this.year = year;
        this.month = month2;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}

