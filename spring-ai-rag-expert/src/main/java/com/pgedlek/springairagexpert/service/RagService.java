package com.pgedlek.springairagexpert.service;

import com.pgedlek.springairagexpert.model.Answer;
import com.pgedlek.springairagexpert.model.Question;

public interface RagService {
    Answer getAnswer(Question question);
}
