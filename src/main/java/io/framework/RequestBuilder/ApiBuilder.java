package io.framework.RequestBuilder;


import io.framework.Enums.PropertiesType;
import io.framework.Utils.PropertyUtils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public final class ApiBuilder {
    private ApiBuilder() {}

    public static RequestSpecification buildRequestForGetCalls() {
        return given()
                .baseUri(PropertyUtils.getValue(PropertiesType.BASEURL))
                .log().all();
    }
    public static RequestSpecification buildRequestForPostCalls() {
        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }

}
