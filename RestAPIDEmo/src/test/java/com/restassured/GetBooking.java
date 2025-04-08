package com.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBooking {

    @Test
    public void getBooking() {

        //Get Response using some booking id as query parameter
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5/");
        response.print();
        // Verify response as 200
        Assert.assertEquals(response.getStatusCode(),200, "Status code is not matching");

        // Verify all the fields
        SoftAssert softassert = new SoftAssert();
        String actualfirstname= response.jsonPath().getString("firstname");
        softassert.assertEquals(actualfirstname,"Mary","First name in response not expected");

        String actuallastname= response.jsonPath().getString("lastname");
        softassert.assertEquals(actuallastname,"Jones","Last name in response not expected");

        int price= response.jsonPath().getInt("totalprice");
        softassert.assertEquals(price,676,"Total price in response not expected");

        Boolean depositprice= response.jsonPath().getBoolean("depositpaid");
        softassert.assertTrue(depositprice,"Deposit paid in response should be true but it's not");

        String actualcheckin = response.jsonPath().getString("bookingdates.checkin");
        softassert.assertEquals(actualcheckin,"2021-03-11","Check in date response not expected");

        String actualcheckout = response.jsonPath().getString("bookingdates.checkout");
        softassert.assertEquals(actualcheckout,"2023-05-27","Check out date response not expected");

        String actualadditionalneeds = response.jsonPath().getString("additionalneeds");
        softassert.assertNull(actualadditionalneeds,"additional needs should be null but it's not");

        softassert.assertAll();

    }
}
