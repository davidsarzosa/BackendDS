package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POST_GenerateToken {
	@Test
	public void generateToken() {

		// Set the Base URI
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";

		// Create a request
		RequestSpecification request = RestAssured.given();

		// since Im sending a JSON payload, I need to add that to the header
		request.header("Content-Type", "application/json; charset=utf-8");

		String payload = "{\r\n" + "  \"userNameOrEmailAddress\": \"Tester\",\r\n"
				+ "  \"password\": \"Student@Neo\",\r\n" + "  \"rememberClient\": true\r\n" + "}";

		request.body(payload);

		// make the call
		Response response = request.when().post("/api/TokenAuth/Authenticate");
		// another way to send the request
		// request.when().request(Method.POST, "/api/TokenAuth/Authenticate");

		System.out.println("---------------------------------");
		System.out.println("Status Code " + response.getStatusCode()); // getStatusCode();
		// VALIDATION TIME
		System.out.println("---------------------------------");
		System.out.println(response.statusLine());
		response.prettyPrint();
		System.out.println("---------------------------------");

		// VALIDATION 2 WAYS
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(200, response.statusCode());

		// IN A SHORT WAY
		System.out.println("---------------------------------");

	}

	@Test
	public void generateTokenShortWay() {
		// Set the Base URI
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";

		String payload = "{\r\n" + "  \"userNameOrEmailAddress\": \"Tester\",\r\n"
				+ "  \"password\": \"Student@Neo\",\r\n" + "  \"rememberClient\": true\r\n" + "}";

		RestAssured.given()
				// set the request
				.header("Content-Type", "application/json; charset=utf-8").body(payload)
				// sending the request
				.when().post("/api/TokenAuth/Authenticate").prettyPeek()
				// validation
				.then().assertThat().statusCode(200);
	}
}