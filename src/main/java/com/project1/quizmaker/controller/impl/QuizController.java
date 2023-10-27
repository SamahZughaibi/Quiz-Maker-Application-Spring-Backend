package com.project1.quizmaker.controller.impl;

import com.project1.quizmaker.controller.dto.QuizTitleDTO;
import com.project1.quizmaker.controller.interfaces.IQuizController;
import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.model.Quiz;
import com.project1.quizmaker.model.User;
import com.project1.quizmaker.repository.QuizRepository;
import com.project1.quizmaker.service.impl.QuizService;
import com.project1.quizmaker.service.interfaces.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController implements IQuizController {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    IQuizService quizService;

    //    ------------------------------ GET ----------------------------
    @GetMapping("/quizzes")
    @ResponseStatus(HttpStatus.OK)
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @GetMapping("/quizzes/{quizId}/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<Question> getAllQuestionsByQuizId(@PathVariable Integer quizId){
        return quizService.getAllQuestionsByQuizId(quizId);
    }
    //------------------------------ POST --------------------------------
    @PostMapping("/quizzes/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestionToQuiz(@RequestBody Question question){
        quizService.addQuestionToQuiz(question);
    }
    //------------------------------PATCH--------------------------------
    @PatchMapping("/quizzes/title/{quizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateQuizTitle(@RequestBody QuizTitleDTO quizTitleDTO, @PathVariable Integer quizId){
        quizService.updateQuizTitle(quizTitleDTO.getTitle(), quizId);
    }

    // -----------------------------DELETE-------------------------------
    @DeleteMapping("/quizzes/{quizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable Integer quizId) {
        quizService.deleteQuiz(quizId);
    }

}
