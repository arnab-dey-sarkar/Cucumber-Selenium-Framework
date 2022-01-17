package framework.stepdefinitions;

import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.dataProvider.ConfigProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_StepDefinitions {
	RequestSpecification request;
	Response response;

	@Given("The BaseURL is \"([^\"]*)\"")
	public void the_BaseURL_is(String baseURI) throws Exception {
		RestAssured.baseURI = ConfigProvider.getAsString("BaseURL") + baseURI;
		request = RestAssured.given();
	}

	@Given("Static \"([^\"]*)\" Information Are Loaded")
	public void static_Information_Are_Loaded(String headers) throws Exception {
		if (headers != null) {
			String[] headerList = DefaultStepDefinition.getCellData(headers).split("\n");
			for (String list : headerList) {
				String[] values = list.split(":");
				request.header(values[0], values[1]);
			}
		}
	}

	@When("User Performs GET Operation")
	public void user_Performs_GET_Operation() {
		response = request.when().get();
	}

	@Then("Verify (\\d+) In Response")
	public void verify_In_Response(Integer statusCode) {
		System.out.println("---------------------RESPONSE----------------------------");
		System.out.println(response.body().asString());
		response.then().statusCode(statusCode);
		System.out.println("---------------------RESPONSE----------------------------");
	}

}
