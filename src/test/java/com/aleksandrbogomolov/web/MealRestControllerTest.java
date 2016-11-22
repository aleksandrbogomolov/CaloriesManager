package com.aleksandrbogomolov.web;

import com.aleksandrbogomolov.domain.Meal;
import com.aleksandrbogomolov.domain.User;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class MealRestControllerTest extends AbstractRestControllerTest {

    private final String url = "http://localhost:8888/meals";

    @Before
    public void setUp() throws Exception {
        if (!template.exists(new Query(Criteria.where("name").is(loggedUser.getName())), User.class))
            template.save(loggedUser);
    }

    @After
    public void tearDown() throws Exception {
        template.dropCollection("meals");
    }

    @Test
    public void save() throws Exception {
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .contentType(ContentType.JSON).body(testMeal1)
               .when().post(url)
               .then().statusCode(200);
        assertTrue(1 == template.getCollection("meals").count());
        assertTrue(testMeal1.equals(template.findOne(new Query(Criteria.where("_id").is(testMeal1.getId())), Meal.class)));
    }

    @Test
    public void delete() throws Exception {
        template.save(testMeal1);
        template.save(testMeal2);
        assertTrue(2 == template.getCollection("meals").count());
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .when().delete(url + "/" + testMeal1.getId())
               .then().statusCode(200);
        assertTrue(1 == template.getCollection("meals").count());
    }

    @Test
    public void getOne() throws Exception {
        template.save(testMeal1);
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .when().get(url + "/" + testMeal1.getId())
               .then().statusCode(200).body("description", equalTo("Завтрак"));
    }

    @Test
    public void getAll() throws Exception {
        template.save(testMeal1);
        template.save(testMeal2);
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .when().get(url)
               .then().statusCode(200).body("description", Matchers.hasItems("Завтрак", "Обед"));
    }

    @Test
    public void getFiltered() throws Exception {
        template.save(testMeal1);
        template.save(testMeal2);
        given().auth().basic(loggedUser.getName(), loggedUser.getPassword())
               .param("startDate", testMeal1.getDateTime().toLocalDate().toString())
               .param("endDate", testMeal1.getDateTime().toLocalDate().toString())
               .param("startTime", "")
               .param("endTime", "")
               .when().get(url + "/filter")
               .then().statusCode(200).body("description", is(not(hasItem("Обед"))));
    }
}
