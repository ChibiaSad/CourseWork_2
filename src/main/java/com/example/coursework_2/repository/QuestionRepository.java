package com.example.coursework_2.repository;

import com.example.coursework_2.entity.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
