package com.restassured;

import com.baserestassured.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBooking extends BaseTest {
    @Test
    public void DeleteBooking() {
        //Step 1 create booking
        Response responseCreate = createBookingMethod();
        responseCreate.print();
        // Step 2 get booking id of new booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //Get Response
        Response responseDelete = RestAssured.given(spec).auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON).delete("/booking/"+bookingid);
        responseDelete.print();

        //Verification of status code
        Assert.assertEquals(responseDelete.statusCode(), 201, "Status code should be 200 but it's " + responseDelete.statusCode());

        //Get Response using some booking id as query parameter
        Response response = RestAssured.given(spec).get("/booking/"+ bookingid);
        response.print();

        Assert.assertEquals(response.getBody().asString(),"Not Found","Body should not found , but it's there");

    }

}
