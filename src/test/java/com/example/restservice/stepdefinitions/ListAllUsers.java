package com.example.restservice.stepdefinitions;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Rule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ListAllUsers {

    @Rule
    public SpringIntegrationMethodRule springIntegrationMethodRule = new SpringIntegrationMethodRule();

    @LocalServerPort
    private int port;

    @Given("{actor} is allowed to call the API")
    public void actor_is_allowed_to_call_the_api(final Actor actor) {

        actor.whoCan(CallAnApi.at("http://localhost:" + port));
    }

    @When("{actor} calls the api for the user with ID {string}")
    public void he_adds_to_the_list(final Actor actor, final String userId) {

        actor.attemptsTo(
                Get.resource("/users/" + userId)
        );
    }

    @Then("{actor} sees that the user has first name {string} and last name {string}")
    public void he_sees_as_an_item_in_the_todo_list(final Actor actor, final String expectedFirstName, final String expectedLastName) {

        actor.should(
                seeThatResponse("User details should be correct",
                        response -> response.statusCode(200)
                                .body("firstName", equalTo(expectedFirstName))
                                .body("lastName", equalTo(expectedLastName))));
    }
}
