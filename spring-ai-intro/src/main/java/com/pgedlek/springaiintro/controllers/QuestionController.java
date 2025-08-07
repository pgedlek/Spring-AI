package com.pgedlek.springaiintro.controllers;

import com.pgedlek.springaiintro.model.Answer;
import com.pgedlek.springaiintro.model.CapitalRequest;
import com.pgedlek.springaiintro.model.CapitalResponse;
import com.pgedlek.springaiintro.model.CapitalWithInfoResponse;
import com.pgedlek.springaiintro.model.Question;
import com.pgedlek.springaiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer createAnswer(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public CapitalResponse capital(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapital(capitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public CapitalWithInfoResponse capitalWithInfo(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapitalWithInfo(capitalRequest);
    }
}
