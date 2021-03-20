package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ApplicationConfiguration;

import static io.restassured.http.ContentType.TEXT;
import static constant.ScenarioNameConstant.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class HelloServiceStepdefs {

    private static final Logger log = LoggerFactory.getLogger(HelloServiceStepdefs.class);
    private RequestSpecification request;
    private Response responseObject;

    @Given("Hello service running on localhost port {int}")
    public void hello_service_running_on_localhost_port(int port) {
        log.info(BASE_SETUP);

        request = new RequestSpecBuilder()
                .setBaseUri(ApplicationConfiguration.getLocalhostBaseUrl())
                .setPort(port)
                .setContentType(TEXT)
                .build();
    }

    @When("a user calls the service with {string}")
    public void a_user_calls_the_service_with(String inputText) {
        responseObject = given().
                spec(request).
                when().
                get(inputText);
    }

    @Then("verify the response code {int} and content type and body contains welcome message for {string}")
    public void verify_the_response_code_and_content_type_and_body_contains_welcome_message_for(int expectedCode, String inputText) {
        log.info(VERIFY_STATUS_CODE_200_AND_CONTENT_TYPE_TEXT);

        String message = responseObject.getBody().asString();
        assertEquals("Service welcome message issue : ",
                "Hi there, " + inputText + "!",
                message);
        assertEquals("Service response code issue : ", expectedCode, responseObject.statusCode());
        assertTrue("Content Type does not match : ",responseObject.contentType().contains("text/plain"));
    }
}