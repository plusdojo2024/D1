package model;

import java.time.Month;
import java.time.Year;
import java.util.Date;

public class ScoreDate {
    private String subject;
    private int score;
    private Date date;
    private Year year;
    private Month month;

    public ScoreDate(String subject, int score, Date date, Year year2, Month month2) {
        this.subject = subject;
        this.score = score;
        this.date = date;
        this.year = year2;
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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}

