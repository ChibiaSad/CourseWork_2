package com.example.coursework_2.service;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.NotEnoughQuestionsException;
import com.example.coursework_2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService{
    private final QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
//        throw new MethodNotAllowedException("нельзя", null);
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
//        throw new MethodNotAllowedException("нельзя", null);
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
//        throw new MethodNotAllowedException("нельзя", null);
        return Set.copyOf(questionRepository.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(questionRepository.getAll());

        if(questionRepository.getAll().size() == 0) throw new NotEnoughQuestionsException();

        Random random = new Random();
        return questionList.get(random.nextInt(questionList.size()));

        /*
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        Question question = null;
        switch (random.nextInt(4)){
            case 0: {
                question = new Question(a + " + " + b, String.valueOf(a + b));
                break;
            }
            case 1: {
                question = new Question(a + " - " + b, String.valueOf(a - b));
                break;
            }
            case 2: {
                question = new Question(a + " * " + b, String.valueOf(a * b));
                break;
            }
            case 3: {
                question = new Question(a + " / " + b, String.valueOf(a / b));
            }
        }
        return question;
        */
    }
}
