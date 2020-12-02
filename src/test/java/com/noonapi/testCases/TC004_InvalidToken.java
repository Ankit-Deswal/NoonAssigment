package com.noonapi.testCases;

import com.noonapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC004_InvalidToken extends TestBase {


    Response response;


//invalid random token
    String token = "abcdefgh";

    @BeforeClass
    void getToken() throws InterruptedException
    {
        logger.info("********* Started TC004_InvalidToken  **********");
        response = RestAssured.given().when().get("https://interview.noonpay.biz/user/txn/history?token="+token);
    }

    @Test
    void checkErrorMessage()
    {

        String success = response.jsonPath().getString("errorMessage");
        Assert.assertEquals(success, "Invalid Token.");
}





    @Test
    void checkSuccessValue()
    {
        String suceess = response.jsonPath().getString("success");
        Assert.assertEquals(suceess, "false");


    }



    @AfterClass
    void tearDown()
    {
        logger.info("*********  Finished TC004_InvalidToken **********");
    }

}
