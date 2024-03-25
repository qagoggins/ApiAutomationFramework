package io.framework.demo;

import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class AuthDemo {
    @Test
    public void basicAuth() {
        Response response = given()
//                .auth()
//                .basic("postman", "password") // way to do 1
                .config(config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization"))) // blacklist the header , so that it wouldn't be shown in the logs
                .header("Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==") // way to do 2
                .log().all()
                .get("https://postman-echo.com/basic-auth");
        response.prettyPrint();
    }
}
