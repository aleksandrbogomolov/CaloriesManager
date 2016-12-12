package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.AbstractTest;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class UserRestControllerTest extends AbstractTest {

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
        Response response = getResponseForCSRF();
        given().cookie("XSRF-TOKEN", response.cookie("XSRF-TOKEN"))
                .header("X-XSRF-TOKEN", response.cookie("XSRF-TOKEN"))
                .auth().basic(loggedUser.getName(), loggedUser.getPassword())
                .contentType(JSON).body(testUser1)
                .when().post(url)
                .then().statusCode(200).body("name", equalTo("test"));
    }

    @Test
    public void delete() throws Exception {
        Response response = getResponseForCSRF();
        template.insert(testUser1);
        template.insert(testUser2);
        assertTrue(3 == template.getCollection("users").count());
        given().cookie("XSRF-TOKEN", response.cookie("XSRF-TOKEN"))
                .header("X-XSRF-TOKEN", response.cookie("XSRF-TOKEN"))
                .auth().basic(loggedUser.getName(), loggedUser.getPassword())
                .when().delete(url + "/" + testUser2.getName())
                .then().statusCode(200);
        assertTrue(2 == template.getCollection("users").count());
    }

    @Test
    public void checkDeleteNotFound() {
        Response response = getResponseForCSRF();
        template.insert(testUser1);
        assertTrue(2 == template.getCollection("users").count());
        given().cookie("XSRF-TOKEN", response.cookie("XSRF-TOKEN"))
                .header("X-XSRF-TOKEN", response.cookie("XSRF-TOKEN"))
                .auth().basic(loggedUser.getName(), loggedUser.getPassword())
                .when().delete(url + "/" + testUser2.getName())
                .then().statusCode(404);
        assertTrue(2 == template.getCollection("users").count());
    }

    @Test
    public void getOneByName() throws Exception {
        template.insert(testUser1);
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
                .when().get(url + "/" + testUser1.getName())
                .then().statusCode(200).body("name", equalTo("test"));
    }

    @Test
    public void checkGetOneNotFound() {
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
                .when().get(url + "/" + testUser1.getName())
                .then().statusCode(404);
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
