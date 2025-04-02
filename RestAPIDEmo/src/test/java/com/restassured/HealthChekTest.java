package com.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthChekTest {
    @Test
    public void HealthChekTest()
    {
        given().
        when().
          get("https://restful-booker.herokuapp.com/ping").
        then().
          assertThat().
          statusCode(201);

    }
}
