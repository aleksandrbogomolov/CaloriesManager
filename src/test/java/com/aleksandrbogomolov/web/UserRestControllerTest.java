package com.aleksandrbogomolov.web;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class UserRestControllerTest extends AbstractRestControllerTest {

    private final String host = "http://localhost:8888/user";

    @Before
    public void setUp() throws Exception {
        if (template.collectionExists("users")) template.dropCollection("users");
    }

    @After
    public void tearDown() throws Exception {
        template.dropCollection("users");
    }

    @Test
    public void saveUser() throws Exception {
        given().auth().basic(userName, userPassword)
               .contentType(ContentType.JSON).body(testUser1)
               .when().post(host)
               .then().statusCode(200).body("name", equalTo("test1"));
    }

    @Test
    public void delete() throws Exception {
        template.insert(testUser1);
        template.insert(testUser2);
        assertTrue(2 == template.getCollection("users").count());
        given().auth().basic(userName, userPassword)
               .when().delete(host + "/test2@mail.ru")
               .then().statusCode(200);
        assertTrue(1 == template.getCollection("users").count());
    }

    @Test
    public void getOneById() throws Exception {
        template.insert(testUser1);
        given().auth().basic(userName, userPassword)
               .when().get(host + "/" + testUser1.getId())
               .then().statusCode(200).body("name", equalTo("test1"));
    }

    @Test
    public void getOneByEmail() throws Exception {
        template.insert(testUser1);
        given().auth().basic(userName, userPassword)
               .when().get(host + "/search/test1@mail.ru")
               .then().statusCode(200).body("name", equalTo("test1"));
    }

    @Test
    public void getAll() throws Exception {
        template.insert(testUser1);
        template.insert(testUser2);
        given().auth().basic(userName, userPassword)
               .when().get(host)
               .then().statusCode(200).body("name", Matchers.hasItems("test1", "test2"));
    }
}
