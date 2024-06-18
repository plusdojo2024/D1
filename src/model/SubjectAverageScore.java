package model;

public class SubjectAverageScore {
    private String subject;
    private int year;
    private int month;
    private double avgScore;

    // コンストラクタ
    public SubjectAverageScore(String subject, int year, int month, double avgScore) {
        this.subject = subject;
        this.year = year;
        this.month = month;
        this.avgScore = avgScore;
    }

    // ゲッターとセッター
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
}
