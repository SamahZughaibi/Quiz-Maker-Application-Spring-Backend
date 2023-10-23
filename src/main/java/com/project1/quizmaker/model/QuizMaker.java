package com.project1.quizmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@Entity
@PrimaryKeyJoinColumn(name = "email")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class QuizMaker extends User {
    private String password;
}
