package com.pgedlek.springaiintro.services;

import com.pgedlek.springaiintro.model.Answer;
import com.pgedlek.springaiintro.model.CapitalRequest;
import com.pgedlek.springaiintro.model.CapitalResponse;
import com.pgedlek.springaiintro.model.CapitalWithInfoResponse;
import com.pgedlek.springaiintro.model.Question;

public interface OpenAIService {
    Answer getAnswer(Question question);

    CapitalResponse getCapital(CapitalRequest capitalRequest);

    CapitalWithInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest);
}
