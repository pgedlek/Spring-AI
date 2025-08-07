package com.pgedlek.springairag.config;

import org.springframework.core.io.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "sfg.aiapp")
public class VectorStoreProperties {
    @Getter
    @Setter
    private String vectorStorePath;
    @Getter
    @Setter
    private List<Resource> documentsToLoad;
}
