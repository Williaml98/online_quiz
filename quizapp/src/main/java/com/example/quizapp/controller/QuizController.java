package com.example.quizapp.controller;

import com.example.quizapp.controller.service.QuizService;
import com.example.quizapp.dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "http://localhost:8082")
public class QuizController {
    @Autowired
    QuizService quizService;
    @Autowired
    QuizDao quizDao;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("create-quiz")
    public String showCreateQuizForm() {
        return "create-quiz";
    }

    /*@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }*/

    /*@GetMapping("get")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam(required = false) Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return quizService.getQuizQuestions(id);
    }*/

    @GetMapping("get")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam(required = false) Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body(List.of(new QuestionWrapper(0, "Invalid quiz ID", "", "", "", "")));
        }
        return quizService.getQuizQuestions(id);
    }


    @GetMapping("get-all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizDao.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);

    }

    @GetMapping("take-quiz")
    public String showTakeQuizPage() {
        return "take-quiz";
    }

}
