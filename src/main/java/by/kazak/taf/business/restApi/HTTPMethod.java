package by.kazak.taf.business.restApi;

import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@Log4j2
public class HTTPMethod {

    public static ValidatableResponse get(String uriPath) {
        log.info("GET URL: {}", baseURI + uriPath);
        return given().when().get(uriPath).then();
    }
}
