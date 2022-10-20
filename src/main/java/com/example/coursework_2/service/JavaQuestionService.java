package com.example.coursework_2.service;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.NotEnoughQuestionsException;
import com.example.coursework_2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{
    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questionRepository.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(getAll());

        if(questionList.size() == 0) {
            throw new NotEnoughQuestionsException();
        }

        return questionList.get(random.nextInt(questionList.size()));
    }
}
