package com.neotech.steps;

import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateStudentSteps {

	int id;
	Response response;

	@When("I create a new student and store its id")
	public void i_create_a_new_student_and_store_its_id() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		String payload = new JSONObject().put("firstName", APIGlobalVariables.firstName)
				.put("lastName", APIGlobalVariables.lastName).put("email", APIGlobalVariables.email)
				.put("city", APIGlobalVariables.city).put("state", APIGlobalVariables.state)
				.put("studentNumber", APIGlobalVariables.studentNumber).toString();

		id = RestAssured.given().auth().oauth2(APIGlobalVariables.token).body(payload).contentType(ContentType.JSON)
				.when().post(APIConstants.CREATE_STUDENT_ENDPOINT).prettyPeek().body().jsonPath().getInt("result.id");
	}

	@When("I get the student by the stored id")
	public void i_get_the_student_by_the_stored_id() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		response = RestAssured.given().auth().oauth2(APIGlobalVariables.token).queryParam("id", id).when()
				.get(APIConstants.GET_ONE_STUDENT_ENDPOINT).prettyPeek();
	}

	@Then("I validate the information of the created student")
	public void i_validate_the_information_of_the_created_student() {
		response.then().assertThat().body("result.firstName", equalTo(APIGlobalVariables.firstName)).and()
				.body("result.lastName", equalTo(APIGlobalVariables.lastName)).and()
				.body("result.email", equalTo(APIGlobalVariables.email)).and()
				.body("result.city", equalTo(APIGlobalVariables.city)).and()
				.body("result.state", equalTo(APIGlobalVariables.state));
		// .and().body("result.studentNumber",
		// equalTo(APIGlobalVariables.studentNumber));

		// lets validate the student number using JUnit assertion
		String studentNumber = response.body().jsonPath().getString("result.studentNumber");
		Assert.assertEquals(APIGlobalVariables.studentNumber, studentNumber);

		// lets get all the student information and print them
		Map<String, String> studentInfo = response.body().jsonPath().getMap("result");

		System.out.println("The student information:" + studentInfo);

		// I may also iterate through the map
		// task:
	}

}
