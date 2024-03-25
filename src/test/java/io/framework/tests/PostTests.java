package io.framework.tests;

import io.framework.Annotations.FrameworkAnnotation;
import io.framework.Constants.FCwithSingleton;
import io.framework.Pojo.Employee;
import static io.framework.Utils.RandomUtils.*;

import io.framework.Reports.ExtentLogger;
import io.framework.RequestBuilder.ApiBuilder;
import io.framework.Utils.ApiUtils;
import io.framework.Utils.RandomUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

public class PostTests extends BaseTest {

    @Test
    @FrameworkAnnotation(author = {"Baiel"}, category = {"Smoke", "Post call"})
    public void postTestUsingPojo() {
        Employee employee = Employee
                .builder()
                .setFname(getFirstName())
                .setLname(getLastName())
                .setId(String.valueOf(getId()))
                .build();

        RequestSpecification requestSpecification = ApiBuilder
                .buildRequestForPostCalls()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");

        ExtentLogger.logResponse(response.prettyPrint());

        assertThat(response.getStatusCode()).isEqualTo(201);
    }
    @Test
    @FrameworkAnnotation
    public void postRequestUsingExternalFile(Method method) {
        String jsonString = ApiUtils
                .readJsonAndGetAsString(FCwithSingleton.getInstance().getRequestJsonFolderPath()+"request.json")
                .replace("firstname", RandomUtils.getFirstName())
                .replace("lastname", RandomUtils.getLastName());
                JSONObject jsonObject = new JSONObject(jsonString);
                jsonObject.put("id", String.valueOf(getId()));
                String resource = jsonObject.toString();

        RequestSpecification request = ApiBuilder
                .buildRequestForPostCalls()
                .body(resource);
        ExtentLogger.logRequest(request);
        Response response = request.post("/employees");
        ExtentLogger.logResponse(response.prettyPrint());

        ApiUtils.storeStringAsJsonFile(FCwithSingleton.getInstance().getResponseJsonFolderPath()+method.getName()+"response.json", response);
        assertThat(response.getStatusCode()).isEqualTo(201);
    }
}