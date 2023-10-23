package com.project1.quizmaker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizId;
    private String title;
//    private List<String> grades; // to be checked later if I wanted to allow the user to customize grades or not
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private QuizMaker owner;

    public Quiz(String title, QuizMaker owner) {
        this.title = title;
        this.owner = owner;
    }
}
