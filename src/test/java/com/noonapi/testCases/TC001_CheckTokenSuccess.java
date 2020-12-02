

package com.noonapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.noonapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_CheckTokenSuccess extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	
	String email = "test@gmail.com";
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
	void checkResponseCode()
	{
		String responseBody = response.getBody().asString();
		int responseCode = response.getStatusCode();
		System.out.println("responseCOde is :" + responseCode);
		Assert.assertEquals(responseCode, 200);

	}
		

		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine().trim();
		Assert.assertEquals(statusLine, "HTTP/1.1 200");
		
	}

	@Test
	void checkSuccessValue()
	{

		String suceess = response.jsonPath().getString("success");
		Assert.assertEquals(suceess, "true");


	}


	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_CheckToken_success **********");
	}

}
