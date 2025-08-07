package com.pgedlek.springaiimage.service;

import com.pgedlek.springaiimage.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAIService {
    byte[] getImage(Question question);

    String getDescription(MultipartFile file);
}
