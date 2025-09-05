package com.neotech.steps;

import java.util.List;
import java.util.Map;

import com.neotech.utils.DBUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DBSteps {

	List<Map<String, String>> customerList;

	@Given("I am connected to the database")
	public void i_am_connected_to_the_database() {
		DBUtils.getConnection();
		System.out.println("Connection is successful!!!");
	}

	@When("I get the top three customers by credit limit")
	public void i_get_the_top_three_customers_by_credit_limit() {
		customerList = DBUtils.storeDataFromDb("SELECT customerNumber, customerName, creditLimit "
				+ " FROM customers ORDER BY creditLimit DESC LIMIT 3;");

		// System.out.println(customerList);
	}

	@Then("I print their names and credit limits")
	public void i_print_their_names_and_credit_limits() {
		System.out.println("--------------------------------");

		for (Map<String, String> customer : customerList) {
			// System.out.println(customer);

			String name = customer.get("customerName");
			String credit = customer.get("creditLimit");

			System.out.println(name + " -> " + credit);
		}

		System.out.println("--------------------------------");
	}

	@Then("I close the database connection")
	public void i_close_the_database_connection() {
		DBUtils.closeConnection();
		System.out.println("Closing the connection!!!");
	}

}
