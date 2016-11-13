package com.aleksandrbogomolov.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MongoDB {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDB(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
