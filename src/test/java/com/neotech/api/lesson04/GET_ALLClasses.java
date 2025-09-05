package com.neotech.api.lesson04;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GET_ALLClasses {

	public static void main(String[] args) {

		// We define the method - GET
		// We define the URI
		// We set any params, header, auth, body...

		// SEND

		// validate response code
		// validate elements on PayLoad / bodyResponse

		// set the base uri

		// this is set up as a class variable
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";

		System.out.println(RestAssured.baseURI);
		System.out.println("------------------------");

		// Build the request - this is where I can specify params, auth, headers
		RequestSpecification request = RestAssured.given();
		Response response = request.when().get("/api/services/app/Class/GetAll");
		System.out.println("response code " + response.statusCode());
		System.out.println("response code " + response.getStatusCode());
		System.out.println("------------------------");
		// Get Headers
		System.out.println("Content Type --> " + response.headers());
		System.out.println("------------------------");

		// get a particular hearder
		String contentType = response.header("Content-Type");
		System.out.println(contentType);
		System.out.println("------------------------");

		// Headers headers = response.headers();
		// System.out.println(heardes);

		ResponseBody body = response.body();
		System.out.println(body.asString());
		System.out.println("------------------------");

		System.out.println(body.asPrettyString());
		System.out.println("------------------------");
	
		// we can also print the response directly
		
		response.prettyPrint();
		
	}

}
