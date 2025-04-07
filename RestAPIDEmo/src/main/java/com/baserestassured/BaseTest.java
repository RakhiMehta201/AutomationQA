package com.baserestassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTest {
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
        Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
        return response;
    }
}
