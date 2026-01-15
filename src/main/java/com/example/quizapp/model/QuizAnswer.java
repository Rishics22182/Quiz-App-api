package com.example.quizapp.model;

public class QuizAnswer {
    private int score;
    private int total;

    public QuizAnswer() {

    }

    public QuizAnswer(int score, int total) {
        this.score = score;
        this.total = total;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }



}
