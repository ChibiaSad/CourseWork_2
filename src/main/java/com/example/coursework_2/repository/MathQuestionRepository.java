package com.example.coursework_2.repository;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.QuestionAlreadyExist;
import com.example.coursework_2.exception.QuestionIsNotExist;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository{
    private final Set<Question> questionSet;

    public MathQuestionRepository() {
        questionSet = new HashSet<>();
    }

    @PostConstruct
    public void init(){
        questionSet.addAll(Set.of(
                new Question("2 + 2", "4"),
                new Question("37 * 12", "444"),
                new Question("√8000", "20√20"),
                new Question("2 * 2", "4"),
                new Question("3 + 3", "6"),
                new Question("4 / 4", "1")));
    }

    @Override
    public Question add(Question question) {
        if(questionSet.contains(question)) {
            throw new QuestionAlreadyExist();
        }

        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(!questionSet.contains(question)) {
            throw new QuestionIsNotExist();
        }

        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questionSet);
    }
}
