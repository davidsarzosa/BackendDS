package com.neotech.steps;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.neotech.utils.APIConstants;
import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateTokenSteps {

	RequestSpecification request;
	Response response;

	@Given("I create a token request")
	public void i_create_a_token_request() {
		RestAssured.baseURI = APIConstants.BASE_URI;
		request = RestAssured.given();
	}

	@Given("I provide the request body to generate token")
	public void i_provide_the_request_body_to_generate_token() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		String username = ConfigsReader.getProperty("ApiUsername");
		String password = ConfigsReader.getProperty("ApiPassword");

		String payload = "{\r\n" + "  \"userNameOrEmailAddress\": \"" + username + "\",\r\n" + "  \"password\": \""
				+ password + "\",\r\n" + "  \"rememberClient\": true\r\n" + "}";

		// alternative way of creating the payload using JSONObject
		String payload2 = new JSONObject().put("userNameOrEmailAddress", username).put("password", password)
				.put("rememberClient", true).toString();

		request.body(payload);
	}

	@Given("I set the header information for token request")
	public void i_set_the_header_information_for_token_request() {
		// hardcoded way of setting the header
		// request.header("Content-Type", "application/json");

		// using RestAssured method to set the header
		request.contentType(ContentType.JSON);
	}

	@When("I send the POST request to GenerateToken endpoint")
	public void i_send_the_post_request_to_generate_token_endpoint() {
		response = request.when().post(APIConstants.GENERATE_TOKEN_ENDPOINT);
		response.prettyPeek();
	}

	@Then("I validate the status code is equal to {int}")
	public void i_validate_the_status_code_is(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("I validate that the body contains {string}")
	public void i_validate_that_the_body_contains(String accessToken) {
		response.then().assertThat().body(Matchers.containsString(accessToken));
	}

	@Then("I validate that the value of {string} is true")
	public void i_validate_that_the_value_of_is_true(String success) {
		response.then().assertThat().body(success, Matchers.equalTo(true));
	}

}
