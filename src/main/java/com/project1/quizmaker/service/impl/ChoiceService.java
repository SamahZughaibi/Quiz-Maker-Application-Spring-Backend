package com.project1.quizmaker.service.impl;

import com.project1.quizmaker.model.Choice;
import com.project1.quizmaker.repository.ChoiceRepository;
import com.project1.quizmaker.service.interfaces.IChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ChoiceService implements IChoiceService {
    @Autowired
    ChoiceRepository choiceRepository;
    @Override
    public void deleteChoice(Integer choiceId) {
        Optional<Choice> choiceOptional = choiceRepository.findById(choiceId);
        if(choiceOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Choice: " + choiceId + " not found");
        choiceRepository.deleteById(choiceId);
    }
}
