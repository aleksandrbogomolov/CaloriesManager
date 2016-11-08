package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Role;
import com.aleksandrbogomolov.domain.User;
import com.mongodb.MongoClient;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8888")
public class UserRestControllerTest {

    private final String database = "calories-manager", host = "http://localhost:8888";

    private final MongoClient mongo = new MongoClient("localhost", 27017);

    private final MongoTemplate template = new MongoTemplate(mongo, database);

    private final User testUser1 = new User("test1", "test1@mail.ru", "pass1", 2000, true, LocalDate.of(2016, 11, 7), new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

    private final User testUser2 = new User("test2", "test2@mail.ru", "pass2", 2000, true, LocalDate.of(2016, 11, 8), new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

    @After
    public void tearDown() throws Exception {
        template.dropCollection("users");
    }

    @Test
    public void saveUser() throws Exception {
        given().auth().basic("user", "password")
               .contentType(ContentType.JSON).body(testUser1)
               .when().post(host + "/user")
               .then().statusCode(200).body("name", equalTo("test1"));
    }

    @Test
    public void delete() throws Exception {
        template.insert(testUser1);
        template.insert(testUser2);
        assertTrue(2 == template.getCollection("users").count());
        given().auth().basic("user", "password")
               .when().delete(host + "/user/test2@mail.ru")
               .then().statusCode(200);
        assertTrue(1 == template.getCollection("users").count());
    }

    @Test
    public void getOne() throws Exception {
        template.insert(testUser1);
        given().auth().basic("user", "password")
               .when().get(host + "/user/test1@mail.ru")
               .then().statusCode(200).body("name", equalTo("test1"));
    }

    @Test
    public void getAll() throws Exception {
        template.insert(testUser1);
        template.insert(testUser2);
        given().auth().basic("user", "password")
               .when().get(host + "/user/all")
               .then().statusCode(200).body("name", Matchers.hasItems("test1", "test2"));
    }
}
