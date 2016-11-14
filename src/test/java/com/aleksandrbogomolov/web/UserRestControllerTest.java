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

    private final String url = "http://localhost:8888/users";

    @Before
    public void setUp() throws Exception {
        if (template.collectionExists("users")) template.dropCollection("users");
        template.save(loggedUser);
    }

    @After
    public void tearDown() throws Exception {
        template.dropCollection("users");
    }

    @Test
    public void saveUser() throws Exception {
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .contentType(ContentType.JSON).body(testUser1)
               .when().post(url)
               .then().statusCode(200).body("name", equalTo("test"));
    }

    @Test
    public void delete() throws Exception {
        template.insert(testUser1);
        template.insert(testUser2);
        assertTrue(3 == template.getCollection("users").count());
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .when().delete(url + "/" + testUser2.getId())
               .then().statusCode(200);
        assertTrue(2 == template.getCollection("users").count());
    }

    @Test
    public void getOneById() throws Exception {
        template.insert(testUser1);
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .when().get(url + "/" + testUser1.getId())
               .then().statusCode(200).body("name", equalTo("test"));
    }

    @Test
    public void getAll() throws Exception {
        template.insert(testUser1);
        template.insert(testUser2);
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .when().get(url)
               .then().statusCode(200).body("name", Matchers.hasItems("User", "test", "test2"));
    }
}
