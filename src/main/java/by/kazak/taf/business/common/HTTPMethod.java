package by.kazak.taf.business.common;

import by.kazak.taf.business.model.User;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@Log4j2
public class HTTPMethod {

    private static RequestSpecification authentication(User user) {
        return given().auth().oauth2(user.getToken());
    }

    private static String jsonString(Object o) {
        return new Gson().toJson(o);
    }

    public static ValidatableResponse get(User user, String uriPath) {
        log.info("GET URL: {}{}", baseURI, uriPath);
        return authentication(user).when().get(uriPath).then();
    }

    public static ValidatableResponse post(User user, String uriPath, Object object) {
        log.info("POST URL: {}{}", baseURI, uriPath);
        return authentication(user).contentType(ContentType.JSON).body(jsonString(object)).when().post(uriPath).then();
    }
}
