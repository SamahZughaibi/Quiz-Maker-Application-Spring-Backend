package com.project1.quizmaker.controller.impl;

import com.project1.quizmaker.controller.interfaces.IChoiceController;
import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.repository.ChoiceRepository;
import com.project1.quizmaker.service.interfaces.IChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChoiceController implements IChoiceController {
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    IChoiceService choiceService;
    @GetMapping("/choices")
    @ResponseStatus(HttpStatus.OK)
    public List<Choice> getAllChoices() {
        return choiceRepository.findAll();
    }
    @DeleteMapping("/choices/{choiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChoice(@PathVariable Integer choiceId) {
        choiceService.deleteChoice(choiceId);
    }
}
