package com.restassured;

import com.baserestassured.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBooking extends BaseTest {
    @Test
    public void createBooking()
    {
        Response response = createBookingMethod();
        response.print();

        //Verification
        // Verify all the fields
        SoftAssert softassert = new SoftAssert();
        String actualfirstname= response.jsonPath().getString("booking.firstname");
        softassert.assertEquals(actualfirstname,"Rakhi","First name in response not expected");

        String actuallastname= response.jsonPath().getString("booking.lastname");
        softassert.assertEquals(actuallastname,"Mehta","Last name in response not expected");

        int price= response.jsonPath().getInt("booking.totalprice");
        softassert.assertEquals(price,145,"Total price in response not expected");

        Boolean depositprice= response.jsonPath().getBoolean("booking.depositpaid");
        softassert.assertFalse(depositprice,"Deposit paid in response should be true but it's not");

        String actualcheckin = response.jsonPath().getString("booking.bookingdates.checkin");
        softassert.assertEquals(actualcheckin,"2025-12-27","Check in date response not expected");

        String actualcheckout = response.jsonPath().getString("booking.bookingdates.checkout");
        softassert.assertEquals(actualcheckout,"2025-12-29","Check out date response not expected");

        String actualadditionalneeds = response.jsonPath().getString("booking.additionalneeds");
        softassert.assertEquals(actualadditionalneeds,"Baby crib","Check out date response not expected");

        softassert.assertAll();
    }

}
