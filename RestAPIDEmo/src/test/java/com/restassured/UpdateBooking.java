package com.restassured;

import com.baserestassured.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBooking extends BaseTest {
    @Test
    public void updateBooking()
    {
        //Step 1 create booking
        Response responseCreate = createBookingMethod();
        responseCreate.print();
        // Step 2 get booking id of new booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");
        // Step 3 update new booking
        //Create JsonBody
        JSONObject body = new JSONObject();
        body.put("firstname","Rajan");
        body.put("lastname","Mehta");
        body.put("totalprice",125);
        body.put("depositpaid",true);
        // for date create new json object
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2025-12-27");
        bookingdates.put("checkout","2025-12-29");
        body.put("bookingdates",bookingdates);
        body.put("additionalneeds","Baby crib");

        //Get Response
        Response responseUpdate = RestAssured.given(spec).auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).body(body.toString()).put("/booking/"+bookingid);
        responseUpdate.print();

        //Verification
        // Verify all the fields
        SoftAssert softassert = new SoftAssert();
        String actualfirstname= responseUpdate.jsonPath().getString("firstname");
        softassert.assertEquals(actualfirstname,"Rajan","First name in response not expected");

        String actuallastname= responseUpdate.jsonPath().getString("lastname");
        softassert.assertEquals(actuallastname,"Mehta","Last name in response not expected");

        int price= responseUpdate.jsonPath().getInt("totalprice");
        softassert.assertEquals(price,125,"Total price in response not expected");

        Boolean depositprice= responseUpdate.jsonPath().getBoolean("depositpaid");
        softassert.assertTrue(depositprice,"Deposit paid in response should be true but it's not");

        String actualcheckin = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softassert.assertEquals(actualcheckin,"2025-12-27","Check in date response not expected");

        String actualcheckout = responseUpdate.jsonPath().getString("bookingdates.checkout");
        softassert.assertEquals(actualcheckout,"2025-12-29","Check out date response not expected");

        String actualadditionalneeds = responseUpdate.jsonPath().getString("additionalneeds");
        softassert.assertEquals(actualadditionalneeds,"Baby crib","Check out date response not expected");

        softassert.assertAll();
    }

}
