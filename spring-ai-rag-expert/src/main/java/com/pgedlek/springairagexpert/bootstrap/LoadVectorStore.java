package com.pgedlek.springairagexpert.bootstrap;

import com.pgedlek.springairagexpert.config.VectorStoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoadVectorStore implements CommandLineRunner {
    @Autowired
    VectorStore vectorStore;

    @Autowired
    VectorStoreProperties vectorStoreProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading vector store...");
        if (vectorStore.similaritySearch("Sportsman").isEmpty()) {
            vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
                log.debug("Loading vector document {}", document);

                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> documents = documentReader.get();

                TextSplitter splitter = new TokenTextSplitter();
                List<Document> applied = splitter.apply(documents);

                vectorStore.add(applied);
            });
        }
        log.info("Vector store loaded.");
    }
}
