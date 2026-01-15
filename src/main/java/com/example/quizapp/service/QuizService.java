package com.example.quizapp.service;
import com.example.quizapp.dto.QuizQuestionResponse;
import com.example.quizapp.dto.QuizSubmitRequest;
import com.example.quizapp.model.QuizQuestion;
import com.example.quizapp.model.QuizAnswer;
import java.util.List;

public interface QuizService {
    List<QuizQuestionResponse> allQuestions();
    QuizAnswer submitQuiz(QuizSubmitRequest request);
    void addQuestions(QuizQuestion question);
}
