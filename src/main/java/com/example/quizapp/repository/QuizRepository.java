package com.example.quizapp.repository;
import com.example.quizapp.model.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRepository {

    private final JdbcTemplate jdbcTemplate;

    public QuizRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<QuizQuestion> quizQuestionRowMapper = (rs,rowNum)->{
        QuizQuestion q = new QuizQuestion();
        q.setId(rs.getInt("id"));
        q.setQuestion(rs.getString("question"));
        q.setOptionA(rs.getString("option_a"));
        q.setOptionB(rs.getString("option_b"));
        q.setOptionC(rs.getString("option_c"));
        q.setOptionD(rs.getString("option_d"));
        q.setCorrectOption(rs.getString("correct_option"));
        return q;
    };

    public void addQuestions(QuizQuestion question) {
        String sql = "INSERT INTO quiz_question " +
                "(question, option_a, option_b, option_c, option_d, correct_option) " +
                "VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                question.getQuestion(),
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC(),
                question.getOptionD(),
                question.getCorrectOption()
        );
    }

    public List<QuizQuestion> allQuestions() {
        String sql = "SELECT id, question, option_a, option_b, option_c, option_d, correct_option FROM quiz_question";
        return jdbcTemplate.query(sql, quizQuestionRowMapper);
    }

    public List<QuizQuestion> findById(int id) {
        String sql = "select * from quiz_question where id = ?";
        return jdbcTemplate.query(sql, quizQuestionRowMapper, id);
    }

    public void saveResult(String username, int score, int total) {
        String sql = "insert into quiz_result(username, score, total) values (?, ?, ?)";
        jdbcTemplate.update(sql, username, score, total);
    }
}
