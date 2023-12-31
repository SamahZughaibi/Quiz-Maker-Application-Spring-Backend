package com.project1.quizmaker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    private String questionText;
    private Integer pointsAssigned;

    @ManyToOne
    @JoinColumn(name = "for_quiz", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Quiz homeQuiz;

    public Question(String questionText, Integer pointsAssigned, Quiz homeQuiz) {
        this.questionText = questionText;
        this.pointsAssigned = pointsAssigned;
        this.homeQuiz = homeQuiz;
    }
}
