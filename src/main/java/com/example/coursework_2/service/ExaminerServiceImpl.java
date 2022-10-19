package com.example.coursework_2.service;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.NotEnoughQuestionsException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;

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
        if(amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new NotEnoughQuestionsException();
        }

        Set<Question> questionSet = new HashSet<>();
        Random random = new Random();

        while(questionSet.size() < amount){
            if(random.nextInt(2) == 0){
                questionSet.add(javaQuestionService.getRandomQuestion());
            }else{
                questionSet.add(mathQuestionService.getRandomQuestion());
            }

            /*
            if(random.nextInt(2) == 0){
                questionSet.add(services.get(0).getRandomQuestion());
            }else{
                questionSet.add(services.get(1).getRandomQuestion());
            }
            */

        }
        return questionSet;
    }
}
