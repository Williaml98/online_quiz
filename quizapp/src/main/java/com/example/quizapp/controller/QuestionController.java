package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
/*
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
      // return new ResponseEntity<>(questionService.addQuestion(question).getStatusCode());
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteQuestion(@RequestBody Question question) {
        try {
            return questionService.deleteQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting question");
        }
    }

    @DeleteMapping("deleteById")
    public ResponseEntity<String> deleteQuestionBYId(@RequestBody Question question) {
        try {
            return questionService.deleteQuestionById(question);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting question by ID");
        }
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
        try {
            return questionService.updateQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating question");
        }
    }*/

    @GetMapping("/add")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "add-question";
    }

    @PostMapping("/add")
    public String saveQuestion(@ModelAttribute("question") Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "add-question";
        }

        questionService.addQuestion(question);

        return "redirect:/question/allQuestions";

    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    /*@GetMapping("/all")
    public String getAllQuestions(Model model) {
        model.addAttribute("questions", questionService.getAllQuestions());
        return "all-questions";

    }*/




}
