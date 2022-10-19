package com.example.coursework_2.service;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.NotEnoughQuestionsException;
import com.example.coursework_2.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepository javaQuestionRepository;
    @InjectMocks
    private JavaQuestionService out;

    @Test
    void addByStringsTest() {
        Question expected = new Question("da", "da");
        when(javaQuestionRepository.add(expected))
                .thenReturn(expected);

        assertThat(out.add("da", "da"))
                .isEqualTo(expected);
    }

    @Test
    void AddByObjectTest() {
        Question expected = new Question("da", "da");
        when(javaQuestionRepository.add(expected))
                .thenReturn(expected);

        assertThat(out.add(expected))
                .isEqualTo(expected);
    }

    @Test
    void removeTest() {
        Question expected = new Question("Yes?", "Yes");
        when(javaQuestionRepository.remove(expected))
                .thenReturn(expected);

        assertThat(out.remove(expected))
                .isEqualTo(expected);
    }

    @Test
    void getRandomQuestionPositiveTest() {
        when(javaQuestionRepository.getAll()).thenReturn(Set.of(
                new Question("Yes?", "Yes"),
                new Question("Yes?", "No"),
                new Question("Yes?", "Maybe")));

        assertThat(out.getRandomQuestion())
                .isNotNull();
        assertThat(out.getAll())
                .isNotEmpty()
                .hasSize(3)
                .contains(out.getRandomQuestion());
    }

    @Test
    void getRandomQuestionNegativeTest() {
        when(javaQuestionRepository.getAll()).thenReturn(Collections.emptySet());

        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> out.getRandomQuestion());
    }
}