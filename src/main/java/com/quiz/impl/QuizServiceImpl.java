package com.quiz.impl;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
@Autowired
    private QuizRepository quizRepository;
@Autowired
private QuestionClient questionClient;
    @Override
    public Quiz add(Quiz quiz) {
        return
quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
       List<Quiz>quizzes= quizRepository.findAll();
      List<Quiz>newQuizList= quizzes.stream().map(quiz->{
           quiz.setQuestions(questionClient.getQuestionofQuiz(quiz.getId()));
           return quiz;
       }).collect(Collectors.toList());
      return newQuizList;
    }

    @Override
    public Quiz get(Long id) {

        Quiz quiz= quizRepository.findById(id).orElseThrow(RuntimeException::new);
quiz.setQuestions(questionClient.getQuestionofQuiz(quiz.getId()));
return quiz;

    }
}
