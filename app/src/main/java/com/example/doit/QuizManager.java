package com.example.doit;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizManager {
    private List<Question> questions;

    public QuizManager(String category){
        this.questions = QuestionBank.getQuestions(category);
        Collections.shuffle(questions);
        if(questions.size() > 10){
            questions = questions.subList(0,10);
        }
    }

    public List<Question> getQuestions(){
        return questions;
    }
}
