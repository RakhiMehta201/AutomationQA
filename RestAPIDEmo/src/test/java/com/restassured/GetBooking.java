package com.restassured;

import com.baserestassured.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBooking extends BaseTest {

    @Test
    public void getBooking() {
        //Step 1 create booking
//        Response responseCreate = createBookingMethod();
//        responseCreate.print();

        // Set Path Paremeters
       // spec.pathParam("bookingId",responseCreate.jsonPath().getInt("bookingid"));

        //Get Response using some booking id as path parameter
//        Response response = RestAssured.given(spec).get("/booking/{bookingId}");
//        response.print();

        //Get Response using first name as query parameter
        spec.queryParam("firstname","Rakhi");
        spec.queryParam("lastname","Mehta");
        Response response = RestAssured.given(spec).get("/booking");
        response.print();
        // Verify response as 200
        Assert.assertEquals(response.getStatusCode(),200, "Status code is not matching");

//        // Verify all the fields
//        SoftAssert softassert = new SoftAssert();
//        String actualfirstname= response.jsonPath().getString("firstname");
//        softassert.assertEquals(actualfirstname,"Rakhi","First name in response not expected");
//
//        String actuallastname= response.jsonPath().getString("lastname");
//        softassert.assertEquals(actuallastname,"Mehta","Last name in response not expected");
//
//        int price= response.jsonPath().getInt("totalprice");
//        softassert.assertEquals(price,145,"Total price in response not expected");
//
//        Boolean depositprice= response.jsonPath().getBoolean("depositpaid");
//        softassert.assertFalse(depositprice,"Deposit paid in response should be true but it's not");
//
//        String actualcheckin = response.jsonPath().getString("bookingdates.checkin");
//        softassert.assertEquals(actualcheckin,"2025-12-27","Check in date response not expected");
//
//        String actualcheckout = response.jsonPath().getString("bookingdates.checkout");
//        softassert.assertEquals(actualcheckout,"2025-12-29","Check out date response not expected");
//
////        String actualadditionalneeds = response.jsonPath().getString("additionalneeds");
////        softassert.assertEquals(actualadditionalneeds,"additional needs should be null but it's not");
//
//        softassert.assertAll();

    }
}
