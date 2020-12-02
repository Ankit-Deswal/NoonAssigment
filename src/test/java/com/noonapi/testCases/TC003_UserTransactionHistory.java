
/*
 * Author: Hemant Choudhari
 * summary: Test Case 3 Reset Password
 * Date: 09/14/2019
 */

/******************************************************
Test Name: Reset password existing user
URI: http: https://api2.fox.com/v2.0/reset
Request Type: POST
Request Payload(Body):

{
	"email":"hemant10@fox.com",
	"password":"abcdef"
}

********* Validations **********
message  and detail Validation
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

import java.util.Map;

public class TC003_UserTransactionHistory extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	String email = "test@gmail.com";
	String password = "Qwerty123";

	String token;

	@BeforeClass
	void getToken() throws InterruptedException
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
		Map<String, String> company = response.jsonPath().getMap("data");
		 token =company.get("token");
		System.out.println(token);
}

	@Test
	void checkTransactionHistory()
	{


		response = RestAssured.given().when().get("https://interview.noonpay.biz/user/txn/history?token="+token);
		String statusLine = response.getStatusLine().trim();


		Assert.assertEquals(response.getStatusCode(), 200);
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
