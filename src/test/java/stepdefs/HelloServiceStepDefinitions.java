package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ApplicationConfiguration;

import static constant.ScenarioNameConstant.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class HelloServiceStepDefinitions {

    private static final Logger log = LoggerFactory.getLogger(HelloServiceStepDefinitions.class);
    private ResponseSpecification response;
    private RequestSpecification request;
    private Response responseObject;

    @Given("Hello service running on localhost port {int}")
    public void hello_service_running_on_localhost_port(int port) {
        log.info(BASE_SETUP);
        request = new RequestSpecBuilder()
                .setBaseUri(ApplicationConfiguration.getLocalhostBaseUrl())
                .setPort(port)
                .setContentType(ContentType.TEXT)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    @When("a user calls the service with {string}")
    public void a_user_calls_the_service_with(String inputText) {
        response = given(request).pathParam("appendedValue", inputText)
        .then();
        response.expect().body(containsString("Hi there, "+inputText+"!"));
    }

    @Then("verify the response is {int}")
    public void verify_the_response_is(int expectedCode) {
        response.expect().statusCode(expectedCode);
    }
}