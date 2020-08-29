package by.kazak.taf.business.restApi.assertion;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kazak.taf.business.restApi.ResponseFieldPath;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public interface BaseAssertion extends ResponseFieldPath {

    Logger LOG = LogManager.getRootLogger();
    ;

    default void checkStatusCode(ValidatableResponse response, int statusCode) {
        assertThat(String.format("Check that status code of request is equal to: %d", statusCode), extract(response).statusCode(),
                equalTo(statusCode));
        LOG.info("Status code is correct. Status code: {}", statusCode);
    }

    default void checkContentType(ValidatableResponse response, ContentType contentType) {
        assertThat(String.format("Check that response contains '%s' content type", contentType.toString()), extract(response).contentType(),
                equalTo(contentType));
        LOG.info("Response contains '{}' content type", contentType);
    }

    default void checkMatchJsonSchema(ValidatableResponse response, URI uri) {
        response.assertThat().body(matchesJsonSchema(uri));
        LOG.info("Response match given json scheme");
    }
}
