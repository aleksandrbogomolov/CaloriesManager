package com.aleksandrbogomolov.configuration;

import com.aleksandrbogomolov.util.converters.LocalDateTimeToStringConverter;
import com.aleksandrbogomolov.util.converters.StringToLocalDateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Arrays;

@Configuration
public class MongoConfiguration {

    private final MongoDbFactory mongoDbFactory;

    @Autowired
    public MongoConfiguration(MongoDbFactory mongoDbFactory) {
        this.mongoDbFactory = mongoDbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MappingMongoConverter converter = new MappingMongoConverter(
                new DefaultDbRefResolver(mongoDbFactory), new MongoMappingContext());
        converter.setCustomConversions(new CustomConversions(Arrays.asList(new StringToLocalDateTimeConverter(), new LocalDateTimeToStringConverter())));
        converter.afterPropertiesSet();
        return new MongoTemplate(mongoDbFactory, converter);
    }
}
