package com.project1.quizmaker.controller.impl;

import com.project1.quizmaker.controller.dto.QuestionTextDTO;
import com.project1.quizmaker.controller.interfaces.IQuestionController;
import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.model.Question;
import com.project1.quizmaker.repository.QuestionRepository;
import com.project1.quizmaker.service.interfaces.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController implements IQuestionController {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    IQuestionService questionService;

    //------------------------------ GET ----------------------------
    @GetMapping("/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/questions/{questionId}/choices")
    @ResponseStatus(HttpStatus.OK)
    public List<Choice> getAllChoicesByQuestionId(@PathVariable Integer questionId){
        return questionService.getAllChoicesByQuestionId(questionId);
    }
    //------------------------------ POST --------------------------------
    @PostMapping("/questions/choices")
    @ResponseStatus(HttpStatus.CREATED)
    public void addChoiceToQuestion(@RequestBody Choice choice){
        questionService.addChoiceToQuestion(choice);
    }
    //------------------------------PATCH--------------------------------
    @PatchMapping("/questions/text/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateQuestionText(@RequestBody QuestionTextDTO questionTextDTO, @PathVariable Integer questionId){
        questionService.updateQuestionText(questionTextDTO.getQuestionText(), questionId);
    }

    // -----------------------------DELETE-------------------------------
    @DeleteMapping("/questions/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable Integer questionId) {
        questionService.deleteQuestion(questionId);
    }
}
