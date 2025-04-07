package com.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBookingIdsTest {
    @BeforeTest
    public void getBeforeTest() {
        System.out.println("Welcome to the BeforeTest Annotations");
    }

    @Test
    public void getBookingIdsWithoutFilterTest() {
        // Get Response with booking Ids
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

        // Verify response as 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 but it's not");

        //Verify at least 1 Booking Id in response
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of Bookind Id is empty, but it shouldn't be");
    }
    @Test
    public void getBookingInfo(){
        //Get Response using some booking id as query parameter
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1090");
        response.print();
        // Verify response as 200
        Assert.assertEquals(response.getStatusCode(),200, "Status code is not matching");
        // Verify firstname and lastname of the response
        String resFname = response.jsonPath().getString("firstname");
        String resLname= response.jsonPath().getString("lastname");
        Assert.assertTrue(!(resFname.isEmpty()),"First Name is empty");
        Assert.assertTrue(!(resLname.isEmpty()),"Last Name is Empty");

        // Verify all the fields
        SoftAssert softassert = new SoftAssert();
        String actualfirstname= response.jsonPath().getString("firstname");
        softassert.assertEquals(actualfirstname,"Rakhi","First name in response not expected");

        String actuallastname= response.jsonPath().getString("lastname");
        softassert.assertEquals(actuallastname,"Mehta","Last name in response not expected");

        int price= response.jsonPath().getInt("totalprice");
        softassert.assertEquals(price,145,"Total price in response not expected");

        Boolean depositprice= response.jsonPath().getBoolean("depositpaid");
        softassert.assertFalse(depositprice,"Deposit paid in response should be true but it's not");

        String actualcheckin = response.jsonPath().getString("bookingdates.checkin");
        softassert.assertEquals(actualcheckin,"2025-12-27","Check in date response not expected");

        String actualcheckout = response.jsonPath().getString("bookingdates.checkout");
        softassert.assertEquals(actualcheckout,"2025-12-29","Check out date response not expected");

        String actualadditionalneeds = response.jsonPath().getString("additionalneeds");
        softassert.assertEquals(actualadditionalneeds,"Baby crib","Check out date response not expected");

        softassert.assertAll();




    }
}
