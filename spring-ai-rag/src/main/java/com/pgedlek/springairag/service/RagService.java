package com.pgedlek.springairag.service;

import com.pgedlek.springairag.model.Answer;
import com.pgedlek.springairag.model.Question;

public interface RagService {
    Answer getAnswer(Question question);
}
