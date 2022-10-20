package com.example.coursework_2.repository;

import com.example.coursework_2.entity.Question;
import com.example.coursework_2.exception.QuestionAlreadyExist;
import com.example.coursework_2.exception.QuestionIsNotExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class JavaQuestionRepositoryTest {
    private JavaQuestionRepository out;

    @BeforeEach
    void setUp() {
        out = new JavaQuestionRepository();
        out.init();
    }

    @Test
    void initTest() {
        assertThat(out.getAll())
                .hasSize(6)
                .isEqualTo(Set.of(
                        new Question("Сет коллекция?", "Да"),
                        new Question("Лист коллекция?", "Да"),
                        new Question("Дэк коллекция?", "Да"),
                        new Question("Мап коллекция?", "Нет"),
                        new Question("Быстрая сортировка быстрая?", "Да"),
                        new Question("Коллекция это коллекция?", "Да")));
    }

    @Test
    void addPositiveTest() {
        out.add(new Question("Yes?", "Yes"));
        out.add(new Question("Yes?", "No"));
        out.add(new Question("Yes?", "Maybe"));

        assertThat(out.getAll())
                .isNotEmpty()
                .hasSize(9)
                .isEqualTo(Set.of(
                        new Question("Сет коллекция?", "Да"),
                        new Question("Лист коллекция?", "Да"),
                        new Question("Дэк коллекция?", "Да"),
                        new Question("Мап коллекция?", "Нет"),
                        new Question("Быстрая сортировка быстрая?", "Да"),
                        new Question("Коллекция это коллекция?", "Да"),
                        new Question("Yes?", "Yes"),
                        new Question("Yes?", "No"),
                        new Question("Yes?", "Maybe")));

        assertThat(out.add(new Question("No?", "No")))
                .isNotNull()
                .isEqualTo(new Question("No?", "No"));
    }

    @Test
    void addNegativeTest() {
        out.add(new Question("Yes?", "Yes"));
        assertThatExceptionOfType(QuestionAlreadyExist.class)
                .isThrownBy(() -> out.add(new Question("Yes?", "Yes")));
    }

    @Test
    void removePositiveTest() {
        Question expected1 = new Question("Быстрая сортировка быстрая?", "Да");
        Question expected2 = new Question("Коллекция это коллекция?", "Да");

        out.remove(expected1);

        assertThat(out.getAll())
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(Set.of(
                        new Question("Сет коллекция?", "Да"),
                        new Question("Лист коллекция?", "Да"),
                        new Question("Дэк коллекция?", "Да"),
                        new Question("Мап коллекция?", "Нет"),
                        new Question("Коллекция это коллекция?", "Да")));

        assertThat(out.remove(expected2))
                .isNotNull()
                .isEqualTo(expected2);
    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(QuestionIsNotExist.class)
                .isThrownBy(() -> out.remove(new Question("Yes?", "Yes")));
    }
}