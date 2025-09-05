package com.neotech.steps;

import static org.hamcrest.Matchers.equalTo;

import com.neotech.utils.APIConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetOneClassSteps {

	RequestSpecification request;
	Response response;

	@Given("I create a GET request")
	public void i_create_a_get_request() {
		RestAssured.baseURI = APIConstants.BASE_URI;
		request = RestAssured.given();
	}

	@Given("I provide the classId  {int} as path param")
	public void i_provide_the_class_id_as_path_param(Integer classId) {
		request.pathParam("Id", classId);
	}

	@When("I send the GET request to GetOneClass endpoint")
	public void i_send_the_get_request_to_get_one_class_endpoint() {
		response = request.when().get(APIConstants.GET_ONE_CLASS_ENDPOINT);
		response.prettyPeek();
	}

	@Then("I validate the status code is {int}")
	public void i_validate_the_status_code_is(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("I validate that the Id in response body is {int}")
	public void i_validate_that_the_id_in_response_body_is(Integer id) {
		response.then().assertThat().body("result.id", equalTo(id));
	}

	@Then("I validate that the class term is {string}")
	public void i_validate_that_the_class_term_is(String term) {
		response.then().assertThat().body("result.term", equalTo(term));
	}
}
