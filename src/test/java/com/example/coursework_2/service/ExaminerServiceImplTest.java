package com.example.coursework_2.service;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.NotEnoughQuestionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestionsPositiveTest() {
        Question expected1 = new Question("Yes?", "Maybe");
        Question expected2 = new Question("Yes?", "No");
        Question expected3 = new Question("Yes?", "Yes");

        when(questionServiceMock.getAll())
                .thenReturn(List.of(
                        new Question("Yes?", "Yes"),
                        new Question("Yes?", "No"),
                        new Question("Yes?", "Maybe")));
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(expected1);

        assertThat(out.getQuestions(1))
                .isNotEmpty()
                .hasSize(1)
                .isEqualTo(Set.of(expected1));

        when(questionServiceMock.getRandomQuestion())
                .thenReturn(expected1)
                .thenReturn(expected2)
                .thenReturn(expected3);

        assertThat(out.getQuestions(3))
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(Set.of(expected1, expected2, expected3));
    }

    @Test
    void getQuestionsNegativeTest() {
        when(questionServiceMock.getAll())
                .thenReturn(Collections.emptyList());

        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> out.getQuestions(1));
    }
}