package io.framework.tests;

import static io.framework.RequestBuilder.ApiBuilder.*;

import io.framework.Annotations.FrameworkAnnotation;
import io.framework.Pojo.Employee;
import static io.framework.Utils.RandomUtils.*;

import io.framework.Reports.ExtentLogger;
import io.framework.Utils.ApiUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

public class AssignmentTests {

    @Test
    @FrameworkAnnotation(author = {"Baiel"}, category = {"assignment"})
    public void assignmentTest() {
        Response response = buildRequestForGetCalls().get("/employees");
        int size = response.jsonPath().getList("$").size(); // 10

        Employee employee = Employee.builder()
                .setId(String.valueOf(getId()))
                .setFname(getFirstName())
                .setLname(getLastName())
                .build();
        RequestSpecification requestSpecification = buildRequestForPostCalls()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);

        ExtentLogger.info("Response of post request: ");
        Response postResponse = requestSpecification.post("/employees");
        ExtentLogger.logResponse(postResponse.asString());

        assertThat(buildRequestForGetCalls().get("/employees").jsonPath().getList("$").size()).isEqualTo(size+1); // 11

        employee.setFname("Default name");
        String id = employee.getId();
        Response putResponse = buildRequestForPostCalls().pathParam("id", id).body(employee).put("/employees/{id}");
        ApiUtils.storeStringAsJsonFile("putresponse.txt", putResponse);
        ExtentLogger.info("Response of put request: ");
        ExtentLogger.logResponse(putResponse.asString());

        Response deleteResponse = buildRequestForGetCalls().pathParam("id", id).delete("/employees/{id}");
        ExtentLogger.info("Response of delete request: ");
        ExtentLogger.logResponse(deleteResponse.prettyPrint());
        assertThat(buildRequestForGetCalls().get("/employees").jsonPath().getList("$").size()).isEqualTo(size);
    }
}
