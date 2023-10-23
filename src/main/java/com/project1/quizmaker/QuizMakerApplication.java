package com.project1.quizmaker;

import com.project1.quizmaker.Components.QuizMakerSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizMakerApplication implements CommandLineRunner {

	@Autowired
	QuizMakerSystem quizMakerSystem;

	public static void main(String[] args) {
		SpringApplication.run(QuizMakerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		quizMakerSystem.printSomething();
	}
}
