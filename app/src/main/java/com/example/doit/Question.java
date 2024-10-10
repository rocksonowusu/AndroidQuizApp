package com.example.doit;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private String correctAnswer;

    public Question(String question, List<String> options, String correctAnswer){
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.question = question;
    }

    public String getQuestion(){
        return question;
    }

    public List<String> getOptions(){
        return options;
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

}
