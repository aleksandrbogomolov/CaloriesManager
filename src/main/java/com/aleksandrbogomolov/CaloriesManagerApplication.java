package com.aleksandrbogomolov;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.domain.Role;
import com.aleksandrbogomolov.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
@EnableMongoRepositories
public class CaloriesManagerApplication implements CommandLineRunner {

    private final MongoTemplate template;

    @Autowired
    public CaloriesManagerApplication(MongoTemplate template) {this.template = template;}

    public static void main(String[] args) {
        SpringApplication.run(CaloriesManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final User user = new User(null, "User", "user@mail.ru", "password", 2000, true, LocalDate.of(2016, 11, 7), new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

        final User admin = new User(null, "Admin", "admmin@mail.ru", "admin", 2500, true, LocalDate.of(2016, 11, 8), new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));

        final Meal meal1 = new Meal(null, LocalDateTime.of(2016, 11, 10, 10, 8), "Завтрак", 500, user);

        final Meal meal2 = new Meal(null, LocalDateTime.of(2016, 11, 10, 13, 0), "Обед", 1000, user);

        if (!template.exists(new Query(Criteria.where("name").is(user.getName())), User.class)) template.save(user);
        if (!template.exists(new Query(Criteria.where("name").is(admin.getName())), User.class)) template.save(admin);
        if (!template.exists(new Query(Criteria.where("description").is(meal1.getDescription())), Meal.class))
            template.save(meal1);
        if (!template.exists(new Query(Criteria.where("description").is(meal2.getDescription())), Meal.class))
            template.save(meal2);
    }
}
