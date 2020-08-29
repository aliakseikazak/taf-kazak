package by.kazak.taf.business.restApi.service;

import java.net.URI;

import by.kazak.taf.business.restApi.assertion.BaseAssertion;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_OK;

class RestApiService implements BaseAssertion {

    String setApiPath (Enum apiPath, String slug) {
        return String.format(apiPath.toString(), slug);
    }
    
    void generalChecks(ValidatableResponse response, int statusCode, URI uri) {
        checkStatusCode(response, statusCode);
        checkContentType(response, ContentType.JSON);
        checkMatchJsonSchema(response, uri);
    }

    void GETChecks(ValidatableResponse response, URI uri) {
        generalChecks(response, HTTP_OK, uri);
    }
}
