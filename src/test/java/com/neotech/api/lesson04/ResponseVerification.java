package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseVerification {

	@Test
	public void veryfyAllClassessRequest() {

		// CONECTION
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		RequestSpecification request = RestAssured.given();

		// PARAMETER
		// any element of the request can be specified in the RequestSpecification
		// object, before we make the call
		request.queryParam("MaxResultCount", 1000);

		// MAKE THE CALL
		Response response = request.when().get("/api/services/app/Class/GetAll");

		// VALIDATION OF RESPONSE TYPE IN 2 DIFFERENT WAYS
		// Validate that the status code is 200
		response.then().assertThat().statusCode(200);
		// Validate using JUnit assertion
		Assert.assertEquals(200, response.statusCode());

		// VALIDATION OF CONTECT TYPE IN 2 DIFFERENT WAYS
		// Verify that the contect-Type is application/json...
		response.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
		// Using Junit assertion
		Assert.assertEquals("application/json; charset=utf-8", response.header("Content-Type"));

		// VALIDATE THE RESPONSE CONTAINS CUCUMBERS TWO DIFFERENT WAYS
		String body = response.body().asString();
		Assert.assertTrue("Cucumber NOT Found! ", body.contains("Cucumber"));
		// OneLiner
		Assert.assertTrue(response.body().asString().contains("Cucumber")); 

	}
	
}
