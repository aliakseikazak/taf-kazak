package by.kazak.taf.business.common;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public interface BaseApiAssertion extends ResponseFieldPath {

    Logger log = LogManager.getRootLogger();

    default void checkStatusCode(ValidatableResponse response, int statusCode) {
        assertThat(String.format("Check that status code of request is equal to: %d", statusCode), extract(response).statusCode(),
                equalTo(statusCode));
        log.info("Status code is correct. Status code: '{}'", statusCode);
    }

    default void checkContentType(ValidatableResponse response, ContentType contentType) {
        assertThat(String.format("Check that response contains '%s' content type", contentType), extract(response).contentType(),
                equalTo(contentType.toString()));
        log.info("Response contains '{}' content type", contentType);
    }

    default void checkMatchJsonSchema(ValidatableResponse response, URI uri) {
        response.assertThat().body(matchesJsonSchema(uri));
        log.info("Response match given json scheme");
    }
}
