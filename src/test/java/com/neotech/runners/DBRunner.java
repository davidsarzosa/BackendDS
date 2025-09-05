package com.neotech.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = "src/test/resources/features/",
		glue = "com.neotech.steps",
		dryRun = false,
		monochrome = true,
		tags = "@DB",

		plugin = { 
				// "pretty",
				"html:target/db-example-report.html",
				"json:target/cucumber.json",
		}
)

public class DBRunner {

}
