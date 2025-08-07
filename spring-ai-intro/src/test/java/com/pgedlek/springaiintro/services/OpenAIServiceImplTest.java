package com.pgedlek.springaiintro.services;

import com.pgedlek.springaiintro.model.Answer;
import com.pgedlek.springaiintro.model.Question;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {
    @Autowired
    OpenAIServiceImpl openAIService;

    @Test
    @Disabled
    void getAnswer() {
        Answer answer = openAIService.getAnswer(new Question("What is a meaning of life?"));

        System.out.println(answer.answer());
    }

    @Test
    @Disabled
    void testGetJson() {
        String prompt = """
                Generate a list of 4 made up cars. Provide them in a JSON format
                with the following attributes: make, model, year and color. Return the JSON string.
                """;
        Answer answer = openAIService.getAnswer(new Question(prompt));

        System.out.println(answer.answer());
    }

    @Test
    @Disabled
    void testGetXml() {
        String prompt = """
                Generate a list of 4 made up cars. Provide them in a XML format
                with the following attributes: make, model, year and color. Return the XML string.
                """;
        Answer answer = openAIService.getAnswer(new Question(prompt));

        System.out.println(answer.answer());
    }

    @Test
    @Disabled
    void testGetYaml() {
        String prompt = """
                Generate a list of 4 made up cars. Provide them in a YAML format
                with the following attributes: make, model, year and color. Return the YAML string.
                """;
        Answer answer = openAIService.getAnswer(new Question(prompt));

        System.out.println(answer.answer());
    }
}