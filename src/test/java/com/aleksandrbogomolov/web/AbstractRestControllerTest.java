package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.domain.Role;
import com.aleksandrbogomolov.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8888")
abstract class AbstractRestControllerTest {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    MongoTemplate template;

    private int startId = 1;

    final User loggedUser = new User(String.valueOf(startId), "User", "user@mail.ru", "password", 2000, true, LocalDate.of(2016, 11, 7), new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

    final User testUser1 = new User(String.valueOf(startId + 1), "test", "test1@mail.ru", "test", 2000, true, LocalDate.of(2016, 11, 7), new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

    final User testUser2 = new User(String.valueOf(startId + 2), "test2", "test2@mail.ru", "pass2", 2000, true, LocalDate.of(2016, 11, 8), new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

    final Meal testMeal1 = new Meal(String.valueOf(startId + 3), LocalDateTime.of(2016, 11, 10, 10, 8), "Завтрак", 500, loggedUser);

    final Meal testMeal2 = new Meal(String.valueOf(startId + 4), LocalDateTime.of(2016, 11, 10, 13, 0), "Обед", 1000, loggedUser);
}
