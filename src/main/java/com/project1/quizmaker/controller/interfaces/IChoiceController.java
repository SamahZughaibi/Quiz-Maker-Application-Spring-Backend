package com.project1.quizmaker.controller.interfaces;

import com.project1.quizmaker.model.Choice;

import java.util.List;

public interface IChoiceController {
    List<Choice> getAllChoices();
    void deleteChoice(Integer choiceId);
}
