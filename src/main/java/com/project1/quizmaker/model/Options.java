package com.project1.quizmaker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionId;
    private String optionText;
    @ManyToOne
    @JoinColumn(name = "forQuestion", nullable = false)
    private Question homeQuestion;

    public Options(String optionText, Question homeQuestion) {
        this.optionText = optionText;
        this.homeQuestion = homeQuestion;
    }
}
