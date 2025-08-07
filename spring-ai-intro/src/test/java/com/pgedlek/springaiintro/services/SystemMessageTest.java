package com.pgedlek.springaiintro.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
@SpringBootTest
public class SystemMessageTest {
    @Autowired
    ChatModel chatModel;

    @Test
    @Disabled
    void asCityGuideTest() {
        String systemPrompt = """
                You are a helpful AI assistant. Your role is a city tourism guide.
                You answer questions about cities in descriptive and welcoming paragraphs.
                You hope the user will visit and enjoy the city.
                """;
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);

        Message systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate promptTemplate = new PromptTemplate("Tell me about Krakow.");
        Message userMessage = promptTemplate.createMessage();

        List<Message> messages = List.of(systemMessage, userMessage);

        ChatOptions options = OpenAiChatOptions.builder()
                .temperature(0.1)
                .model(OpenAiApi.ChatModel.GPT_4_1)
                .build();

        Prompt prompt = new Prompt(messages, options);

        System.out.println(chatModel.call(prompt).getResult().getOutput().getText());
    }

    @Test
    @Disabled
    void asHemingwayTest() {
        String systemPrompt = """
                You are a helpful AI assistant. You are also Ernest Hemingway's biggest fan. You answer questions \s
                using the tone, style, and themes of Ernest Hemingway. You have a particular fondness for city of Key West
                """;
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);

        Message systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate promptTemplate = new PromptTemplate("Tell me about Key West.");
        Message userMessage = promptTemplate.createMessage();

        List<Message> messages = List.of(systemMessage, userMessage);

        ChatOptions options = OpenAiChatOptions.builder()
                .temperature(0.7)
                .model(OpenAiApi.ChatModel.GPT_4_1)
                .build();

        Prompt prompt = new Prompt(messages, options);

        System.out.println(chatModel.call(prompt).getResult().getOutput().getText());
    }

    @Test
    void asHarryPotterTest() {
        String cookASteak = """
                Cooking the perfect steak is easy.
                First, remove the steak from the refrigerator and packaging. Let sit at
                room temperature for at least 30 mins.
                rub the steak with a light coating of olive oil, and season the steak with salt and pepper.
                Next, heat a pan over high heat.
                Then, add the steak to the pan and sear for 3 minutes on each side.
                Finally, let the steak rest for 5 minutes before slicing.
                Enjoy!""";

        String systemPrompt = """
                You are a creative writer heavily inspired by JK Rowling and her Harry Potter series of books.
                Respond by using the tone, tools and imagination of JK Rowling.
                """;
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);

        Message systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate promptTemplate = new PromptTemplate(cookASteak);
        Message userMessage = promptTemplate.createMessage();

        List<Message> messages = List.of(systemMessage, userMessage);

        Prompt prompt = new Prompt(messages);

        System.out.println(chatModel.call(prompt).getResult().getOutput().getText());
    }
}
