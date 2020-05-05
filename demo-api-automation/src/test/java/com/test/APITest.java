package com.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.apicore.BaseAPI;
import com.google.gson.Gson;
import com.model.JsonModel;
import com.utils.GlobalConstant;
import com.utils.HttpStatusCode;
import com.utils.URIPath;
import com.utils.Utils;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

@Listeners(com.report.TestListener.class)
public class APITest extends BaseAPI {


	@Test
	public void getCustomerDetailTest() {
		super.log("Executing get customer details API using Get method");
		Response response = given().header("accept", GlobalConstant.application_JSON).when().get(URIPath.CUSTOMER);
		
		System.out.println(response.asString());
		super.log("Getting response below\n "+response.asString());
	
		super.log("Verifying response code and content type");
		Assert.assertEquals(response.getStatusCode(), HttpStatusCode.HTTP_OK);
		Assert.assertEquals(response.getContentType(), GlobalConstant.application_JSONUTF8);

		// Converting json object into Java Object
		super.log("Loading json String into user defined JSONModel class");
		Gson gson = new Gson();
		JsonModel jsonModel = gson.fromJson(response.asString(), JsonModel.class);		
		
		// Verify response data
		super.log("Verify Id is greater than zero");
		Assert.assertTrue(jsonModel.getId() > 0, "Id is not greater than 0 actual id is : " + jsonModel.getId());
		
		super.log("Verify name can't be longer than 10 alpha characters");
		Assert.assertTrue(Utils.isAlpha(jsonModel.getName()),
				"Name is not alpha characters Actual name: " + jsonModel.getName());
		Assert.assertTrue(jsonModel.getName().length() <= 10,
				"Name length is greater than 10, Actual length: " + jsonModel.getName().length());

		super.log("Verify last name can't be longer than 10 alpha characters");
		Assert.assertTrue(Utils.isAlpha(jsonModel.getLast()), "Last Name is not alpha characters  : " + jsonModel.getLast());
		Assert.assertTrue(jsonModel.getLast().length() <= 10,
				"Last Name length is greater than 10, Actual length: " + jsonModel.getLast());

		super.log("Verify age must be integer and 0 > age < 120");
		Assert.assertTrue((jsonModel.getAge() > 0 && jsonModel.getAge() < 120),
				"Age limit should be between 0 to 120, Actual age is :" + jsonModel.getAge());
		
		super.log("Verify must only be F or M");
		Assert.assertTrue((jsonModel.getGender().equals("M") || jsonModel.getGender().equals("F")),
				"Gender should be M or F, actual gender is:" + jsonModel.getGender());
		
		super.log("Verify response time must be less than 500ms");
		Assert.assertTrue( response.getTime() < 500, "Response time ("+response.getTime()+ ") is greater than 500 milliseconds"  );
	}
	
}
