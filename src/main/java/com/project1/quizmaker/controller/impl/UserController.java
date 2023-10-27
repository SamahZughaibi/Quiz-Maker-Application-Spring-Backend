package com.project1.quizmaker.controller.impl;

import com.project1.quizmaker.controller.dto.UserFullNameDTO;
import com.project1.quizmaker.controller.interfaces.IUserController;
import com.project1.quizmaker.model.*;
import com.project1.quizmaker.repository.QuizRepository;
import com.project1.quizmaker.repository.UserRepository;
import com.project1.quizmaker.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController implements IUserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IUserService userService;

//    ------------------------------ GET ----------------------------
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String email) {
        return userService.getUserById(email);
    }

    @GetMapping("/user/{email}/quizzes")
    @ResponseStatus(HttpStatus.OK)
    public List<Quiz> getAllUserQuizzes(@PathVariable String email){
        return userService.getAllUserQuizzes(email);
    }//  /api/user/samah@gmail.com/quizzes

    @GetMapping("/user/{email}/results")
    @ResponseStatus(HttpStatus.OK)
    //for the user to view results of the quizzes they've taken
    public List<QuizResult> getAllResultsForUser(@PathVariable String email){
        return userService.getAllResultsForUser(email);
    }

    @GetMapping("/user/{email}/resultsOfQuiz")
    @ResponseStatus(HttpStatus.OK)
    //for the user to view results of a quiz they've made
    public List<QuizResult> getAllResultsForOwnedQuiz(@PathVariable String email, @RequestParam Integer quizId){
        return userService.getAllResultsForOwnedQuiz(email,quizId);
    }

    @GetMapping("/user/{email}/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<Question> getAllUserQuestions(@PathVariable String email){
        return userService.getAllUserQuestions(email);
    }

    @GetMapping("/user/{email}/choices")
    @ResponseStatus(HttpStatus.OK)
    public List<Choice> getAllUserChoices(@PathVariable String email){
        return userService.getAllUserChoices(email);
    }

//  ---------------------------- POST ----------------------------

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }
    @PostMapping("/users/quizzes")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuizForUser(@RequestBody Quiz quiz){
        userService.addQuizForUser(quiz);
    }

//  ---------------------------- PATCH ----------------------------

    @PatchMapping("/user/fullName/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFullName(@RequestBody UserFullNameDTO userFullNameDTO, @PathVariable String email) {
        userService.updateFullName(userFullNameDTO.getFullName(), email);
    }
}
