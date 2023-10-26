package com.project1.quizmaker.model;

import com.project1.quizmaker.repository.QuestionRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

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
    private User owner;
    private Integer quizScore;
    public Quiz(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }
}
