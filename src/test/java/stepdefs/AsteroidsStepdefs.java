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

import static constant.ScenarioNameConstant.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class AsteroidsStepdefs {

    private static final Logger log = LoggerFactory.getLogger(AsteroidsStepdefs.class);
    private RequestSpecification request;
    private Response responseObject;

    @Given("a Trajectory exists")
    public void a_trajectory_exists(){
        log.info(BASE_SETUP);

        request = new RequestSpecBuilder()
                .setBaseUri(ApplicationConfiguration.getHostBaseUrl())
                .setContentType(JSON)
                .build();
    }

    @When("a user retrieves the Trajectory Details by date range {string} and {string} and dist-max as {string}")
    public void a_user_retrieves_the_trajectory_details_by_date_range_and_dist_max(String date_min, String date_max, String dist_max){
        request.when()
                .param("date-min", date_min)
                .param("date-max", date_max)
                .param("dist-max", dist_max);
        responseObject = given()
                .spec(request).get();
    }

    @Then("verify the statusCode and contentType and {string}")
    public void verify_the_statusCode_and_contentType_and_countInResponseBody(String count){
        log.info(VERIFY_STATUS_CODE_200_AND_CONTENT_TYPE_JSON);
        log.info(VALIDATE_RESPONSE_BODY);

        responseObject
                .then()
                .body("count", equalTo(count))
                .contentType(JSON)
                .statusCode(SC_OK);
    }
}
