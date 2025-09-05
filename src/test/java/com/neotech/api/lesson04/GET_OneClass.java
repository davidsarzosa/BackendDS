package com.neotech.api.lesson04;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_OneClass {

//	@Test
	public void getOneClass() {

		// Set the URI

		// Given --> request --> params, -->, body, auth, headers,
		// --> when --> get, post, put/patch, delete
		// --> then assert status code, content.

		// HamCrest Matcher

		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		RequestSpecification request = RestAssured.given();

		// setting the path parameter
		request.pathParam("Id", "1021");

		// make the call
		Response response = request.when().get("/api/services/app/Class/Get/{Id}");

		// can we write this simpler?? with directly adding the path parameter on the
		// endPoint
		// request.when().get("/api/services/app/Class/Get/1052"); // is this the same
		// ??
		// YES OBAMA

		System.out.println("Status Line: " + response.getStatusLine());
		response.prettyPeek();
		System.out.println("----------------------------");

		// Assert using Hamcrest Matchers
		// Assert the vody contains name Cucumber

		// If we want to use the imports as a Static we can just declare and then we
		// dont have to use it inside the code
		// all the time
		response.then().body("result.name", equalTo("Math101")); // or we can use just that method EqualTo
		// assert that the description contains Math
		response.then().assertThat().body("result.description", containsString("Math")); // exactly what I need from
																							// Matchers import
		// assert that the instructor email ends with neo.com

		response.then().assertThat().body("result.instructorEmail", endsWith("neo.com"));

//		// ALL OF THE ABOVE, CAN BE DONE IN ONE LONG METHOD CHAIN 
//		RestAssured.given().pathParam("Id", 1052)
//		.when()
//		.get("/api/services/app/Class/Get/{Id}")
//		.prettyPeek()
//		.then().assertThat().body("result.name", equalTo("Math101"))
//		.and().assertThat().body("result.description", containsString("Math"))
//		.and().assertThat().body("result.instructorEmail", endsWith("neo.com"));
//		

	}

	@Test
	public void getOneClassShort() {
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		RestAssured.given().pathParam("Id", 1052).when().get("/api/services/app/Class/Get/{Id}").prettyPeek().then()
				.assertThat().body("result.name", equalTo("TestNG")).and().assertThat()
				.body("result.description", containsString("Student")).and().assertThat()
				.body("result.instructorEmail", endsWith("cihanadamey@neo"));

	}
}
