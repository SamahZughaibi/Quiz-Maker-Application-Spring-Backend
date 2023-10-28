package com.project1.quizmaker.controller.impl;

import com.project1.quizmaker.controller.interfaces.IQuizResultController;
import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.QuizResult;
import com.project1.quizmaker.repository.QuizResultRepository;
import com.project1.quizmaker.service.interfaces.IQuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizResultController implements IQuizResultController {
    @Autowired
    QuizResultRepository quizResultRepository;
    @Autowired
    IQuizResultService quizResultService;
    @GetMapping("/results")
    @ResponseStatus(HttpStatus.OK)
    public List<QuizResult> getAllQuizResults() {
        return quizResultRepository.findAll();
    }
    @PostMapping("/results")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuizForUser(@RequestBody QuizResult quizResult){
        quizResultService.addResult(quizResult);
    }

    @DeleteMapping("/results/{resultId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuizResult(@PathVariable Integer resultId) {
        quizResultService.deleteQuizResult(resultId);
    }
}
