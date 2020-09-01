package by.kazak.taf.business.common;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import by.kazak.taf.core.config.ConfigData;
import by.kazak.taf.core.util.TestListener;
import io.restassured.RestAssured;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Listeners({TestListener.class})
public abstract class Base {

    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite() {
        initParams();
        environmentLogger();
        RestAssured.baseURI = ConfigData.BASE_API_URL;
    }

    protected abstract void initParams();

    private void environmentLogger() {
        log.info("====ENVIRONMENT INFO====");
        Map<Object, Object> envInfo = Stream.of(new String[][]{
                {"APP", ConfigData.APP_NAME},
                {"ENVIRONMENT", ConfigData.APP_ENV},
                {"BROWSER", ConfigData.BROWSER}
        }).collect(Collectors.toMap(key -> key[0], value -> value[1]));
        if (ConfigData.BROWSER.equals("none")) envInfo.put("REST API", "true");
        envInfo.forEach((key, value) -> log.info(String.format("[%s] = %s", key, value)));
        log.info("========================");
    }
}
