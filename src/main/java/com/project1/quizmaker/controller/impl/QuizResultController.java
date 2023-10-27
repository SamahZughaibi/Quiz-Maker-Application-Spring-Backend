package com.project1.quizmaker.controller.impl;

import com.project1.quizmaker.controller.interfaces.IQuizController;
import com.project1.quizmaker.controller.interfaces.IQuizResultController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuizResultController implements IQuizResultController {
}
