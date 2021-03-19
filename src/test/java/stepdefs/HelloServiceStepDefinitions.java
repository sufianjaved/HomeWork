package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ApplicationConfiguration;

import static constant.ScenarioNameConstant.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class HelloServiceStepDefinitions {

    private static final Logger log = LoggerFactory.getLogger(HelloServiceStepDefinitions.class);
    private RequestSpecification request;
    private Response responseObject;

    @Given("Hello service running on localhost port {int}")
    public void hello_service_running_on_localhost_port(int port) {
        log.info(BASE_SETUP);
        request = new RequestSpecBuilder()
                .setBaseUri(ApplicationConfiguration.getLocalhostBaseUrl())
                .setPort(port)
                .setContentType(ContentType.TEXT)
                .build();
    }

    @When("a user calls the service with {string}")
    public void a_user_calls_the_service_with(String inputText) {
        responseObject = given().
                spec(request).
                when().
                get(inputText);
    }

    @Then("verify the response code is {int} and body contains welcome message for {string}")
    public void verify_the_response_code_is_and_body_contains_welcome_message_for(int expectedCode, String inputText) {
        String message = responseObject.getBody().asString();
        assertTrue("Service welcome message issue : ", message.equals("Hi there, " + inputText + "!"));
        assertEquals("Service response code issue : ", responseObject.statusCode(), expectedCode);
    }
}