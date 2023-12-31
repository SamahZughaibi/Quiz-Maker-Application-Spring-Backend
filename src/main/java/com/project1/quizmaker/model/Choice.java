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
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer choiceId;
    private String choiceText;
    private boolean correctAnswer;
    @ManyToOne
    @JoinColumn(name = "forQuestion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question homeQuestion;

    public Choice(String choiceText, boolean correctAnswer, Question homeQuestion) {
        this.choiceText = choiceText;
        this.correctAnswer = correctAnswer;
        this.homeQuestion = homeQuestion;
    }
}
