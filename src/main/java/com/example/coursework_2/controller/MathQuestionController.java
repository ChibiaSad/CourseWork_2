package com.example.coursework_2.controller;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/math")
public class MathQuestionController {
    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/add")
    public Question addMathQuestion(@RequestParam String question,
                                    @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping(value = "/remove")
    public Question removeMathQuestion(@RequestParam String question,
                                       @RequestParam String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping()
    public Collection<Question> getAllMathQuestions() {
        return questionService.getAll();
    }
}
