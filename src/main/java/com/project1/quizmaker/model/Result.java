package com.project1.quizmaker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;
    @ManyToOne
    @JoinColumn(name = "quiz_taker", nullable = false)
    private User quizTaker;
    @ManyToOne
    @JoinColumn(name = "quiz_taken", nullable = false)
    private Quiz quizTaken;
    private Date date;
    private Integer totalScore;

    public Result(User quizTaker, Quiz quizTaken, Date date, Integer totalScore) {
        this.quizTaker = quizTaker;
        this.quizTaken = quizTaken;
        this.date = date;
        this.totalScore = totalScore;
    }
}
