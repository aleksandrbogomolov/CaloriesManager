package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Meal;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class MealRestControllerTest extends AbstractRestControllerTest {

    private final String host = "http://localhost:8888/meals";

    @Before
    public void setUp() throws Exception {
        if (!template.collectionExists("users")) template.createCollection("users");
        template.save(testUser1);
    }

    @After
    public void tearDown() throws Exception {
        template.dropCollection("meals");
    }

    @Test
    public void save() throws Exception {
        given().auth().basic(userName, userPassword)
               .contentType(ContentType.JSON).body(testMeal1)
               .when().post(host)
               .then().statusCode(200);
        Query query = new Query(Criteria.where("_id").is(testMeal1.getId()));
        assertTrue(1 == template.getCollection("meals").count());
        assertTrue(testMeal1.equals(template.findOne(query, Meal.class)));
    }

    @Test
    public void delete() throws Exception {
        template.save(testMeal1);
        template.save(testMeal2);
        assertTrue(2 == template.getCollection("meals").count());
        given().auth().basic(userName, userPassword)
               .when().delete(host + "/" + testMeal1.getId())
               .then().statusCode(200);
        assertTrue(1 == template.getCollection("meals").count());
    }

    @Test
    public void getOne() throws Exception {
        template.save(testMeal1);
        given().auth().basic(userName, userPassword)
               .when().get(host + "/" + testMeal1.getId())
               .then().statusCode(200).body("description", equalTo("Завтрак"));
    }

    @Test
    public void getAll() throws Exception {
        template.save(testMeal1);
        template.save(testMeal2);
        given().auth().basic(userName, userPassword)
               .when().get(host)
               .then().statusCode(200).body("description", Matchers.hasItems("Завтрак", "Обед"));
    }
}
