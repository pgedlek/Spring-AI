package com.pgedlek.springaiintro.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgedlek.springaiintro.model.Answer;
import com.pgedlek.springaiintro.model.CapitalRequest;
import com.pgedlek.springaiintro.model.CapitalResponse;
import com.pgedlek.springaiintro.model.CapitalWithInfoResponse;
import com.pgedlek.springaiintro.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class OpenAIServiceImpl implements OpenAIService {
    private final ChatModel chatModel;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource capitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource capitalPromptWithInfo;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public CapitalResponse getCapital(CapitalRequest capitalRequest) {
        BeanOutputConverter<CapitalResponse> converter = new BeanOutputConverter<>(CapitalResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(capitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                "stateOrCountry", capitalRequest.stateOrCountry(),
                "format", format));

        ChatResponse response = chatModel.call(prompt);

        return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
    }

    @Override
    public CapitalWithInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest) {
        BeanOutputConverter<CapitalWithInfoResponse> converter = new BeanOutputConverter<>(CapitalWithInfoResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(capitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of(
                "stateOrCountry", capitalRequest.stateOrCountry(),
                "format", format));

        ChatResponse response = chatModel.call(prompt);

        return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
    }
}
