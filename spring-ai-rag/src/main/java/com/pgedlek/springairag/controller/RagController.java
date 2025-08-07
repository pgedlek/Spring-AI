package com.pgedlek.springairag.controller;

import com.pgedlek.springairag.model.Answer;
import com.pgedlek.springairag.model.Question;
import com.pgedlek.springairag.service.RagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {
    @Autowired
    private RagService ragService;

    @PostMapping("/ask")
    public Answer getAnswer(@RequestBody Question question) {
        return ragService.getAnswer(question);
    }
}
