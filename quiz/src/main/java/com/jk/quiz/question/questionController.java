package com.jk.quiz.question;

import java.security.SecureRandom;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class questionController {

    // Geography questions
    String question1 = "1. What is the capital of Finland?";
    String answer1 = "1.Helsinki";
    String question2 = "2. What is the capital of Sweden?";
    String answer2 = "2.Stockholm";
    String question3 = "3. What is the capital of Norway?";
    String answer3 = "3.Oslo";
    String question4 = "4. What is the capital of Denmark?";
    String answer4 = "4.Copenhagen";
    String question5 = "5. What is the capital of Iceland?";
    String answer5 = "5.Reykjavik";

    // Math questions
    String question6 = "6. What is 54 + 23?";
    String answer6 = "6.77";
    String question7 = "7. What is 12 * 8?";
    String answer7 = "7.96";
    String question8 = "8. What is 100 / 2?";
    String answer8 = "8.50";
    String question9 = "9. What is 34 - 13?";
    String answer9 = "9.21";
    String question10 = "10. What is 2^4?";
    String answer10 = "10.16";

    // History questions
    String question11 = "11. What year did World War 2 end?";
    String answer11 = "11.1945";
    String question12 = "12. What year did World War 1 end?";
    String answer12 = "12.1918";
    String question13 = "13. What year did Finland become independent?";
    String answer13 = "13.1917";
    String question14 = "14. What year did the United States declare independence?";
    String answer14 = "14.1776";
    String question15 = "15. What year did the Soviet Union collapse?";
    String answer15 = "25.1991";

    // Science questions
    String question16 = "16. What is the chemical symbol for gold?";
    String answer16 = "16.Au";
    String question17 = "17. What is the chemical symbol for silver?";
    String answer17 = "17.Ag";
    String question18 = "18. What is the chemical symbol for iron?";
    String answer18 = "18.Fe";
    String question19 = "19. What is the chemical symbol for copper?";
    String answer19 = "19.Cu";
    String question20 = "20. What is the chemical symbol for calcium?";
    String answer20 = "20.Ca";

    String[] questions = {
            question1, question2, question3, question4, question5, question6, question7, question8,
            question9, question10, question11, question12, question13, question14, question15, question16, question17,
            question18, question19, question20
    };

    String[] answers = {
            answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10,
            answer11, answer12, answer13, answer14, answer15, answer16, answer17, answer18, answer19, answer20
    };

    // How to play the game
    @GetMapping("/howToPlay")
    public String howToPlay() {
        return "- This quiz game is played using Postman. The collection of Postman requests are provided in the GitHub repository\n."
                +
                "- To start, you need to get a set of 5 random questions by using the /questions endpoint\n"
                +
                "- You can then answer the questions by using the /answer endpoint. You need to send the answers all at once\n"
                +
                "- The aswers need to include the question number before the answer. For example: If the question was '21. What is the firstname of the President of Finland?', then the answer should be written like '21.Sauli'\n"
                +
                "- The answer are case sensitive";
    }

    // Get all the possible questions
    @GetMapping("/allQuestions")
    public String[] getAllQuestions() {
        return questions;
    }

    // Generate a set of 5 random questions for the game
    public String[] generateQuestionSet() {
        SecureRandom random = new SecureRandom();
        String[] questionSet = new String[5];
        for (int i = 0; i < 5; i++) {
            int randomQuestion = random.nextInt(questions.length);
            questionSet[i] = questions[randomQuestion];
        }
        return questionSet;
    }

    // Get a set of 5 random questions
    @GetMapping("/questions")
    public String[] getQuestions() {
        return generateQuestionSet();
    }

    // Get the answers to all the questions
    @GetMapping("/answers")
    public String[] getAnswers() {
        return answers;
    }

    // Aswer the 5 random questions and get a score
    @PostMapping("/answer")
    public String answerQuestions(@RequestParam String a1, @RequestParam String a2, @RequestParam String a3,
            @RequestParam String a4, @RequestParam String a5) {
        String[] quizAnswers = { a1, a2, a3, a4, a5 };
        int correctAnswers = 0;
        // Go through the answers one by one and check if they are in the list of correct answers
        for (int i = 0; i < quizAnswers.length; i++) {
            if (Arrays.asList(answers).contains(quizAnswers[i])) {
                correctAnswers++;
            }
        }
        return "You got " + correctAnswers + " out of 5 correct!";
    }
}
