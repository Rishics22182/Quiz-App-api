package com.example.quizapp.service;
import com.example.quizapp.dto.QuizQuestionResponse;
import com.example.quizapp.dto.QuizSubmitRequest;
import com.example.quizapp.model.QuizQuestion;
import com.example.quizapp.model.QuizAnswer;
import com.example.quizapp.repository.QuizRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<QuizQuestionResponse> allQuestions() {
        List<QuizQuestion> questions = quizRepository.allQuestions();
        List<QuizQuestionResponse> responseList = new ArrayList<>();

        for (QuizQuestion q : questions) {
            QuizQuestionResponse res = new QuizQuestionResponse();
            res.setId(q.getId());
            res.setQuestion(q.getQuestion());
            List<String> options = new ArrayList<>();
            options.add(q.getOptionA());
            options.add(q.getOptionB());
            options.add(q.getOptionC());
            options.add(q.getOptionD());
            res.setOptions(options);
            responseList.add(res);
        }

        return responseList;
    }

    @Override
    public QuizAnswer submitQuiz(QuizSubmitRequest request) {
        Map<Integer, String> answers = request.getAnswers();
        int score = 0;
        int total = 0;
        for (Integer questionId : answers.keySet()) {
            total = total + 1;
            String userAnswer = answers.get(questionId);
            List<QuizQuestion> questionList = quizRepository.findById(questionId);
            if (questionList.isEmpty()) {
                throw new RuntimeException("invalid question id: " + questionId);
            }
            QuizQuestion question = questionList.get(0);
            if (question.getCorrectOption().equalsIgnoreCase(userAnswer)) {
                score = score + 1;
            }
        }
        quizRepository.saveResult("user1", score, total);
        QuizAnswer result = new QuizAnswer();
        result.setScore(score);
        result.setTotal(total);
        return result;
    }

    @Override
    public void addQuestions(QuizQuestion question) {
        quizRepository.addQuestions(question);
    }
}
