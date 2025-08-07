package com.pgedlek.springaifunctions.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgedlek.springaifunctions.functions.StockServiceFunction;
import com.pgedlek.springaifunctions.functions.WeatherServiceFunction;
import com.pgedlek.springaifunctions.model.Answer;
import com.pgedlek.springaifunctions.model.Question;
import com.pgedlek.springaifunctions.model.StockRequest;
import com.pgedlek.springaifunctions.model.StockResponse;
import com.pgedlek.springaifunctions.model.WeatherRequest;
import com.pgedlek.springaifunctions.model.WeatherResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAIServiceImpl implements OpenAIService {
    private final OpenAiChatModel chatModel;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${sfg.aiapp.apiNinjasKey}")
    private String apiNinjasKey;

    public OpenAIServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getWeather(Question question) {
        OpenAiChatOptions currentWeather = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(FunctionCallback.builder()
                        .function("CurrentWeather", new WeatherServiceFunction(apiNinjasKey))
                        .inputType(WeatherRequest.class)
                        .description("Get the current weather for a location")
                        .responseConverter(response -> {
                            String schema = ModelOptionsUtils.getJsonSchema(WeatherResponse.class, false);
                            String jsonString = ModelOptionsUtils.toJsonString(response);
                            return schema + "\n" + jsonString;
                        })
                        .build()))
                .build();

        Message message = new PromptTemplate(question.question()).createMessage();

        //Message systemMessage = new SystemPromptTemplate("You are a weather service. You receive weather information from a service which gives you the information based on the metrics system." +
        // " When answering the weather in an imperial system country, you should convert the temperature to Fahrenheit and the wind speed to miles per hour. ").createMessage();
        SystemMessage systemMessage = new SystemMessage("Sunset and sunrise time are represent as timestamp. Try to convert them to human readable time.");

        ChatResponse response = chatModel.call(new Prompt(List.of(message), currentWeather));

        return new Answer(response.getResult().getOutput().getText());
    }

    @Override
    public Answer getStockPrice(Question question) {
        OpenAiChatOptions currentStockPrice = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(FunctionCallback.builder()
                        .function("CurrentStockPrice", new StockServiceFunction(apiNinjasKey))
                        .description("Get the current stock price for a ticker")
                        .inputType(StockRequest.class)
                        .responseConverter(response -> {
                            String schema = ModelOptionsUtils.getJsonSchema(StockResponse.class, false);
                            String jsonString = ModelOptionsUtils.toJsonString(response);
                            return schema + "\n" + jsonString;
                        })
                        .build()))
                .build();

        Message message = new PromptTemplate(question.question()).createMessage();
        SystemMessage systemMessage = new SystemMessage("In response include stock ticker, name, current price, current and exchange name.");
        ChatResponse response = chatModel.call(new Prompt(List.of(message, systemMessage), currentStockPrice));

        return new Answer(response.getResult().getOutput().getText());
    }
}
