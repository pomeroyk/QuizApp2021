package com.example.quizapp2021;

public class Question {
    String questionText;
    boolean correctAns;
    int imageName;


    //to make the ide do the rest, right click on whitespace and pick Generate..
    //pick constructor!!

    public Question(String questionText, boolean correctAns, int i) {
        this.questionText = questionText;
        this.correctAns = correctAns;
        imageName = i;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getImageName() { return imageName;  }
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
