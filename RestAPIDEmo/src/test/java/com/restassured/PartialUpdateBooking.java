package com.restassured;

import com.baserestassured.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBooking extends BaseTest {
    @Test
    public void partialUpdateBooking()
    {
        //Step 1 create booking
        Response responseCreate = createBookingMethod();
        responseCreate.print();
        // Step 2 get booking id of new booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");
        // Step 3 update new booking
        //Create JsonBody
        JSONObject body = new JSONObject();
        body.put("firstname","Aaradhya");
        // for date create new json object
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2025-12-25");
        bookingdates.put("checkout","2025-12-29");
        body.put("bookingdates",bookingdates);

        //Get Response
        Response responseUpdate = RestAssured.given(spec).auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).body(body.toString()).patch("/booking/"+bookingid);
        responseUpdate.print();

        //Verification
        // Verify all the fields
        SoftAssert softassert = new SoftAssert();
        String actualfirstname= responseUpdate.jsonPath().getString("firstname");
        softassert.assertEquals(actualfirstname,"Aaradhya","First name in response not expected");

        String actuallastname= responseUpdate.jsonPath().getString("lastname");
        softassert.assertEquals(actuallastname,"Mehta","Last name in response not expected");

        int price= responseUpdate.jsonPath().getInt("totalprice");
        softassert.assertEquals(price,145,"Total price in response not expected");

        Boolean depositprice= responseUpdate.jsonPath().getBoolean("depositpaid");
        softassert.assertFalse(depositprice,"Deposit paid in response should be true but it's not");

        String actualcheckin = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softassert.assertEquals(actualcheckin,"2025-12-25","Check in date response not expected");
//
        String actualcheckout = responseUpdate.jsonPath().getString("bookingdates.checkout");
        softassert.assertEquals(actualcheckout,"2025-12-29","Check out date response not expected");

        String actualadditionalneeds = responseUpdate.jsonPath().getString("additionalneeds");
        softassert.assertEquals(actualadditionalneeds,"Baby crib","Check out date response not expected");

        softassert.assertAll();
    }

}
