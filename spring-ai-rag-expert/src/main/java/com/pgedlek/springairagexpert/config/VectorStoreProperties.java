package com.pgedlek.springairagexpert.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

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
