package com.example.quizapp.controller;
import com.example.quizapp.dto.QuizQuestionResponse;
import com.example.quizapp.dto.QuizSubmitRequest;
import com.example.quizapp.model.QuizQuestion;
import com.example.quizapp.model.QuizAnswer;
import com.example.quizapp.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuizQuestionResponse>> getQuestions() {
        return ResponseEntity.ok(quizService.allQuestions());
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizAnswer> submitQuiz(@RequestBody QuizSubmitRequest request) {
        QuizAnswer result = quizService.submitQuiz(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/question")
    public ResponseEntity<String> addQuestion(@RequestBody QuizQuestion question) {
        quizService.addQuestions(question);
        return new ResponseEntity<>("question added successfully", HttpStatus.CREATED);
    }
}
