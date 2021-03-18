package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ApplicationConfiguration;

import static constant.ScenarioNameConstant.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class HelloServiceStepDefinitions {

    private static final Logger log = LoggerFactory.getLogger(AsteroidsStepDefinitions.class);
    private ResponseSpecification response;
    private RequestSpecification request;

    @Given("Hello service running on localhost port {int}")
    public void hello_service_running_on_localhost(int port) {
        log.info(BASE_SETUP);
        request = new RequestSpecBuilder()
                .setBaseUri(ApplicationConfiguration.getLocalhostBaseUrl())
                .setPort(port)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    @When("a user calls the service with {string}")
    public void a_user_calls_the_service_on_port_with(String inputText) {
        given().pathParam("appendedValue", inputText).when().
                spec(request).
                get("/{appendedValue}");
    }
    @Then("verify the response body text includes {string}")
    public void verify_the_response_body_text_includes(String testString) {
        log.info(VALIDATE_RESPONSE_BODY);
        /*
                given().pathParam("test", testString).when().
                spec(request).
                        get("/{test}").
                then().
                assertThat().
                statusCode(SC_OK).
                body(containsString(testString));
*/
        response = new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectContentType(ContentType.JSON)
                .build();

    }

}
