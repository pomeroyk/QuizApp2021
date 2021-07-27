package com.example.quizapp;

public class Question {
    String questionText;
    boolean correctAns;

    //to make the ide do the rest, right click on whitespace and pick Generate..
    //pick constructor!!

    public Question(String questionText, boolean correctAns) {
        this.questionText = questionText;
        this.correctAns = correctAns;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isCorrectAns() {
        return correctAns;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setCorrectAns(boolean correctAns) {
        this.correctAns = correctAns;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", correctAns=" + correctAns +
                '}';
    }
}
