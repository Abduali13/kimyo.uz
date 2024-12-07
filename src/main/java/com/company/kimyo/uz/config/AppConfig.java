package com.company.kimyo.uz.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {
    private static final Logger APP_LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @PostConstruct
    public void postConstructMethod(){
        log.info("Post Constructor Started!");
    }

    @PreDestroy
    public void preDestroyMethod(){
        APP_LOGGER.warn("This Project Destroyed");
    }
}
