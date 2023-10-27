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

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    private Integer quizScore;
    public Quiz(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }
}
