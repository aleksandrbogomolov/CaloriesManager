package com.aleksandrbogomolov.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Getter
@Configuration
public class MongoConfiguration {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoConfiguration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
