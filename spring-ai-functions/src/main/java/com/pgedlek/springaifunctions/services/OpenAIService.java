package com.pgedlek.springaifunctions.services;

import com.pgedlek.springaifunctions.model.Answer;
import com.pgedlek.springaifunctions.model.Question;

public interface OpenAIService {
    Answer getWeather(Question question);

    Answer getStockPrice(Question question);
}
