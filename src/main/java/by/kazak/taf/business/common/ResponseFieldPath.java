package by.kazak.taf.business.common;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public interface ResponseFieldPath {

    default ExtractableResponse<Response> extract(ValidatableResponse response) {
        return response.extract();
    }
}
