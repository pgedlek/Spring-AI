package com.pgedlek.springaiaudio.service;

import com.pgedlek.springaiaudio.model.Question;

public interface OpenAIService {
    byte[] getSpeech(Question question);
}
