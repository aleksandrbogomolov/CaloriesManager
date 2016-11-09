package com.aleksandrbogomolov.web;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractRestControllerTest {

    final String database = "calories-manager";

    final MongoClient mongo = new MongoClient("localhost", 27017);

    final MongoTemplate template = new MongoTemplate(mongo, database);
}
