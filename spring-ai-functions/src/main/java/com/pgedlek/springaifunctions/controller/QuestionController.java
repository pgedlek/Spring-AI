package com.pgedlek.springaifunctions.controller;

import com.pgedlek.springaifunctions.model.Answer;
import com.pgedlek.springaifunctions.model.Question;
import com.pgedlek.springaifunctions.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/currentWeather")
    public Answer currentWeather(@RequestBody Question question) {
        return openAIService.getWeather(question);
    }

    @PostMapping("/currentStockPrice")
    public Answer currentStockPrice(@RequestBody Question question) {
        return openAIService.getStockPrice(question);
    }

}
