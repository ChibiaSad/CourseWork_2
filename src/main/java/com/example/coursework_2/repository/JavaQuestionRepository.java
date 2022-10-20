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
public class JavaQuestionRepository implements QuestionRepository{
    private final Set<Question> questionSet;

    public JavaQuestionRepository() {
        questionSet = new HashSet<>();
    }

    @PostConstruct
    public void init(){
        questionSet.addAll(Set.of(
                new Question("Сет коллекция?", "Да"),
                new Question("Лист коллекция?", "Да"),
                new Question("Дэк коллекция?", "Да"),
                new Question("Мап коллекция?", "Нет"),
                new Question("Быстрая сортировка быстрая?", "Да"),
                new Question("Коллекция это коллекция?", "Да")));
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
