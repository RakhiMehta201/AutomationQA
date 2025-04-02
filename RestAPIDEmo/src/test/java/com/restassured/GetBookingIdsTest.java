package com.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/2");
        response.print();
        // Verify response as 200
        Assert.assertEquals(response.getStatusCode(),200, "Status code is not matching");
        // Verify firstname and lastname of the response
        String resFname = response.jsonPath().getString("firstname");
        String resLname= response.jsonPath().getString("lastname");
        Assert.assertTrue(!(resFname.isEmpty()),"First Name is empty");
        Assert.assertTrue(!(resLname.isEmpty()),"Last Name is Empty");



    }
}
