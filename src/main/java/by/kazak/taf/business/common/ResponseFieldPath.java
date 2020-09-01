package by.kazak.taf.business.common;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public interface ResponseFieldPath {

    default String getUserId(ValidatableResponse response, Enum fieldPath) {
        return extractPath(response, fieldPath.toString());
    }

    default String extractPath(ValidatableResponse response, String pathToField) {
        return extract(response).path(pathToField);
    }

    default ExtractableResponse<Response> extract(ValidatableResponse response) {
        return response.extract();
    }
}
