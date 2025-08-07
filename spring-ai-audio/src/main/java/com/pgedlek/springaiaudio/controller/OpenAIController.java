package com.pgedlek.springaiaudio.controller;

import com.pgedlek.springaiaudio.model.Question;
import com.pgedlek.springaiaudio.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIService openAIService;

    @PostMapping(value = "/talk", produces = "audio/mpeg")
    public byte[] talk(@RequestBody Question question) {
        return openAIService.getSpeech(question);
    }

}
