
/*
 * Author: Hemant Choudhari
 * summary: Test Case 2 Login user
 * Date: 09/14/2019
 */

/******************************************************
Test Name: Login Using existing user
URI: http:  https://api2.fox.com/v2.0/login
Request Type: POST
Request Payload(Body):

{
	"email":"hemant10@fox.com",
	"password":"abcdef"
}

********* Validations **********
isVerified : true
Status Code : 200
Response Time Validation
Status Line : HTTP/1.1 200 OK
Content Type : application/json

*********************************************************/



package com.noonapi.testCases;
import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.*;

import com.noonapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_TokenInvalidUser extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	String email = "Invalid@gmail.com";
	String password = "Qwerty123";



	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("********* Started TC001_CheckToken_success  **********");

		RestAssured.baseURI = "https://interview.noonpay.biz/user/validate";
		httpRequest = RestAssured.given();



		JSONObject requestParams = new JSONObject();
		requestParams.put("email", email); // Cast
		requestParams.put("password", password);



		httpRequest.header("Content-Type", "application/json");




		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST);



	}

	@Test
	void checkResposeCode()
	{
		String responseBody = response.getBody().asString();
		int responseCode = response.getStatusCode();
		System.out.println("responseCOde is :" + responseCode);
		Assert.assertEquals(responseCode, 200);

	}


//suceess should be false and error message should be Invalid Credentials.
	@Test
	void checkSuccessValue()
	{
		
		String suceess = response.jsonPath().getString("success");
		Assert.assertEquals(suceess, "false");


	}
	@Test
	void checkErrorMessage()
	{
		String responseBody = response.getBody().asString();
		String suceess = response.jsonPath().getString("errorMessage");
		Assert.assertEquals(suceess, "Invalid Credentials.");


	}

	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC002_TC002_TokenInvalidUser  **********");
	}
	
}
