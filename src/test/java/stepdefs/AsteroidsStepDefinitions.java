package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
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
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class AsteroidsStepDefinitions{

    private static final Logger log = LoggerFactory.getLogger(AsteroidsStepDefinitions.class);
    private ResponseSpecification response;
    private RequestSpecification request;

    @Given("a Trajectory exists")
    public void a_trajectory_exists(){
        log.info(BASE_SETUP);
        request = new RequestSpecBuilder()
                .setBaseUri(ApplicationConfiguration.getHostBaseUrl())
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    @When("a user retrieves the Trajectory Details by date range {string} and {string} and dist-max as {string}")
    public void a_user_retrieves_the_trajectory_details_by_date_range(String date_min, String date_max, String dist_max){
        request.when()
                .param("date-min", date_min) //1990-01-01
                .param("date-max", date_max) //1999-12-31
                .param("dist-max", dist_max); //0.01
    }

    @Then("verify the statusCode and contentType")
    public void verify_the_statusCode_and_contentType(){
        log.info(VERIFY_STATUS_CODE_200_AND_CONTENT_TYPE_JSON);
        response = new ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @And("response includes the count {string}")
    public void response_includes_the_following_count(String count) {
        log.info(VALIDATE_RESPONSE_BODY);
        Response responseObject = given().
                spec(request).get();
        responseObject.
                then().
                body("count", equalTo(count));
    }
}
