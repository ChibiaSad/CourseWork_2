package com.example.coursework_2.service;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.NotEnoughQuestionsException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService mathQuestionService, QuestionService javaQuestionService) {
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
    }

    /*
    private final List<QuestionService> services = new ArrayList<>();   //что??????????

    public ExaminerServiceImpl(QuestionService mathQuestionService, QuestionService javaQuestionService) {
        services.add(javaQuestionService);
        services.add(mathQuestionService);
    }
    */


    @Override
    public Collection<Question> getQuestions(int amount) {
        //в последней сложности это условие не нужно, так как вопросы math бесконечны
        if(amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new NotEnoughQuestionsException();
        }

        Set<Question> questionSet = new HashSet<>();

        while(questionSet.size() < amount){
            if(random.nextBoolean()){
                questionSet.add(javaQuestionService.getRandomQuestion());
            }else{
                questionSet.add(mathQuestionService.getRandomQuestion());
            }

            /*
            questionSet.add(services.get(random.nextInt(2)).getRandomQuestion());
            */
        }
        return questionSet;
    }
}
