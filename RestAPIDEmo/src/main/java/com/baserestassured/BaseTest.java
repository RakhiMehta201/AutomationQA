package com.baserestassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected RequestSpecification spec;
    @BeforeMethod
    public void setUp()
    {
        //Request Specification
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
    protected Response createBookingMethod() {
        //Create JsonBody
        JSONObject body = new JSONObject();
        body.put("firstname","Rakhi");
        body.put("lastname","Mehta");
        body.put("totalprice",145);
        body.put("depositpaid",false);
        // for date create new json object
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2025-12-27");
        bookingdates.put("checkout","2025-12-29");
        body.put("bookingdates",bookingdates);
        body.put("additionalneeds","Baby crib");

        //Get Response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/booking");
        return response;
    }
}
