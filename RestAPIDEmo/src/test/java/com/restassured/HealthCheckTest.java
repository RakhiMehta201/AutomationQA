package com.restassured;

import com.baserestassured.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class HealthCheckTest extends BaseTest {
    @Test
    public void HealthCheckTest()
    {
        given().spec(spec).
        when().
          get("/ping").
        then().
          assertThat().
          statusCode(201);

    }

    @Test
    public void HeaderAmdCookiesTest()
    {
        Header someHeader = new Header("some name","some value");
        spec.header(someHeader);
        Cookie someCookie = new Cookie.Builder("some name","some value").build();
        spec.cookie(someCookie);
        Response response = RestAssured.given(spec).cookie("test cookie name","Cookie Value")
                .header("Test Header Name","Test Header Value").log().all().get("/ping");

        // Get Headers
        Headers headers =response.getHeaders();
        System.out.println("Headers :"+headers);

        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1.getName()+" "+serverHeader1.getValue());
        String serverHeader2 = response.getHeader("Server");
        System.out.println("Server :"+serverHeader2);

        //Get Cookies
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies: " + cookies);


    }
}
