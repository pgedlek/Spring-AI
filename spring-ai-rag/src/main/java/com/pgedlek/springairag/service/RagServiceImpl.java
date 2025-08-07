package com.pgedlek.springairag.service;

import com.pgedlek.springairag.model.Answer;
import com.pgedlek.springairag.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RagServiceImpl implements RagService {
    @Autowired
    private ChatModel client;
    @Autowired
    private SimpleVectorStore simpleVectorStore;

    @Value("classpath:/templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Override
    public Answer getAnswer(Question question) {
        List<Document> documents = simpleVectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question.question())
                        .topK(5)
                        .build());

        List<String> contentList = documents.stream().map(Document::getContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of(
                "input", question.question(),
                "documents", String.join("\n", contentList)));

        contentList.forEach(System.out::println);

        ChatResponse response = client.call(prompt);

        return new Answer(response.getResult().getOutput().getText());
    }
}
